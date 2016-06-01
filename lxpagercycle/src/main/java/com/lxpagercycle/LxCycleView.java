package com.lxpagercycle;

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

import com.bumptech.glide.Glide;
import com.lxpagercycle.PagerAdapter.InfiniteViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixian on 2016/3/15.
 */
public class LxCycleView extends RelativeLayout {

    private Context ct;
    ViewPager mViewPager;
    LinearLayout mLinearLayout;
    int select = 0;
    Handler handler;
    MyRun mMyRun;

    public LxCycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ct = context;
    }

    public LxCycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ct = context;
    }

    List<ImageView> mListPoint;
    LayoutInflater inflater;

    public void setListImage(List<String> mUrlList) {
        inflater = LayoutInflater.from(ct);

        mListPoint = new ArrayList<>();
        for (int i = 0; i < mUrlList.size(); i++) {
            ImageView img = new ImageView(ct);
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
        View view = inflater.inflate(R.layout.cycle_view, this);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);
        for (ImageView img : mListPoint) {
            mLinearLayout.addView(img);
        }


        MyPagerAdapter adapter = new MyPagerAdapter(mUrlList, mListPoint);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(InfiniteViewPager.FakePositionHelper.CurrentItem * mUrlList.size());
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


//    public class MyPagerAdapter extends InfinitePagerAdapter {
//        List<String> mUrlList;
//        List<ImageView> mListPoint;
//
//        public MyPagerAdapter(List<String> mList, List<ImageView> mListPoint) {
//            this.mUrlList = mList;
//            this.mListPoint = mListPoint;
//        }
//
//        @Override
//        public View getView(int position, View view, ViewGroup container) {
//            ViewHolder holder;
//            if (view != null) {
//                holder = (ViewHolder) view.getTag();
//            } else {
//                view = inflater.inflate(R.layout.image_item, container, false);
//                holder = new ViewHolder(view);
//                view.setTag(holder);
//            }
//            String itemUrl = mUrlList.get(position);
//            holder.position = position;
//            Glide.with(ct).load(itemUrl).placeholder(com.lxpagercycle.R.drawable.ic_image_loading).error(com.lxpagercycle.R.drawable.ic_image_loadfail).crossFade().into(holder.image);
//            view.setTag(R.id.tag_first,itemUrl+"");
//            return view;
//        }
//
//
//        @Override
//        public int getItemCount() {
//            return mUrlList.size();
//        }
//
//        private class ViewHolder {
//            public int position;
//            ImageView image;
//
//            public ViewHolder(View view) {
//                image = (ImageView) view.findViewById(R.id.mImageViewItem);
//            }
//        }
//
//    }




    public class MyPagerAdapter extends PagerAdapter {
        List<String> mUrlList;
        List<ImageView> mListPoint;

        public MyPagerAdapter(List<String> mUrlList, List<ImageView> mListPoint) {
            this.mUrlList = mUrlList;
            this.mListPoint = mListPoint;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           View view = (View) object;
            container.removeView(view);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = null;
            view = getViewInternal(position, view, container);
            container.addView(view);
            String tag = view.getTag(R.id.tag_first).toString();
            return view;
        }

        @Override
        public int getCount() {
            return mUrlList.size() * InfiniteViewPager.FakePositionHelper.MULTIPLIER;
        }
        protected View getViewInternal(int position, View convertView, ViewGroup container) {
            return getView(position % mUrlList.size(), convertView, container);
        }
        public View getView(int position, View view, ViewGroup container) {
            ViewHolder holder;
            if (view != null) {
                holder = (ViewHolder) view.getTag();
            } else {
                view = inflater.inflate(R.layout.image_item, container, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            String itemUrl = mUrlList.get(position);
            holder.position = position;
            Glide.with(ct).load(itemUrl).placeholder(com.lxpagercycle.R.drawable.ic_image_loading).error(com.lxpagercycle.R.drawable.ic_image_loadfail).crossFade().into(holder.image);
            view.setTag(R.id.tag_first,itemUrl+"");
            return view;
        }
        private class ViewHolder {
            public int position;
            ImageView image;

            public ViewHolder(View view) {
                image = (ImageView) view.findViewById(R.id.mImageViewItem);
            }
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

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
