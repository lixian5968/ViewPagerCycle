package com.yjmfortune.viewpagercycle.Demo5;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.yjmfortune.viewpagercycle.R;

import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends AppCompatActivity {


    LayoutInflater inflater;
    List<ImageView> mList;
    List<ImageView> mListPoint;
    Context ct;
    myCycView lxview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main50);


        lxview = (myCycView) findViewById(R.id.mmyCycView);

        ct = Main5Activity.this;
        inflater = LayoutInflater.from(Main5Activity.this);
        ImageView view1 = (ImageView) inflater.inflate(R.layout.activity_main_item, null);
        Glide.with(ct).load("http://dev-weixin.loveiparty.com/upload/images/2016-03-21/party_56ef9236eaae7.jpeg").placeholder(com.lxpagercycle.R.drawable.ic_image_loading).error(com.lxpagercycle.R.drawable.ic_image_loadfail).crossFade().into(view1);
        ImageView view2 = (ImageView) inflater.inflate(R.layout.activity_main_item, null);
        Glide.with(ct).load("http://dev-weixin.loveiparty.com/upload/images/2015-09-26/party_56063f81a3ef4.jpeg").placeholder(com.lxpagercycle.R.drawable.ic_image_loading).error(com.lxpagercycle.R.drawable.ic_image_loadfail).crossFade().into(view2);
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
//        myCycView cy = new myCycView(ct,inflater,mList,mListPoint);
        lxview.setCycView(inflater, mList, mListPoint);
        lxview.start();

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
