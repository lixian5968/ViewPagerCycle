package com.yjmfortune.viewpagercycle.Demo3;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yjmfortune.viewpagercycle.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixian on 2016/3/14.
 */
public class CycleView extends FrameLayout implements ViewPager.OnPageChangeListener {

    public Context ct;
    ViewPager mViewPager;
    LinearLayout mLinearLayout;
    LayoutInflater inflater;
    List<ImageView> mList;
    List<ImageView> mListPoint;

    AutoRollRunnable mAutoRollRunnable;
    private MyAdapter mAdapter = null;
    private Handler mHandler = null;

    private int prePosition = 0;

    public CycleView(Context context) {
        super(context);
        ct = context;
        inflater = LayoutInflater.from(ct);
        InitView(ct);

    }

    private void InitView(Context ct) {
        View view = inflater.inflate(R.layout.activity_main3, this);

        mViewPager = (ViewPager) view.findViewById(R.id.m3ViewPager);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.m3LinearLayout);
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, dip2px(ct, 10), 0);
        for (int i = 0; i < mList.size(); i++) {
            ImageView img = new ImageView(ct);
            img.setLayoutParams(params);
            if (i == 0) {
                img.setImageResource(R.drawable.point_pressed);
            } else {
                img.setImageResource(R.drawable.point_unpressed);
            }
            mLinearLayout.addView(img);
            mListPoint.add(img);
        }


        mAutoRollRunnable = new AutoRollRunnable();
        mHandler = new Handler();
        mAdapter = new MyAdapter(mList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);


        startRoll();
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        mListPoint.get(prePosition).setBackgroundResource(R.drawable.point_unpressed);
//        mListPoint.get(position % mListPoint.size()).setBackgroundResource(R.drawable.point_pressed);
//        prePosition = position % mListPoint.size();



        for (int i = 0; i < mListPoint.size(); i++) {
            if (i == (position % mListPoint.size())) {
                mListPoint.get(i).setImageResource(R.drawable.point_pressed);
            } else {
                mListPoint.get(i).setImageResource(R.drawable.point_unpressed);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private class AutoRollRunnable implements Runnable {

        //是否在轮播的标志
        boolean isRunning = false;

        public void start() {
            if (!isRunning) {
                isRunning = true;
                mHandler.removeCallbacks(this);
                mHandler.postDelayed(this, 3000);
            }
        }

        public void stop() {
            if (isRunning) {
                mHandler.removeCallbacks(this);
                isRunning = false;
            }
        }

        @Override
        public void run() {
            if (isRunning) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                mHandler.postDelayed(this, 3000);
            }
        }
    }
    //开始轮播
    public void startRoll() {
        mAutoRollRunnable.start();
    }

    // 停止轮播
    public void stopRoll() {
        mAutoRollRunnable.stop();
    }

    private class MyAdapter extends PagerAdapter {

        //为了复用
        private List<ImageView> imgCache = new ArrayList<ImageView>();

        List<ImageView> mList;

        public MyAdapter(List<ImageView> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            //无限滑动
            return Integer.MAX_VALUE;
//            return mList.size();

        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            ImageView iv = mList.get(position % mList.size());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(iv);
            return iv;
//            container.addView(mList.get(position % mList.size()));
//            return mList.get(position % mList.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (object != null && object instanceof ImageView) {
                ImageView iv = (ImageView) object;
                ((ViewPager) container).removeView(iv);
//                imgCache.add(iv);
            }

//            container.removeView(mList.get(position % mList.size()));
        }
    }
}
