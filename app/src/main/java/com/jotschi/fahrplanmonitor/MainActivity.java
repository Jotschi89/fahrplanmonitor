package com.jotschi.fahrplanmonitor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENTS = 2;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<Fragment>();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Create fragments.
        fragments.add(FRAGMENT_ONE, new AbfahrtsmonitorFragment());
        fragments.add(FRAGMENT_TWO, new WetterFragment());

        // Setup the fragments, defining the number of fragments, the screens and titles.
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()){
            @Override
            public int getCount() {
                return FRAGMENTS;
            }
            @Override
            public Fragment getItem(final int position) {
                return fragments.get(position);
            }
            @Override
            public CharSequence getPageTitle(final int position) {
                switch (position) {
                    case FRAGMENT_ONE:
                        return "Abfahrtsmonitor";
                    case FRAGMENT_TWO:
                        return "Wetter";
                    default:
                        return null;
                }
            }
        };
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(fragmentPagerAdapter);
    }
}
