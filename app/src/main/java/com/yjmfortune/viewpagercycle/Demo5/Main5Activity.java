package com.yjmfortune.viewpagercycle.Demo5;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yjmfortune.viewpagercycle.R;

import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends AppCompatActivity {


    LayoutInflater inflater;
    List<ImageView> mList;
    List<ImageView> mListPoint;
    Context ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ct = Main5Activity.this;


        inflater = LayoutInflater.from(Main5Activity.this);
        ImageView view1 = (ImageView) inflater.inflate(R.layout.activity_main_item, null);
        ImageView view2 = (ImageView) inflater.inflate(R.layout.activity_main_item, null);
        ImageView view3 = (ImageView) inflater.inflate(R.layout.activity_main_item, null);
        ImageView view4 = (ImageView) inflater.inflate(R.layout.activity_main_item, null);
        view1.setImageResource(R.drawable.main_img1);
        view2.setImageResource(R.drawable.main_img2);
        view3.setImageResource(R.drawable.main_img3);
        view4.setImageResource(R.drawable.main_img4);
        mList = new ArrayList<>();
        mList.add(view1);
        mList.add(view2);
        mList.add(view3);
        mList.add(view4);
        mListPoint = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            ImageView img = new ImageView(Main5Activity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(ct, 15), dip2px(ct, 15));
            params.setMargins(dip2px(ct, 5), dip2px(ct, 5), dip2px(ct, 5), dip2px(ct, 5));
            if (i == 0) {
                img.setImageResource(R.drawable.point_pressed);
            } else {
                img.setImageResource(R.drawable.point_unpressed);
            }
            img.setLayoutParams(params);
            mListPoint.add(img);
        }
        myCycView cy = new myCycView(ct,inflater,mList,mListPoint);
//        cy.setImageData(mList);
//        cy.setPointData(mListPoint);
        setContentView(cy);
        cy.start();

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
