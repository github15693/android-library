package com.innoria.khanhduong.library.Example;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.innoria.khanhduong.library.CropImage.KBaseCropImage;
import com.innoria.khanhduong.library.R;
import com.soundcloud.android.crop.Crop;

public class ExampleCropImageActivity extends ActionBarActivity {
    private ImageView iv_rs;
    private Button btn_camera, btn_gallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_crop_image);
        iv_rs = (ImageView) findViewById(R.id.iv_rs);
        btn_camera = (Button) findViewById(R.id.btn_camera);
        btn_gallery = (Button) findViewById(R.id.btn_gallery);

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KBaseCropImage.openCamera(ExampleCropImageActivity.this);
            }
        });
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KBaseCropImage.openGallery(ExampleCropImageActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == KBaseCropImage.RESULT_GALLERY && resultCode == RESULT_OK
                    && null != data) {
                KBaseCropImage.startCropImageFromGallery(this, data);
            }
            if (requestCode == KBaseCropImage.RESULT_CAMERA && resultCode == RESULT_OK
                    && null != data) {
                KBaseCropImage.startCropImageFromCamera(this, data);
            }

            if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
                iv_rs.setImageBitmap(KBaseCropImage.getBitmapCroped(data));
            }
        } catch (Exception e) {

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_example_crop_image, menu);
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
