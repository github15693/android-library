package com.innoria.khanhduong.library.HttpClient;

import android.os.StrictMode;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by KhanhDuong on 8/11/2015.
 * Author: Khanh Duong
 * Email: khanhduong@innoria.com
 */
public class KBaseHttpClient extends OkHttpClient implements IApiAsyn{
    private OkHttpClient mOkHttpClient;
    private final int CONNECT_TIMEOUT = 30; //second
    private Request mRequest;
    private final String AUTH_NAME = "allerpal@gmail.com", AUTH_PASSWORD = "123456";
    private String mCredentials;
    private Call mCall;

    //initialize
    public KBaseHttpClient(){
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

    //builder get request
    public Request builderGetRequest(String url){
        mRequest = new Request.Builder().url(url).header("Authorization", mCredentials).build();
        return mRequest;
    }

    //builder get request with paramter
    public Request builderGetRequest(String url, HashMap<String, String> paramters){
        mRequest = new Request.Builder().url(compareQuery(url, paramters)).header("Authorization", mCredentials).build();
        return mRequest;
    }

    //execute
    public ApiResult execute(){
        Response response = null;
        ApiResult apiResult = new ApiResult();
        try {

            response = mOkHttpClient.newCall(mRequest).execute();
            apiResult = apiResult.convertFromJsonObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiResult;
    }

    //execute asyntask
    public void enqueue(){
        getmCall().enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }

    //repare query paramter
    public String compareQuery(String url, HashMap<String, String> paramters){
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
        return url;
    }

    public Request getmRequest() {
        return mRequest;
    }

    public Call getmCall() {
        mCall = mOkHttpClient.newCall(mRequest);
        return mCall;
    }

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) {

    }
}
