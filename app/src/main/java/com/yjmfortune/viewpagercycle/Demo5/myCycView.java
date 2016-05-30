package com.yjmfortune.viewpagercycle.Demo5;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yjmfortune.viewpagercycle.R;

import java.util.List;

/**
 * Created by lixian on 2016/3/15.
 */
public class myCycView extends RelativeLayout {

    private Context ct;
    private List<ImageView> imageData;
    private List<ImageView> pointData;
    ViewPager mViewPager;
    LinearLayout mLinearLayout;
    List<ImageView> mList;
    List<ImageView> mListPoint;
    int select = 0;
    Handler handler;
    MyRun mMyRun;

    public myCycView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public myCycView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setCycView(LayoutInflater inflater, final List<ImageView> mList, final List<ImageView> mListPoint) {


        this.mList = mList;
        this.mListPoint = mListPoint;
        View view = inflater.inflate(R.layout.activity_main5, this);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);
        for (ImageView img : mListPoint) {
            mLinearLayout.addView(img);
        }
        MyPagerAdapter adapter = new MyPagerAdapter(mList, mListPoint);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(200 * mList.size());
        handler = new Handler();
        mMyRun = new MyRun();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                select = position;
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
    }

    public class MyPagerAdapter extends PagerAdapter {
        List<ImageView> mList;
        List<ImageView> mListPoint;

        public MyPagerAdapter(List<ImageView> mList, List<ImageView> mListPoint) {
            this.mList = mList;
            this.mListPoint = mListPoint;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mList.get(position % mList.size());
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    //开始游戏
    public void start() {
        mMyRun.start();

    }

    //是否在,可以 轮播
    boolean isCyc = false;
    boolean canCyc = true;

    public class MyRun implements Runnable {


        public void start() {
            if (!isCyc) {
                isCyc = true;
                handler.removeCallbacks(this);
                handler.postDelayed(this, 2000);
            }
        }


        public void stop() {
            if (isCyc) {
                isCyc = false;
                handler.removeCallbacks(this);
            }

        }

        @Override
        public void run() {
            if (isCyc) {
                if (canCyc) {
                    mViewPager.setCurrentItem(select + 1);
                    //这样就变成无限循环了
                }
                handler.postDelayed(this, 2000);
            }

        }
    }
}
