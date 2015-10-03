package com.innoria.khanhduong.library.Api;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pika on 8/11/2015.
 */
public class ApiResult<T> {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private T data;
    @SerializedName("page")
    private int page;
    @SerializedName("totalPage")
    private int totalPage;
    @SerializedName("rowPerPage")
    private int rowPerPage;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResult convertFromJsonObject(String jsonObject){
        ApiResult result = new ApiResult();
        try {
            JSONObject data = new JSONObject(jsonObject);
            result.code = data.getInt(ApiKey.API_RESULT_CODE);
            result.message = data.getString(ApiKey.API_RESULT_MESSAGE);
            result.data = data.getString(ApiKey.API_RESULT_DATA);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
