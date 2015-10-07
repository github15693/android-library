package com.innoria.khanhduong.library.Example;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.StringMap;
import com.innoria.khanhduong.library.Api.ApiResult;
import com.innoria.khanhduong.library.Api.KBaseApi;
import com.innoria.khanhduong.library.Api.Listeners.OnExecuteApiListener;
import com.innoria.khanhduong.library.Progress.KBaseProgress;
import com.innoria.khanhduong.library.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ExampleOkhttpActivity extends ActionBarActivity {
    private TextView tv_rs;
    private Button btn_get, btn_post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_okhttp);
        tv_rs = (TextView) findViewById(R.id.tv_rs);
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_post = (Button) findViewById(R.id.btn_post);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeGet();
            }
        });
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executePost();
            }
        });

    }

    private void showResult(final ApiResult apiResult){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_rs.setText(new Gson().toJson(apiResult));
            }
        });
    }

    private void executeGet(){
        KBaseApi mKBaseApi = new KBaseApi();
        mKBaseApi.setOnExecuteApiListener(new OnExecuteApiListener() {
            @Override
            public void onFailure(ApiResult apiResult) {
                super.onFailure(apiResult);
                showResult(apiResult);
            }

            @Override
            public void onSuccess(ApiResult apiResult) {
                super.onSuccess(apiResult);
                showResult(apiResult);
            }

            @Override
            public void onNotFound(final ApiResult apiResult) {
                super.onNotFound(apiResult);
                showResult(apiResult);
            }
        });
        HashMap<String, String> paramteters = new HashMap<String, String>();
        paramteters.put("child_id", "2");
        mKBaseApi.execute(KBaseApi.GET, "http://demo.innoria.com/allerpal-test/api/children/get_list_shared_by_child/format/json", paramteters);
    }
    private void executePost(){
        KBaseApi mKBaseApi = new KBaseApi();
        mKBaseApi.setOnExecuteApiListener(new OnExecuteApiListener() {
            @Override
            public void onFailure(ApiResult apiResult) {
                super.onFailure(apiResult);
                showResult(apiResult);
            }

            @Override
            public void onSuccess(ApiResult apiResult) {
                super.onSuccess(apiResult);
                showResult(apiResult);
            }

            @Override
            public void onNotFound(ApiResult apiResult) {
                super.onNotFound(apiResult);
                showResult(apiResult);
            }
        });
        HashMap<String, String> paramteters = new HashMap<String, String>();
        paramteters.put("user_id", "2");
        paramteters.put("child_id", "2");
        mKBaseApi.execute(KBaseApi.POST, "http://demo.innoria.com/allerpal-test/api/allergies/get_allergies/format/json", paramteters);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_example_okhttp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
