package com.luckysun.tabfragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private MainActivity mActivity;
    private ViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<>();

    public TabFragmentAdapter(FragmentManager fm, ViewPager viewPager, MainActivity activity) {
        super(fm);
        this.mViewPager = viewPager;
        this.mActivity = activity;
        mViewPager.setOnPageChangeListener(this);

        for (TabInfo info : mActivity.mPageInfo.tabs) {
            mFragments.add(TabFragment.newInstance(info));
        }

    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    /*-----------OnPageChangeListener-----------*/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        scrollToPage();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        if(state == 2) {
//            scrollToPage();
//        }

    }
    /*-----------OnPageChangeListener-----------*/


    public void scrollToPage() {
        mActivity.updateTab(mViewPager.getCurrentItem());
        if (!((TabFragment) getItem(mViewPager.getCurrentItem())).isLoaded()) {
            ((TabFragment) getItem(mViewPager.getCurrentItem())).loadDatas();
        }

    }
}
