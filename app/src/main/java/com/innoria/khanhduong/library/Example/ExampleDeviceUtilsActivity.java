package com.innoria.khanhduong.library.Example;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.innoria.khanhduong.library.R;
import com.innoria.khanhduong.library.Utils.DeviceUtils;

public class ExampleDeviceUtilsActivity extends ActionBarActivity {
    private TextView tv_device_id, tv_device_name, tv_device_seri, tv_device_height, tv_device_width, tv_convert_px, tv_device_android_id, tv_device_info;
    private EditText et_convert_px;
    private Button btn_convert_px;
    private Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_device_utils);
        ctx = getBaseContext();

        tv_device_id = (TextView) findViewById(R.id.tv_device_id);
        tv_device_name = (TextView) findViewById(R.id.tv_device_name);
        tv_device_seri = (TextView) findViewById(R.id.tv_device_seri);
        tv_device_height = (TextView) findViewById(R.id.tv_device_height);
        tv_device_width = (TextView) findViewById(R.id.tv_device_width);
        tv_convert_px = (TextView) findViewById(R.id.tv_convert_px);
        tv_device_android_id = (TextView) findViewById(R.id.tv_device_android_id);
        tv_device_info = (TextView) findViewById(R.id.tv_device_info);
        et_convert_px = (EditText) findViewById(R.id.et_convert_px);
        btn_convert_px = (Button) findViewById(R.id.btn_convert_px);

        tv_device_id.setText("Device id: " + String.valueOf(DeviceUtils.getDeviceId(ctx)));
        tv_device_name.setText("Device name: " + String.valueOf(DeviceUtils.getDeviceName(ctx)));
        tv_device_seri.setText("Device seri: " + String.valueOf(DeviceUtils.getDeviceSerial(ctx)));
        tv_device_height.setText("Screen height: " + String.valueOf(DeviceUtils.getScreenHeight(ctx)));
        tv_device_width.setText("Screen width: " + String.valueOf(DeviceUtils.getScreenWidth(ctx)));
        tv_device_android_id.setText("Android id: " + String.valueOf(DeviceUtils.getAndroidId(ctx)));
        tv_device_info.setText(
                "===Start show device information===\n"
                + "DEVICE=" + Build.DEVICE + "\n"
                + "MODEL=" + Build.MODEL + "\n"
                + "PRODUCT=" + Build.PRODUCT + "\n"
                + "DISPLAY=" + Build.DISPLAY + "\n"
                + "BRAND=" + Build.BRAND + "\n"
                + "CPU_ABI=" + Build.CPU_ABI + "\n"
                + "BOARD=" + Build.BOARD + "\n"
                + "SDK INT=" + Build.VERSION.SDK_INT + "\n"
                + "SDK RELEASE=" + Build.VERSION.RELEASE + "\n"
                + "SDK CODENAME=" + Build.VERSION.CODENAME + "\n"
                + "SDK INCREMENTAL=" + Build.VERSION.INCREMENTAL + "\n"
                + "===End show device information==="
        );

        ChangeValueConvert(Integer.parseInt(et_convert_px.getText().toString().trim()));

        btn_convert_px.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeValueConvert(Integer.parseInt(et_convert_px.getText().toString().trim()));
            }
        });
    }

    private void ChangeValueConvert(int dp){
        tv_convert_px.setText(String.valueOf(DeviceUtils.convertDpToPx(ctx, dp)) + " px");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_example_device_utils, menu);
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
