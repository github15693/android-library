package com.innoria.khanhduong.library.CropImage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.innoria.khanhduong.library.Utils.SystemUtils;
import com.soundcloud.android.crop.Crop;

import java.io.File;

/**
 * Created by KhanhDuong on 8/12/2015.
 * Author Khanh Duong
 * Email khanhduong@innoria.com
 * Company www.innoria.com
 */
public class KBaseCropImage {
    public static final int RESULT_CAMERA = 1301;
    public static final int RESULT_GALLERY = 1302;

    //call new intent open camera
    public static void openCamera(Context ctx){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        ((Activity)ctx).startActivityForResult(intent, RESULT_CAMERA);
    }

    //call new intent open gallery
    public static void openGallery(Context ctx){
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        ((Activity)ctx).startActivityForResult(galleryIntent, RESULT_GALLERY);
    }

    //start crop image from camera
    public static void startCropImageFromCamera(Context ctx, Intent intentResult){
        Uri destination = Uri.fromFile(new File(ctx.getCacheDir(), "imageCroped"));
        Bitmap bitmap = (Bitmap) intentResult.getExtras().get("data");
        SystemUtils.saveBitmapToFile(bitmap, destination.getPath());
        Crop.of(destination, destination).asSquare().start((Activity) ctx);
    }
    //start crop image from gallery
    public static void startCropImageFromGallery(Context ctx, Intent intentResult){
        String imgDecodableString;
        Bitmap bitmap;
        Uri destination = Uri.fromFile(new File(ctx.getCacheDir(), "imageCroped"));
        Uri selectedImage = intentResult.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        // Get the cursor
        Cursor cursor = ctx.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);

        // Move to first row
        cursor.moveToLast();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        imgDecodableString = cursor.getString(columnIndex);
        cursor.close();

        // Set the Image in ImageView after decoding the String
        bitmap = BitmapFactory
                .decodeFile(imgDecodableString);
        SystemUtils.saveBitmapToFile(bitmap, destination.getPath());
        Crop.of(destination, destination).asSquare().start((Activity) ctx);
    }

    //get bitmap image
    public static Bitmap getBitmapCroped(Intent intentResult){
        return BitmapFactory.decodeFile(Crop.getOutput(intentResult).getPath());
    }
}
