package com.naruto.udacity_newsapp.fragment;
/*
 * Created with Android Studio.
 * User: narutonbm@gmail.com
 * Date: 2017-01-03
 * Time: 17:15
 * Desc: UdaLearn
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PoliticsFragment extends BaseFragment {

	public PoliticsFragment() {
        //空参构造函数
    }

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		String theGuardianQueryURL = "http://content.guardianapis.com/search?tag=politics%2Fpolitics&api-key=test";
		return super.onCreateView(inflater, container, savedInstanceState, theGuardianQueryURL);
	}
}