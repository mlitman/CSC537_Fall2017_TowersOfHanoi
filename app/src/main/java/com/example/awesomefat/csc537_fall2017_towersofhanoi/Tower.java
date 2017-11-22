package com.example.awesomefat.csc537_fall2017_towersofhanoi;

import android.view.ViewGroup;

/**
 * Created by awesomefat on 11/7/17.
 */

public class Tower
{
    private Disk top;
    private ViewGroup theView;
    private int count = 0;

    public Tower(ViewGroup theView)
    {
        this.theView = theView;
        this.theView.removeAllViews();
        this.top = null;
    }

    public int getCount() {
        return count;
    }

    /*
        currently does NOT check the Tower of Hanoi rules for placing a disk
        1. if the tower is empty, a push is allowed
        2. otherwise, if the disk at the top of the tower is larger than the
           disk I am trying to push it is a LEGAL move
        3. If the disk at the top of the tower is smaller than the disk I am
           trying to push, then it is an ILLEGAL move and should not be allowed
        HINT: Make this guy return true when a move was successfully made, and
              false otherwise.  This way you know whether to clear out the landing
              zone, or reset it to what it was before we tried to make this move.
         */
    public void push(Disk d)
    {
        this.count++;
        if(this.top == null)
        {
            this.top = d;
        }
        else
        {
            d.setNextDisk(this.top);
            this.top = d;
        }

        //visually put this disk at the top of this view
        this.theView.addView(d.getTheView(), 0);
    }

    public void empty()
    {
        while(this.pop() != null);
    }

    public Disk pop()
    {
        Disk diskToReturn = this.top;

        if(this.top != null)
        {
            this.count--;
            this.top = this.top.getNextDisk();
            diskToReturn.setNextDisk(null);
            this.theView.removeViewAt(0);
        }
        return diskToReturn;
    }

    public Disk peek()
    {
        return this.top;
    }
}
