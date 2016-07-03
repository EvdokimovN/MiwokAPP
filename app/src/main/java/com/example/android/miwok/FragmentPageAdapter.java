package com.example.android.miwok;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by evdokimovn on 29.06.16.
 */
public class FragmentPageAdapter extends FragmentPagerAdapter {

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    private String tabTitles[] = new String[] { "Numbers", "Family", "Colors", "Phrases" };

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Fragment fragment = new BaseFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(MainActivity.KEY, R.string.category_numbers);
            fragment.setArguments(bundle);
            return fragment;
        } else if (position == 1){
            Fragment fragment = new BaseFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(MainActivity.KEY, R.string.category_family);
            fragment.setArguments(bundle);
            return fragment;
        } else if (position == 2){
            Fragment fragment = new BaseFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(MainActivity.KEY, R.string.category_colors);
            fragment.setArguments(bundle);
            return fragment;
        }
        else {
            Fragment fragment = new BaseFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(MainActivity.KEY, R.string.category_phrases);
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
