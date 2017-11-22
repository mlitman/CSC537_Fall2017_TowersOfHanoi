package com.example.awesomefat.csc537_fall2017_towersofhanoi;

import android.content.Intent;
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
    private Disk d1;
    private Disk d2;
    private Disk d3;
    private Disk temp = null;
    private ViewGroup landingZone;
    private boolean isSourceMode = true;
    private int numberOfMoves = 0;
    private TextView movesTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestSingleton.getInstance().ma = this;
        Core.ma = this;

        this.movesTV = (TextView)this.findViewById(R.id.movesTV);
        this.landingZone = (ViewGroup)this.findViewById(R.id.landingZone);

        d1 = new Disk(1, (TextView) this.findViewById(R.id.disk1TV));
        d2 = new Disk(2, (TextView) this.findViewById(R.id.disk2TV));
        d3 = new Disk(3, (TextView) this.findViewById(R.id.disk3TV));

        t1 = new Tower((ViewGroup)this.findViewById(R.id.tower1Layout));
        t2 = new Tower((ViewGroup)this.findViewById(R.id.tower2Layout));
        t3 = new Tower((ViewGroup)this.findViewById(R.id.tower3Layout));
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        System.out.println("OnPause");

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        System.out.println("OnStop");

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        System.out.println("OnDestroy");

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(Core.shouldReset)
        {
            this.resetGame();
            Core.shouldReset = false;
        }
        System.out.println("OnStart");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        System.out.println("OnResume");

    }

    public void onResetButtonPressed(View v)
    {
        this.resetGame();
    }

    public void resetGame()
    {
        this.isSourceMode = true;
        this.numberOfMoves = 0;
        this.updateMovesTextView();
        this.temp = null;

        //clear the towers
        this.t1.empty();
        this.t2.empty();
        this.t3.empty();

        //put the disks into t1
        this.t1.push(d3);
        this.t1.push(d2);
        this.t1.push(d1);
    }

    private void updateMovesTextView()
    {
        this.movesTV.setText("Moves: " + this.numberOfMoves);
    }

    private void towerLogic(Tower t)
    {
        if(this.isSourceMode)
        {
            //try to retrieve from the appropriate tower and put onto
            //landing zone
            if(t.peek() != null)
            {
                //pop top of tower 1 into temp
                this.temp = t.pop();
                this.landingZone.addView(this.temp.getTheView());
                this.isSourceMode = false;
            }
        }
        else
        {
            //try to take from landing zone and place on appropriate
            //tower
            if((t.peek() != null && this.temp.getSize() < t.peek().getSize()) ||
                    t.peek() == null)
            {
                this.landingZone.removeAllViews();
                t.push(this.temp);
                this.temp = null;
                this.isSourceMode = true;
                this.numberOfMoves++;
                this.updateMovesTextView();
                this.checkForWinner();
            }
        }
    }

    private void checkForWinner()
    {
        if(this.t3.getCount() == 3)
        {
            Core.shouldReset = true;
            Intent i = new Intent(this, WinnerActivity.class);
            i.putExtra("numberOfMoves", this.numberOfMoves);
            this.startActivity(i);
        }
    }

    public void tower1ButtonPressed(View v)
    {
        this.towerLogic(this.t1);
    }

    public void tower2ButtonPressed(View v)
    {
        this.towerLogic(this.t2);
    }

    public void tower3ButtonPressed(View v)
    {
        this.towerLogic(this.t3);
    }
}
