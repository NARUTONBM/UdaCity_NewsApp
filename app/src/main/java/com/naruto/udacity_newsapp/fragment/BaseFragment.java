package com.naruto.udacity_newsapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.naruto.udacity_newsapp.R;
import com.naruto.udacity_newsapp.adapter.NewsDetailAdapter;
import com.naruto.udacity_newsapp.bean.NewsDetail;
import com.naruto.udacity_newsapp.loader.NewsDetailLoader;
import com.naruto.udacity_newsapp.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class BaseFragment extends Fragment {

	private static final int NEWSDETAIL_LOADER_ID = 1;
	private ArrayList<NewsDetail> mNewsDetails;
	private NewsDetailAdapter mAdapter;

	public BaseFragment() {
		// 空参构造函数
	}

	@Nullable
	public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState,
					final String TheGuardianQueryURL) {
		// 创建fragment view对象
		View view = inflater.inflate(R.layout.fragment_news, container, false);
		// 找到recyclerview
		RecyclerView rv_news = (RecyclerView) view.findViewById(R.id.rv_news);
		// 找到进度条控件
		final ProgressBar loading_indicator = (ProgressBar) view.findViewById(R.id.loading_indicator);
		// 找到用于空view的textview
		TextView empty_view = (TextView) view.findViewById(R.id.empty_view);
		// 设置layoutmanager
		rv_news.setLayoutManager(new LinearLayoutManager(getActivity()));
		// 创建空的newsdetail数组
		mNewsDetails = new ArrayList<>();
		// 创建适配器
		mAdapter = new NewsDetailAdapter(getContext(), mNewsDetails);
		// 将适配器设置给recyclerView
		rv_news.setAdapter(mAdapter);

		// 创建连接管理者
		ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		// 得到当前的网络状态
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		// 如果有网络连接，采取发出请求，获取数据
		if (networkInfo != null && networkInfo.isConnected()) {
			// 得到loader管理者对象
			LoaderManager loaderManager = getLoaderManager();

			// 初始化loader
			loaderManager.initLoader(NEWSDETAIL_LOADER_ID, null, new LoaderManager.LoaderCallbacks<List<NewsDetail>>() {
				@Override
				public Loader<List<NewsDetail>> onCreateLoader(int id, Bundle args) {
					return new NewsDetailLoader(getContext(), TheGuardianQueryURL);
				}

				@Override
				public void onLoadFinished(Loader<List<NewsDetail>> loader, List<NewsDetail> newsDetails) {
					mNewsDetails.clear();
					if (newsDetails != null && !newsDetails.isEmpty()) {
						mNewsDetails.addAll(newsDetails);
						mAdapter.notifyDataSetChanged();
						// 加载完成，隐藏进度条
						loading_indicator.setVisibility(View.GONE);
					}
				}

				@Override
				public void onLoaderReset(Loader<List<NewsDetail>> loader) {
					mNewsDetails.clear();
				}
			});
		} else {
			// 无网络状况下，取消进度条，显示无网络连接的textview
			loading_indicator.setVisibility(View.GONE);
			empty_view.setText(R.string.no_internet_connection);
		}
		//设置分割线
		rv_news.addItemDecoration(new RecycleViewDivider(
				getContext(), LinearLayoutManager.HORIZONTAL, 4, getResources().getColor(R.color.colorAccent)));
		// 设置recyclerView的条目点击事件
		mAdapter.setOnItemClickListener(new NewsDetailAdapter.OnRecyclerViewItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(mNewsDetails.get(position).getUrl()));
				startActivity(intent);
			}
		});

		return view;
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
}