package com.example.yanwei.testrongyun.service;

import android.text.TextUtils;


;
import com.example.yanwei.testrongyun.bean.User;
import com.example.yanwei.testrongyun.exception.ResponseException;
import com.example.yanwei.testrongyun.utils.L;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Iterator;


/**

 */
public class DataParseUtil {
    private static final String TAG = "DataParseUtil";
    public static boolean modifyPassword(JSONObject jsonObject) throws ResponseException {

        return processDataResult(jsonObject);
    }

    public static boolean modifyPhone(JSONObject jsonObject) throws ResponseException {

        return processDataResult(jsonObject);
    }
    public static User getToken(JSONObject jsonObject)throws ResponseException{
        String a = processDataStr(jsonObject);
        Gson gson = new Gson();
        User user = gson.fromJson(a, User.class);
        return user;
    }


    /**
     * 通用解析方法， 判断请求是否成功
     *
     * @param jsonObject
     * @return
     * @throws ResponseException
     */
    public static boolean processDataResult(JSONObject jsonObject) throws ResponseException {
        if (null == jsonObject) {
            L.d(TAG, "processDataResult: json对象为null");
            return false;
        }

        String code = jsonObject.optString("code");
        L.d("xxx", jsonObject.optString("code"));
        String message = jsonObject.optString("message");
        if (TextUtils.equals("200", code)) {        //数据正确
            return true;
        } else {
            processData(jsonObject);

        }

        return false;
    }

    public static String processDataStr(JSONObject jsonObject) throws ResponseException {
        if (null == jsonObject) {
            L.d(TAG, "processData: json对象为null");
            return null;
        }

        String code = jsonObject.optString("code");
        if (TextUtils.equals("200", code)) {        //数据正确
            String data = jsonObject.optString("data");
            if (TextUtils.isEmpty(data)) {
                return null;
            }else {
                return data;
            }
        } else {
            processData(jsonObject);
        }

        return null;
    }

    /**
     * Json数据解析  返回正确的JSONObject
     *
     * @param jsonObject
     * @return
     * @throws ResponseException 抛出异常：code message
     */
    public static JSONObject processData(JSONObject jsonObject) throws ResponseException {

        String code = jsonObject.optString("code");
        String message = jsonObject.optString("message");
        if (TextUtils.equals("200", code)) {        //数据正确
            return jsonObject.optJSONObject("data");
        } else {
            String dataStr = jsonObject.optString("data");
            String errorMessage = "";
            if (!TextUtils.isEmpty(dataStr) && !TextUtils.equals("null", dataStr) && !TextUtils.equals("NULL", dataStr)) {
                JSONObject data = jsonObject.optJSONObject("data");
                if (data != null) {
                    Iterator<String> keys = data.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        errorMessage = data.optString(next);
                    }
                }
            }

            if (TextUtils.isEmpty(errorMessage)) {
                throw new ResponseException(code, message);
            } else {
                throw new ResponseException(code, errorMessage);
            }
        }

    }
}
