package com.example.ahmedsaleh.dbse;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Ahmed Saleh on 4/13/2017.
 * Custom Adapter Class that extend from FragmentPagerAdapter Class to hold the fragments to be shown by view pager
 * @extend FragmentPagerAdapter
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new UniFragment();
        } else if (position == 1){
            return new InstitutesFragment();
        } else {
            return new AcademiesFragment();
        }


    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Universities";
            case 1:
                return "Instituties";
            case 2:
                return "Academic";
            default:
                return null;
        }
    }
}
