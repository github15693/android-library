package com.innoria.khanhduong.library.Example;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.innoria.khanhduong.library.HttpClient.ApiResult;
import com.innoria.khanhduong.library.HttpClient.KBaseHttpClient;
import com.innoria.khanhduong.library.Progress.KBaseProgress;
import com.innoria.khanhduong.library.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;

public class ExampleOkhttpActivity extends ActionBarActivity {
    private final KBaseHttpClient kBaseHttpClient = new KBaseHttpClient();
    private TextView text;
    private Button btn;
    private KBaseProgress mKBaseProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_okhttp);
        text = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
        mKBaseProgress = new KBaseProgress();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                KBaseProgress.circleWaiting(ExampleOkhttpActivity.this);
//                ApiResult apiResult = callGetApiWithParameters();
//                text.setText(apiResult.getData());
//                KBaseProgress.dismiss();
                callGetApiWithParametersynTask();
            }
        });
    }

    public ApiResult callGetApiWithParameters() {
        Toast.makeText(getBaseContext(), "call run", Toast.LENGTH_SHORT).show();
        String rs = "";
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("user_id", "6");
        parameters.put("child_id", "2");
        kBaseHttpClient.builderGetRequest("http://demo.innoria.com/allerpal/api/children/accept_share/format/json", parameters);

        return kBaseHttpClient.execute();
    }
    public void callGetApiWithParametersynTask() {
        mKBaseProgress.circleWaiting(ExampleOkhttpActivity.this);
        Toast.makeText(getBaseContext(), "start call api", Toast.LENGTH_SHORT).show();
        String rs = "";
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("user_id", "6");
        parameters.put("child_id", "2");
        kBaseHttpClient.builderGetRequest("http://demo.innoria.com/allerpal/api/children/accept_share/format/json", parameters);

        kBaseHttpClient.getmCall().enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mKBaseProgress.dismiss();
                Toast.makeText(getBaseContext(), "call api onFailure", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mKBaseProgress.dismiss();
                Toast.makeText(getBaseContext(), "call api onResponse", Toast.LENGTH_SHORT).show();
            }
        });
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
