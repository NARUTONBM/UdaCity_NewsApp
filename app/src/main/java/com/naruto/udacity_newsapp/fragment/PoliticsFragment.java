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
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naruto.udacity_newsapp.R;

public class PoliticsFragment extends Fragment {

    public PoliticsFragment() {
        //空参构造函数
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建fragment view对象
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        //找到recyclerview
        RecyclerView rv_news = (RecyclerView) view.findViewById(R.id.rv_news);
        //设置layoutmanager
        rv_news.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
