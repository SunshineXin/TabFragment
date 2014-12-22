package com.luckysun.tabfragmentdemo;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    public static final String TAG = "TabFragment";

    private static final int TAB_SPLIT_HEIGHT = 23;

    public PageInfo mPageInfo;
    private LinearLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mSliderImageView;
    private TabFragmentAdapter mAdapter;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getDate();

        updateTabs();

    }


    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout = (LinearLayout) findViewById(R.id.layout_tab);
        mSliderImageView = (ImageView) findViewById(R.id.iv_tab);
    }

    private void getDate() {
        mPageInfo = new PageInfo();

        for (int i = 1; i < 5; i++) {
            TabInfo info = new TabInfo(i, "tab" + i);
            mPageInfo.tabs.add(info);
        }
        mPageInfo.page_count = mPageInfo.tabs.size();

    }


    private void updateTabs() {
        Log.d(TAG, "-----------------------------\nupdateTabs");

        int homeIndex = mPageInfo.pageIndex;

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        drawTabs(metrics.widthPixels);

        mAdapter = new TabFragmentAdapter(this.getSupportFragmentManager(), mViewPager, this);
        mViewPager.setAdapter(mAdapter);

        updateTab(homeIndex);
        mViewPager.setCurrentItem(homeIndex);

        Log.d(TAG, "-----------------------------");
        //FIXME 初始化首页的数据
        ((TabFragment)mAdapter.getItem(homeIndex)).loadDatas();
    }

    public void updateTab(int id) {
        if (mPageInfo.tabs.size() == 0) {
            return;
        }
        int margin = id * (width / mPageInfo.tabs.size());
        android.widget.RelativeLayout.LayoutParams param = (android.widget.RelativeLayout.LayoutParams) mSliderImageView.getLayoutParams();
        param.leftMargin = margin;
        mSliderImageView.setLayoutParams(param);
    }

    private void drawTabs(int drawWidth) {
        if (drawWidth != 0) {
            width = drawWidth;
        } else {
            width = mTabLayout.getWidth();
        }

        float split = dip2px(this, 1);

        float tabWidth = (width - (mPageInfo.tabs.size() - 1) * split)
                / mPageInfo.tabs.size();
        for (int i = 0; i < mPageInfo.tabs.size(); i++) {

            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.tab_title_textview, null);
            textView.setId(i);
            textView.setText(mPageInfo.tabs.get(i).name);
            textView.setOnClickListener(this);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams((int) tabWidth, LinearLayout.LayoutParams.MATCH_PARENT);
            param.gravity = Gravity.CENTER_VERTICAL;
            textView.setLayoutParams(param);
            mTabLayout.addView(textView);
            if (i < mPageInfo.tabs.size() - 1) {
                ImageView iv = new ImageView(this);
                LinearLayout.LayoutParams paramImageView = new LinearLayout.LayoutParams((int) split, dip2px(this, TAB_SPLIT_HEIGHT));
                paramImageView.gravity = Gravity.CENTER_VERTICAL;
                iv.setLayoutParams(paramImageView);
                iv.setBackgroundColor(0xFFEAEAEA);
                mTabLayout.addView(iv);
            }
        }
        int size = mPageInfo.tabs.size();
        if (size != 0) {
            mSliderImageView.getLayoutParams().width = width / mPageInfo.tabs.size();
        }
    }

    @Override
    public void onClick(View v) {
        updateTab(v.getId());
        mViewPager.setCurrentItem(v.getId());
    }


    public static int dip2px(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float scale = metrics.density;
        return (int) (dipValue * scale + 0.5f);
    }


}
