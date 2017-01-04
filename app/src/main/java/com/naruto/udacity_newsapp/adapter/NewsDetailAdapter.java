package com.naruto.udacity_newsapp.adapter;
/*
 * Created with Android Studio.
 * User: narutonbm@gmail.com
 * Date: 2017-01-04
 * Time: 14:20
 * Desc: UdaCity_NewsApp
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naruto.udacity_newsapp.R;
import com.naruto.udacity_newsapp.bean.NewsDetail;

import java.util.ArrayList;

public class NewsDetailAdapter extends Adapter<NewsDetailAdapter.NewsDetailViewHolder> implements View.OnClickListener {

	private Context mContext;
	private ArrayList<NewsDetail> mNewsDetails;
	private OnRecyclerViewItemClickListener mOnItemClickListener = null;
	private NewsDetailViewHolder mHolder;

	/**
	 * 构造函数
	 *
	 * @param context
	 *            上下文环境
	 * @param newsDetails
	 *            newsDetail数组
	 */
	public NewsDetailAdapter(Context context, ArrayList<NewsDetail> newsDetails) {
		mContext = context;
		mNewsDetails = newsDetails;
	}

	@Override
	public NewsDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// 为viewholder构造view
		View view;
		view = LayoutInflater.from(mContext).inflate(R.layout.item_news_detail, parent, false);
		// 将创建的View注册点击事件
		view.setOnClickListener(this);
		mHolder = new NewsDetailViewHolder(view);

		// 返回viewHolder
		return mHolder;
	}

	@Override
	public void onBindViewHolder(NewsDetailViewHolder holder, int position) {
		NewsDetail newsDetail = mNewsDetails.get(position);
		holder.tv_news_title.setText(newsDetail.getTitle());
		holder.tv_news_date.setText(newsDetail.getPubDate());
	}

	@Override
	public int getItemCount() {

		// 返回条目数目
		return mNewsDetails.size();
	}

	static class NewsDetailViewHolder extends ViewHolder {

		// 创建控件对象
		private TextView tv_news_title;
		private TextView tv_news_date;

		NewsDetailViewHolder(View itemView) {
			super(itemView);
			// 找到控件
			tv_news_title = (TextView) itemView.findViewById(R.id.tv_news_title);
			tv_news_date = (TextView) itemView.findViewById(R.id.tv_news_date);
		}
	}

	// 定义接口
	public interface OnRecyclerViewItemClickListener {
		void onItemClick(View view, int position);
	}

	public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
		this.mOnItemClickListener = listener;
	}

	@Override
	public void onClick(View v) {
		if (mOnItemClickListener != null) {
			// 注意这里使用getTag方法获取数据
			mOnItemClickListener.onItemClick(v, mHolder.getAdapterPosition());
		}
	}
}