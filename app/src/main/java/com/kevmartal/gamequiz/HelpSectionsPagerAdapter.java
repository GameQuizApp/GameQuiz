package com.kevmartal.gamequiz;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class HelpSectionsPagerAdapter extends FragmentPagerAdapter {

    //    @StringRes
//    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    private final Context mContext;

    public HelpSectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
//        return PlaceholderFragment.newInstance(position + 1);
        switch (position) {
            case 0:
                return new Hoja1();
            case 1:
                return new Hoja2();
            case 2:
                return new Hoja3();
            default:
                return null;

//                return int 0;
//                return new PlaceholderFragment();

        }
    }


//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
////        return mContext.getResources().getString(TAB_TITLES[position]);
//    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}