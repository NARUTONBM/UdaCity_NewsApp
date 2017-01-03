package com.naruto.udacity_newsapp.adapter;
/*
 * Created with Android Studio.
 * User: narutonbm@gmail.com
 * Date: 2017-01-03
 * Time: 16:58
 * Desc: UdaLearn
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.naruto.udacity_newsapp.R;
import com.naruto.udacity_newsapp.fragment.ArtsFragment;
import com.naruto.udacity_newsapp.fragment.BusinessFragment;
import com.naruto.udacity_newsapp.fragment.EnvironmentFragment;
import com.naruto.udacity_newsapp.fragment.FashionFragment;
import com.naruto.udacity_newsapp.fragment.LifestyleFragment;
import com.naruto.udacity_newsapp.fragment.OpinionFragment;
import com.naruto.udacity_newsapp.fragment.PoliticsFragment;
import com.naruto.udacity_newsapp.fragment.SoccerFragment;
import com.naruto.udacity_newsapp.fragment.SportsFragment;
import com.naruto.udacity_newsapp.fragment.TechFragment;
import com.naruto.udacity_newsapp.fragment.TravelFragment;
import com.naruto.udacity_newsapp.fragment.WorldFragment;

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new PoliticsFragment();
                break;

            case 1:
                fragment = new WorldFragment();
                break;

            case 2:
                fragment = new OpinionFragment();
                break;

            case 3:
                fragment = new SportsFragment();
                break;

            case 4:
                fragment = new SoccerFragment();
                break;

            case 5:
                fragment = new TechFragment();
                break;

            case 6:
                fragment = new ArtsFragment();
                break;

            case 7:
                fragment = new LifestyleFragment();
                break;

            case 8:
                fragment = new FashionFragment();
                break;

            case 9:
                fragment = new BusinessFragment();
                break;

            case 10:
                fragment = new TravelFragment();
                break;

            case 11:
                fragment = new EnvironmentFragment();
                break;

            default:
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String pageTitle = null;
        switch (position) {
            case 0:
                pageTitle = mContext.getString(R.string.category_politics);
                break;

            case 1:
                pageTitle = mContext.getString(R.string.category_world);
                break;

            case 2:
                pageTitle = mContext.getString(R.string.category_opinion);
                break;

            case 3:
                pageTitle = mContext.getString(R.string.category_sports);
                break;

            case 4:
                pageTitle = mContext.getString(R.string.category_soccer);
                break;

            case 5:
                pageTitle = mContext.getString(R.string.category_tech);
                break;

            case 6:
                pageTitle = mContext.getString(R.string.category_arts);
                break;

            case 7:
                pageTitle = mContext.getString(R.string.category_lifestyle);
                break;

            case 8:
                pageTitle = mContext.getString(R.string.category_fashion);
                break;

            case 9:
                pageTitle = mContext.getString(R.string.category_business);
                break;

            case 10:
                pageTitle = mContext.getString(R.string.category_travel);
                break;

            case 11:
                pageTitle = mContext.getString(R.string.category_environment);
                break;

            default:
                break;
        }

        return pageTitle;
    }
}