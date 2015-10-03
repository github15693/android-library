package com.innoria.khanhduong.library.Example;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView text;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_okhttp);
        text = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeGet();
            }
        });
    }

    private void executeGet(){
        KBaseApi mKBaseApi = new KBaseApi();
        mKBaseApi.setOnExecuteApiListener(new OnExecuteApiListener() {
            @Override
            public void onFailure(ApiResult apiResult) {
                super.onFailure(apiResult);
            }

            @Override
            public void onSuccess(ApiResult apiResult) {
                super.onSuccess(apiResult);
            }

            @Override
            public void onNotFound(ApiResult apiResult) {
                super.onNotFound(apiResult);
            }
        });
        HashMap<String, String> paramteters = new HashMap<String, String>();
        mKBaseApi.execute(KBaseApi.GET, "http://demo.innoria.com/allerpal-test/api/children/get_list_shared_by_child/format/json", paramteters);
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
