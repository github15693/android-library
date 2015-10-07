package com.innoria.khanhduong.library.Api;

import android.app.Activity;
import android.content.Entity;
import android.content.res.Resources;
import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.innoria.khanhduong.library.Api.Listeners.OnExecuteApiListener;
import com.innoria.khanhduong.library.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Khanh Duong on 10/2/2015.
 * Email: khanhduong@innoria.com
 */
public class KBaseApi {
    private OkHttpClient mOkHttpClient;
    private final int CONNECT_TIMEOUT = 30; //second
    private Request mRequest;
    private final String AUTH_NAME = "allerpal@gmail.com", AUTH_PASSWORD = "123456";
    private String mCredentials;
    private Call mCall;
    public static int GET = 0, POST = 1;
    private OnExecuteApiListener mOnExecuteApiListener;
    public static final MediaType JSON = MediaType.parse("multipart/form-data; charset=utf-8");

    public KBaseApi(){
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //set pilocy for api
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //get authentication
        mCredentials = Credentials.basic(AUTH_NAME, AUTH_PASSWORD);
    }

    public void execute(int method, String url, HashMap<String, String> paramters){
        if(method == GET){
            mRequest = new Request.Builder().url(compareQuery(url, paramters)).header("Authorization", mCredentials).build();
        }
        if(method == POST){
            MultipartBuilder requestBuilder = new MultipartBuilder();
            requestBuilder.type(MultipartBuilder.FORM);
            for (Map.Entry<String, String> entry : paramters.entrySet()) {
                requestBuilder.addFormDataPart(entry.getKey(), entry.getValue());
            }
            RequestBody requestBody = requestBuilder.build();
            mRequest = new Request.Builder().url(url).post(requestBody).header("Authorization", mCredentials).build();
        }
        mCall = mOkHttpClient.newCall(mRequest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                proccessCallApiFailure(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                proccessCallApiRespone(response);
            }
        });
    }

    //repare query paramter
    public String compareQuery(String url, HashMap<String, String> paramters){
        if(paramters.size() > 0){
            url += "?";
            int i = 0;
            for (Map.Entry<String, String> param : paramters.entrySet()) {
                i += 1;
                url += param.getKey();
                url += "=";
                url += param.getValue();
                if(i < paramters.size())
                    url += "&";
            }
        }
        return url;
    }

    //
    public void setOnExecuteApiListener(OnExecuteApiListener onExecuteApiListener){
        mOnExecuteApiListener = onExecuteApiListener;
    }

    private void proccessCallApiFailure(String errorMessage){
        ApiResult apiResult = new ApiResult();
        apiResult.setMessage(errorMessage);
        mOnExecuteApiListener.onFailure(apiResult);
    }

    private void proccessCallApiRespone(Response response){
        try {
            ApiResult apiResult = new ApiResult();
            apiResult.setCode(response.code());
            switch (response.code()){
                case 200:{
                    apiResult = new Gson().fromJson(response.body().string(), ApiResult.class);
                    mOnExecuteApiListener.onSuccess(apiResult);
                    break;
                }
                case 404:{
                    apiResult = new Gson().fromJson(response.body().string(), ApiResult.class);
                    mOnExecuteApiListener.onNotFound(apiResult);
                    break;
                }
                case 401:{
                    apiResult.setMessage(Resources.getSystem().getString(R.string.error_not_authorization));
                    mOnExecuteApiListener.onNotAuthorization(apiResult);
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
