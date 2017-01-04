package com.naruto.udacity_newsapp.bean;
/*
 * Created with Android Studio.
 * User: narutonbm@gmail.com
 * Date: 2017-01-03
 * Time: 21:02
 * Desc: UdaCity_NewsApp
 */

public class NewsDetail {

    //创建参数
    private final String mPubDate;
    private final String mTitle;
    private final String mUrl;

    //构造函数
    public NewsDetail(String pubDate, String title, String url) {
        mPubDate = pubDate;
        mTitle = title;
        mUrl = url;
    }

    //get方法
    public String getPubDate() {
        return mPubDate;
    }

    //get方法
    public String getTitle() {
        return mTitle;
    }

    //get方法
    public String getUrl() {
        return mUrl;
    }
}