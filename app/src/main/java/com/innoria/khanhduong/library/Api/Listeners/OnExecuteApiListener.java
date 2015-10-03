package com.innoria.khanhduong.library.Api.Listeners;

import com.innoria.khanhduong.library.Api.ApiResult;

/**
 * Created by Khanh Duong on 10/3/2015.
 * Email: khanhduong@innoria.com
 */
public abstract class OnExecuteApiListener{
    public void onFailure(ApiResult apiResult){};
    public void onSuccess(ApiResult apiResult){};
    public void onNotAuthorization(ApiResult apiResult){};
    public void onNotFound(ApiResult apiResult){};
}