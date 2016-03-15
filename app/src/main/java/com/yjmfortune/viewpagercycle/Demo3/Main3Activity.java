package com.yjmfortune.viewpagercycle.Demo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(new CycleView(Main3Activity.this));


    }


}
