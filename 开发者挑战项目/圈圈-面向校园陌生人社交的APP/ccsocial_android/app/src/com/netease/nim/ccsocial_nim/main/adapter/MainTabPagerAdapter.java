package com.netease.nim.ccsocial_nim.main.adapter;

import android.content.Context;

import com.netease.nim.ccsocial_nim.common.ui.viewpager.SlidingTabPagerAdapter;
import com.netease.nim.ccsocial_nim.main.fragment.MainTabFragment;
import com.netease.nim.ccsocial_nim.main.model.MainTab;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

public class MainTabPagerAdapter extends SlidingTabPagerAdapter {

    @Override
    public int getCacheCount() {
        return MainTab.values().length;
    }

    public MainTabPagerAdapter(FragmentManager fm, Context context, ViewPager pager) {
        super(fm, MainTab.values().length, context.getApplicationContext(), pager);
        for (MainTab tab : MainTab.values()) {
            try {
                MainTabFragment fragment = null;
                List<Fragment> fs = fm.getFragments();
                if (fs != null) {
                    for (Fragment f : fs) {
                        if (f.getClass() == tab.clazz) {
                            fragment = (MainTabFragment) f;
                            break;
                        }
                    }
                }
                if (fragment == null) {
                    fragment = tab.clazz.newInstance();
                }
                fragment.setState(this);
                fragment.attachTabData(tab);
                fragments[tab.tabIndex] = fragment;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getCount() {
        return MainTab.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        MainTab tab = MainTab.fromTabIndex(position);
        int resId = tab != null ? tab.resId : 0;
        return resId != 0 ? context.getText(resId) : "";
    }

}