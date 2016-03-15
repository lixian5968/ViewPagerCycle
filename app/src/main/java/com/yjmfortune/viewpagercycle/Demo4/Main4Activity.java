package com.yjmfortune.viewpagercycle.Demo4;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yjmfortune.viewpagercycle.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main4Activity extends AppCompatActivity {
    ViewPager mViewPager;
    LinearLayout mLinearLayout;
    LayoutInflater inflater;
    List<ImageView> mList;
    List<ImageView> mListPoint;
    Context ct;


    public boolean Scoll = false;
    mAdapter adapter;
    //能滚动
    boolean canCyc = true;

    public int select = 0;
    private ScheduledExecutorService scheduledExecutorService;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                mViewPager.setCurrentItem(select);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);
        ct = this;


        inflater = LayoutInflater.from(Main4Activity.this);
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


        adapter = new mAdapter(mList);
        mViewPager.setAdapter(adapter);
        mListPoint = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            ImageView img = new ImageView(Main4Activity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(ct, 15), dip2px(ct, 15));
            params.setMargins(dip2px(ct, 5), dip2px(ct, 5), dip2px(ct, 5), dip2px(ct, 5));
            if (i == 0) {
                img.setImageResource(R.drawable.point_pressed);
            } else {
                img.setImageResource(R.drawable.point_unpressed);
            }
            mLinearLayout.addView(img, params);
            mListPoint.add(img);
        }


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                select = position;
                for (int i = 0; i < mListPoint.size(); i++) {
                    if (i == (position % mList.size())) {
                        mListPoint.get(i).setImageResource(R.drawable.point_pressed);
                    } else {
                        mListPoint.get(i).setImageResource(R.drawable.point_unpressed);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                switch (state) {
                    case 1:
                        canCyc = true;
                        break;
                    case 2:
                        canCyc = false;
                        break;
                    case 0:
                        canCyc = true;
                        break;


                }

            }
        });


        mViewPager.setCurrentItem(200 * mList.size());

    }


    //mAdapter 负责提供数据  mViewPager 负责显示数据
    //mAdapter 只会加载三个界面，刚开始的 0-2 界面加载完之后，第四个界面加载的时候 第一个界面被拿掉，但是数据拿掉了，mViewPager显示的位置没有改变，
    // 当加上去第四个的时候 mViewPager 第一个界面还是在的

    //mViewPager 位置已经定下来了 刚开始的时候
    public class mAdapter extends android.support.v4.view.PagerAdapter {

        ArrayList<ImageView> mList;

        public mAdapter(List<ImageView> mList) {
            this.mList = (ArrayList<ImageView>) mList;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            Log.e("lx,isViewFromObject", "view:" + view.getTag() + ",object:" + ((View) object).getTag() + "result:" + (view == object));
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mList.get(position % mList.size());
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            view.setTag(position);
            container.addView(view);
            return view;
        }
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void start(View v) {
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    synchronized (mViewPager) {
                        select = select + 1;
                        handler.sendEmptyMessage(100);
                    }
                }
            }, 1, 1, TimeUnit.SECONDS);
            //第二个参数 开始启动的时间
            //第三个参数 暂停的时间
        }

    }

    public void stop(View v) {
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
    }


}
