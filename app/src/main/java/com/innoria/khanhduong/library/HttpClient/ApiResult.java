package com.innoria.khanhduong.library.HttpClient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pika on 8/11/2015.
 */
public class ApiResult {
    private int code;
    private String message;
    private String data;

    public ApiResult(){
        this.code = 0;
        this.message = "";
        this.data = "";
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
