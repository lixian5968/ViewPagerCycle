package com.yjmfortune.viewpagercycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yjmfortune.viewpagercycle.Demo1.MainActivity;
import com.yjmfortune.viewpagercycle.Demo2.Main2Activity;
import com.yjmfortune.viewpagercycle.Demo3.Main3Activity;
import com.yjmfortune.viewpagercycle.Demo4.Main4Activity;
import com.yjmfortune.viewpagercycle.Demo5.Main5Activity;

public class AllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all);
    }

    public void onclick1(View v) {
        startActivity(new Intent(AllActivity.this, MainActivity.class));
    }

    public void onclick2(View v) {
        startActivity(new Intent(AllActivity.this, Main2Activity.class));
    }

    public void onclick3(View v) {
        startActivity(new Intent(AllActivity.this, Main3Activity.class));
    }

    public void onclick4(View v) {
        startActivity(new Intent(AllActivity.this, Main4Activity.class));
    }

    public void onclick5(View v) {
        startActivity(new Intent(AllActivity.this, Main5Activity.class));
    }
}
