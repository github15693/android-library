package com.innoria.khanhduong.library;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.innoria.khanhduong.library.Example.ExampleCropImageActivity;
import com.innoria.khanhduong.library.Example.ExampleOkhttpActivity;
import com.soundcloud.android.crop.Crop;

import java.io.File;


public class MainActivity extends ActionBarActivity {


    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        String[] arrList = {"okhttp", "Crop image"};
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrList);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (arrayAdapter.getItem(position)){
                    case "okhttp":{
                        Intent okhttpIntent = new Intent(getBaseContext(), ExampleOkhttpActivity.class);
                        startActivity(okhttpIntent);
                        break;
                    }
                    case "Crop image":{
                        Intent cropImage = new Intent(getBaseContext(), ExampleCropImageActivity.class);
                        startActivity(cropImage);
                        break;
                    }
                }
            }
        });



    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
