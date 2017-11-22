package com.example.awesomefat.csc537_fall2017_towersofhanoi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        int numberOfMoves = this.getIntent().getIntExtra("numberOfMoves", -1);
        System.out.println(numberOfMoves);

    }
}
