package com.example.awesomefat.csc537_fall2017_towersofhanoi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private Tower t1;
    private Tower t2;
    private Tower t3;
    private Disk temp = null;
    private ViewGroup landingZone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.landingZone = (ViewGroup)this.findViewById(R.id.landingZone);

        Disk d1 = new Disk(1, (TextView) this.findViewById(R.id.disk1TV));
        Disk d2 = new Disk(2, (TextView) this.findViewById(R.id.disk2TV));
        Disk d3 = new Disk(3, (TextView) this.findViewById(R.id.disk3TV));

        t1 = new Tower((ViewGroup)this.findViewById(R.id.tower1Layout));
        t2 = new Tower((ViewGroup)this.findViewById(R.id.tower2Layout));
        t3 = new Tower((ViewGroup)this.findViewById(R.id.tower3Layout));

        //put the disks into t1
        t1.push(d3);
        t1.push(d2);
        t1.push(d1);

    }

    public void tower1ButtonPressed(View v)
    {
        if(temp == null)
        {
            if(t1.peek() != null)
            {
                //pop top of tower 1 into temp
                this.temp = this.t1.pop();
                this.landingZone.addView(this.temp.getTheView());
            }
        }
        else
        {
            /*
            remove view from landing zone so it can POTENTIALLY be added
            to the tower.

            if the push is successful, set temp to null indicating the
            landing zone is empty, otherwise, put the view back in the
            landing zone so it is visually there, and don't set temp to
            null, since the move was not a legal move.
             */

            this.landingZone.removeAllViews();
            this.t1.push(this.temp);
            this.temp = null;
        }
    }
}
