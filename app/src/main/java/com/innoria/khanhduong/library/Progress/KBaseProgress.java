package com.innoria.khanhduong.library.Progress;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.innoria.khanhduong.library.R;

import java.util.zip.Inflater;


/**
 * Created by KhanhDuong on 8/11/2015.
 * Author Khanh Duong
 * Email khanhduong@innoria.com
 * Company www.innoria.com
 */
public class KBaseProgress {
    private static Dialog progressDialog;
    private static boolean isCancelTouchOutside = false, isCancelable = false;

    public static Dialog circleWaiting(Context context){
        progressDialog = new Dialog(context);
        progressDialog.setCanceledOnTouchOutside(isCancelTouchOutside);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progress_circle_waiting);
        progressDialog.setCancelable(isCancelable);
        progressDialog.show();
        return progressDialog;
    }

    public static void dismiss(){
        if(progressDialog != null)
            progressDialog.dismiss();
    }
}
