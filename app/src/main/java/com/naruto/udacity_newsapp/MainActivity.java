package com.naruto.udacity_newsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.naruto.udacity_newsapp.adapter.CategoryAdapter;

public class MainActivity extends AppCompatActivity {

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;
		// 初始化UI
		initUI();
	}

	private void initUI() {
		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (mToolbar != null) {
			mToolbar.setTitle("NewsApp");
		}
		setSupportActionBar(mToolbar);
		// 找到控件
		ViewPager vp_main = (ViewPager) findViewById(R.id.vp_main);
		TabLayout tl_main = (TabLayout) findViewById(R.id.tl_main);
		// 创建适配器
		CategoryAdapter adapter = new CategoryAdapter(mContext, getSupportFragmentManager());
		if (vp_main != null) {
			vp_main.setAdapter(adapter);
		}
		// 将tl和vp关联起来
		if (tl_main != null) {
			tl_main.setupWithViewPager(vp_main);
		}
	}
}