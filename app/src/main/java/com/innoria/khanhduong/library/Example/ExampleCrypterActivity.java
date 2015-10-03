package com.innoria.khanhduong.library.Example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.innoria.khanhduong.library.R;
import com.scottyab.aescrypt.AESCrypt;

public class ExampleCrypterActivity extends AppCompatActivity {

    private EditText et_key, et_data_encode, et_data_decode;
    private Button btn_encode, btn_decode;
    private TextView tc_rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_crypter);

        et_key = (EditText) findViewById(R.id.et_key);
        et_data_encode = (EditText) findViewById(R.id.et_data_encode);
        et_data_decode = (EditText) findViewById(R.id.et_data_decode);
        btn_encode = (Button) findViewById(R.id.btn_encode);
        btn_decode = (Button) findViewById(R.id.btn_decode);
        tc_rs = (TextView) findViewById(R.id.tc_rs);

        btn_encode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String password = et_key.getText().toString().trim();
                    String message = et_data_encode.getText().toString().trim();
                    String encrypted = AESCrypt.encrypt(password, message);
                    et_data_decode.setText(encrypted);
                    tc_rs.setText(encrypted);
                    Log.d("DEBUG", "encrypted: " + encrypted);
                } catch (Exception e) {
                    e.printStackTrace();
                    tc_rs.setText(e.getMessage());
                }
            }
        });

        btn_decode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String password = et_key.getText().toString().trim();
                    String encryptedMsg = et_data_decode.getText().toString().trim();
                    String decrypted = AESCrypt.decrypt(password, encryptedMsg);
                    tc_rs.setText(decrypted);
                    Log.d("DEBUG", "decrypted: " + decrypted);
                } catch (Exception e) {
                    e.printStackTrace();
                    tc_rs.setText(e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_example_crypter, menu);
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
