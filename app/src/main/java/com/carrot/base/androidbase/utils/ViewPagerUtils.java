package com.carrot.base.androidbase.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by victor on 9/4/16.
 */
public class ViewPagerUtils {

    private ViewPagerUtils() {}

    /**
     * Find fragment in certain position of viewpager.
     *
     * @param fragmentManager
     * @param viewPager
     * @param position
     * @return The Fragment if found or null otherwise.
     */
    public static Fragment findFragment(FragmentManager fragmentManager, ViewPager viewPager,
                                        int position) {
        return fragmentManager.findFragmentByTag(makeFragmentName(viewPager.getId(), position));
    }

    private static String makeFragmentName(long viewPagerId, int position) {
        return "android:switcher:" + viewPagerId + ":" + position;
    }

    /**
     * Find fragment in certain position of viewpager.
     *
     * @param adapter {@link android.support.v4.app.FragmentStatePagerAdapter} of this ViewPager
     * @param viewPager
     * @param position
     * @return The Fragment if found or null otherwise.
     */
    public static Object findFragment(FragmentStatePagerAdapter adapter, ViewPager viewPager,
                                      int position) {
        return adapter.instantiateItem(viewPager, position);
    }

}
