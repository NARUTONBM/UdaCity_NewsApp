package com.naruto.udacity_newsapp.fragment;
/*
 * Created with Android Studio.
 * User: narutonbm@gmail.com
 * Date: 2017-01-03
 * Time: 17:16
 * Desc: UdaLearn
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OpinionFragment extends BaseFragment {

    public OpinionFragment() {
        //空参构造函数
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String theGuardianQueryURL = "http://content.guardianapis.com/search?tag=commentisfree%2Fcommentisfree&api-key=test";
        return super.onCreateView(inflater, container, savedInstanceState, theGuardianQueryURL);
    }
}