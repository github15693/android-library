package com.innoria.khanhduong.library.HttpClient;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by KhanhDuong on 8/12/2015.
 * Author Khanh Duong
 * Email khanhduong@innoria.com
 * Company www.innoria.com
 */
public interface IApiAsyn {
    public void onFailure(Request request, IOException e);
    public void onResponse(Response response);
}
