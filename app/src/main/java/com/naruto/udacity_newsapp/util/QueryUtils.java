package com.naruto.udacity_newsapp.util;
/*
 * Created with Android Studio.
 * User: narutonbm@gmail.com
 * Date: 2017-01-04
 * Time: 0:07
 * Desc: UdaCity_NewsApp
 */

import android.text.TextUtils;
import android.util.Log;

import com.naruto.udacity_newsapp.bean.NewsDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

	private static final String LOG_TAG = QueryUtils.class.getSimpleName();

	/**
	 * 空参构造函数
	 */
	private QueryUtils() {
	}

	/**
	 * 查询卫报数据，并返回NewsDetail类型的数组
	 */
	public static List<NewsDetail> fetchEarthquakeData(String requestUrl) {
		// 创建url对象
		URL url = null;
		try {
			url = new URL(requestUrl);
		} catch (MalformedURLException e) {
			Log.e(LOG_TAG, "Problem building the URL ", e);
		}

		// 发出url请求，并返回jsonResponse
		String jsonResponse = null;
		try {
			jsonResponse = makeHttpRequest(url);
		} catch (IOException e) {
			Log.e(LOG_TAG, "Http请求出错！", e);
		}

		// 解析jsonResponse，得到NewsDetail数组
		List<NewsDetail> newsDetails;
		newsDetails = extractFeatureFromJson(jsonResponse);

		// 返回NewsDetail数组
		return newsDetails;
	}

	/**
	 * 向指定url发出http请求，以String类型返回一个jsonResponse
	 */
	private static String makeHttpRequest(URL url) throws IOException {
		String jsonResponse = "";

		// url为空，返回null
		if (url == null) {
			return jsonResponse;
		}

		HttpURLConnection urlConnection = null;
		InputStream inputStream = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setReadTimeout(10000);
			urlConnection.setConnectTimeout(15000);
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();

			// 响应码==200，读取输入流。得到jsonResponse
			if (urlConnection.getResponseCode() == 200) {
				inputStream = urlConnection.getInputStream();
				jsonResponse = readFromStream(inputStream);
			} else {
				Log.e(LOG_TAG, "错误响应码：" + urlConnection.getResponseCode());
			}
		} catch (IOException e) {
			Log.e(LOG_TAG, "读取输入流异常!", e);
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			if (inputStream != null) {
				// 关闭流
				inputStream.close();
			}
		}
		return jsonResponse;
	}

	/**
	 * 从输入流中读取数据
	 * 
	 * @param inputStream
	 *            输入流
	 * @return 返回String类型的jsonResponse
	 * @throws IOException
	 *             读取流过程中的IO异常
	 */
	private static String readFromStream(InputStream inputStream) throws IOException {
		// 创建StringBuilder对象
		StringBuilder output = new StringBuilder();
		// 输入流不为空，读取流中的信息
		if (inputStream != null) {
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String line = reader.readLine();
			while (line != null) {
				output.append(line);
				line = reader.readLine();
			}
		}

		return output.toString();
	}

	/**
	 * 从返回的jsonResponse中解析，获得NewsDetail数组
	 * 
	 * @param newsDetailJSON
	 *            返回的jsonresponse
	 * @return 得到的NewsDetail数组
	 */
	private static List<NewsDetail> extractFeatureFromJson(String newsDetailJSON) {
		// jsonresponse为空，返回null
		if (TextUtils.isEmpty(newsDetailJSON)) {
			return null;
		}

		// 创建NewsDetail数组
		List<NewsDetail> newsDetails = new ArrayList<>();

		try {
			// 创建jsonobject对象
			JSONObject baseJsonResponse = new JSONObject(newsDetailJSON);
			// 从json对象中得到
			JSONObject responseObject = baseJsonResponse.getJSONObject("response");
			// 得到response节点下的内容
			JSONArray resultsArray = responseObject.getJSONArray("results");
			// 遍历循环resultsArray
			for (int i = 0; i < resultsArray.length(); i ++) {
				// 得到单条result
				JSONObject newsResult = resultsArray.getJSONObject(i);
				// 得到webPublicationDate
				String newsWebPublicationDate = newsResult.getString("webPublicationDate").replace("Z", " ").replace("T", " ");
				// 得到webTitle
				String newsWebTitle = newsResult.getString("webTitle");
				// 得到webUrl
				String newsWebUrl = newsResult.getString("webUrl");
				// 创建NewsDetail
				NewsDetail newsDetail = new NewsDetail(newsWebPublicationDate, newsWebTitle, newsWebUrl);

				// 将创建的newsDetail添加到数组中去
				newsDetails.add(newsDetail);
			}

		} catch (JSONException e) {
			Log.e("QueryUtils", "解析json异常", e);
		}

		// 返回NewsDetail数组
		return newsDetails;
	}
}