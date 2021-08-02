package study.cc15.test.ui.main;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import study.cc15.test.mainfragments.Fragment1;
import study.cc15.test.mainfragments.Fragment2;
import study.cc15.test.mainfragments.Fragment3;
import study.cc15.test.mainfragments.Fragment4;
import study.cc15.test.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position) {
            case 0:
                fragment = new Fragment1(mContext);
                break;
            case 1:
                fragment = new Fragment2(mContext);
                break;
            case 2:
                fragment = new Fragment3(mContext);
                break;
            case 3:
                fragment = new Fragment4(mContext);
                break;
            default:
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}