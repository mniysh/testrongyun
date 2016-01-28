package com.example.yanwei.testrongyun.service;

import android.app.Activity;
import android.text.TextUtils;


import com.example.yanwei.testrongyun.MyApplication;
import com.example.yanwei.testrongyun.bean.User;
import com.example.yanwei.testrongyun.utils.L;
import com.example.yanwei.testrongyun.utils.NetUtils;
import com.example.yanwei.testrongyun.utils.T;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 */
public class DataAccessUtil {
    private static final String TAG = "DataAccessUtil";

    private static AsyncHttpClient mClient;
    private static void initAsyncHttpClient() {
        if (mClient == null) {
            mClient = new AsyncHttpClient();
            //修改超时时间为15秒
            mClient.setTimeout(15000);
            mClient.setResponseTimeout(15000);
        }
    }
    public static  RequestHandle getToken(String userId, String name,String  portraitUri,AsyncHttpResponseHandler asyncHttpResponseHandler){
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        params.put("name", name);
        params.put("portraitUri", portraitUri);
        return doPost(RequestUrl.getToken, params, asyncHttpResponseHandler);
    }

    public static RequestHandle doPost(String url, RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {

        if (!NetUtils.isConnected(MyApplication.getInstance())) {
            T.show("网络异常");
            return null;
        }
        initAsyncHttpClient();
        if (params == null) {
            params = new RequestParams();
        }
//        addCommonParams(params);
        L.d(TAG, "doPost: " + url + "?" + params.toString());
        return mClient.post(url, params, asyncHttpResponseHandler);
    }



    public static RequestHandle doGet(String url, RequestParams params, AsyncHttpResponseHandler
        asyncHttpResponseHandler) {

        if (!NetUtils.isConnected(MyApplication.getInstance())) {
            T.show("网络异常");
            return null;
        }
        initAsyncHttpClient();
        if (params == null) {
            params = new RequestParams();
        }
//        params = addCommonParams(params);
        L.d(TAG, "doGet Url : " + url + "?" + params.toString());
        return mClient.get(url, params, asyncHttpResponseHandler);

    }

    /**
     * 取消所有请求
     */
    public static void cancelAllRequests() {
        if (null != mClient)
            mClient.cancelAllRequests(true);
    }




    /**
     * 上传图片:Post方式
//     */
//    private static RequestHandle upLoadImage(String url, File imageFile, AsyncHttpResponseHandler
//        asyncHttpResponseHandler) {
//
//        if (!NetUtils.isConnected(MyApplication.getInstance())) {
//            T.show("网络异常,请检查网络连接");
//            return null;
//        }
//        initAsyncHttpClient();
//        RequestParams params = new RequestParams();
//        try {
//            params.put("image", imageFile, "image/png");
//            params = addCommonParams(params);
//            mClient.post(url, params, asyncHttpResponseHandler);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            L.d(TAG, "upLoadImage：文件不存在");
//        }
//        return null;
//    }

//    private static RequestParams addCommonParams(RequestParams params) {
//        User user = MyApplication.getInstance().getUser();
//        if (null != user) {
//
//            String id = user.getUserId();
//            String app_token = user.getToken();
//            params.put("id", id);
//            params.put("app_token", app_token);
//            params.put("os", "android");
//        }
//
//        return params;
//    }

//    /**
//     * 获取包含有id和app_token的图片Url
//     * @param url
//     * @return
//     */
//    public static String getImageUrl(String url) {
//        User user = MyApplication.getInstance().getUser();
//        if (null != user) {
//            String id = user.getId();
//            String app_token = user.getApp_token();
//            url = url + "?id=" + id + "&app_token=" + app_token;
//            return url;
//        }
//        return url;
//    }
}  
