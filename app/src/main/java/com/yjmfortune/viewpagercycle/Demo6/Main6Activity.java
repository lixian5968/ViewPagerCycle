package com.yjmfortune.viewpagercycle.Demo6;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lxpagercycle.LxCycleView;
import com.yjmfortune.viewpagercycle.R;

import java.util.ArrayList;
import java.util.List;

public class Main6Activity extends AppCompatActivity {
    LxCycleView mLxCycleView;
    Context ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        mLxCycleView = (LxCycleView) findViewById(R.id.mLxCycleView);
        ct = this;
        List<String> myUrlList = new ArrayList<>();
        myUrlList.add("http://dev-weixin.loveiparty.com/upload/images/2016-03-21/party_56ef9236eaae7.jpeg");
        myUrlList.add("http://dev-weixin.loveiparty.com/upload/images/2015-09-26/party_56063f81a3ef4.jpeg");
//        myUrlList.add("http://dev-weixin.loveiparty.com/upload/images/2016-03-21/party_56ef9236eaae7.jpeg");
        mLxCycleView.setListImage(myUrlList);
//        mLxCycleView.start();
    }


}
