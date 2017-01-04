package com.naruto.udacity_newsapp.loader;
/*
 * Created with Android Studio.
 * User: narutonbm@gmail.com
 * Date: 2017-01-03
 * Time: 21:08
 * Desc: UdaCity_NewsApp
 */

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.naruto.udacity_newsapp.bean.NewsDetail;
import com.naruto.udacity_newsapp.util.QueryUtils;

import java.util.List;

public class NewsDetailLoader extends AsyncTaskLoader<List<NewsDetail>> {

    private final String mUrl;
    private String LOG_TAG = NewsDetailLoader.class.getName();

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public NewsDetailLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsDetail> loadInBackground() {
        //url为空，返回null
        if (mUrl == null) {
            return null;
        }

        // 发出请求，并解析获取的返回值
        List<NewsDetail> newsDetails = QueryUtils.fetchEarthquakeData(mUrl);

        return newsDetails;
    }
}