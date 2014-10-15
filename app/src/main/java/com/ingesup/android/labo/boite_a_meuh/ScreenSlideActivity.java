package com.ingesup.android.labo.boite_a_meuh;

import android.app.*;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

/**
 * Created by Mathea on 15/10/2014.
 */
public class ScreenSlideActivity extends FragmentActivity {
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meuh);

        // Instantiate a ViewPager and a PagerAdapter.
        ArrayList<SlideFragment> liste = new ArrayList<SlideFragment>();
            liste.add(new SlideFragment(R.drawable.minion));
            liste.add(new SlideFragment(R.drawable.vache));
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), liste);
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<SlideFragment> liste;

        public ScreenSlidePagerAdapter(FragmentManager fm, ArrayList<SlideFragment> list) {
            super(fm);
            this.liste = list;
        }

        @Override
        public Fragment getItem(int position) {
            return liste.get(position);
        }

        @Override
        public int getCount() {
            return liste.size();
        }
    }
}
