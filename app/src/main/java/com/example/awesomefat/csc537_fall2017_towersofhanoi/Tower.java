package com.example.awesomefat.csc537_fall2017_towersofhanoi;

import android.view.ViewGroup;

/**
 * Created by awesomefat on 11/7/17.
 */

public class Tower
{
    private Disk top;
    private ViewGroup theView;

    public Tower(ViewGroup theView)
    {
        this.theView = theView;
        this.theView.removeAllViews();
        this.top = null;
    }

    public void push(Disk d)
    {
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

    public Disk pop()
    {
        Disk diskToReturn = this.top;

        if(this.top != null)
        {
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
