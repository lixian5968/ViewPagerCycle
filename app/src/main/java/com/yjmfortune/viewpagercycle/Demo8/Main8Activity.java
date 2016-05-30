package com.yjmfortune.viewpagercycle.Demo8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yjmfortune.viewpagercycle.R;

import java.util.ArrayList;
import java.util.List;

public class Main8Activity extends AppCompatActivity {
    LoopViewPager mViewPager;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        mViewPager = (LoopViewPager) findViewById(R.id.mViewPager);
        inflater = LayoutInflater.from(Main8Activity.this);
        ImageView view1 = (ImageView) inflater.inflate(R.layout.activity_main_item, null);
        ImageView view2 = (ImageView) inflater.inflate(R.layout.activity_main_item, null);
        ImageView view3 = (ImageView) inflater.inflate(R.layout.activity_main_item, null);
        ImageView view4 = (ImageView) inflater.inflate(R.layout.activity_main_item, null);
        view1.setImageResource(R.drawable.main_img1);
        view2.setImageResource(R.drawable.main_img2);
        view3.setImageResource(R.drawable.main_img3);
        view4.setImageResource(R.drawable.main_img4);
        List<ImageView> mList = new ArrayList<>();
        mList.add(view1);
        mList.add(view2);
        mList.add(view3);
        mList.add(view4);
        mAdapter adapter = new mAdapter(mList);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main8, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mList.get(position);
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            container.addView(view);
            return view;
        }
    }
}
