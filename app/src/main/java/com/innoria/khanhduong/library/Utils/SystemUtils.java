package com.innoria.khanhduong.library.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.DisplayMetrics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KhanhDuong on 8/12/2015.
 * Author Khanh Duong
 * Email khanhduong@innoria.com
 * Company www.innoria.com
 */
public class SystemUtils {

    //save bitmap to file
    public static String saveBitmapToFile(Bitmap bp, String filename){
        String path = "";
        FileOutputStream out = null;
        try {
            path = filename;
            out = new FileOutputStream(filename);
            bp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    //convert bitmap to be64
    public static String bitmapToBase64(Bitmap bitmap){
        String output = "";
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            output = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
        catch (Exception e){
//            Log.e("Error", e.getMessage());
        }

        return output;
    }

    //get all item of array list object
    public ArrayList<Object> getAllItem(ArrayList<Object> objectArrayList){
        ArrayList<Object> objectList = new ArrayList<>();
        for (int i = 0; i < objectArrayList.size(); i++) {
            objectList.add(objectArrayList.get(i));
        }
        return objectList;
    }

    //convert jsonArryay to array list
    public ArrayList<Object> jsonArrayToArrayList(JSONArray jsonArray) throws JSONException {
        ArrayList<Object> arrayListData = new ArrayList<Object>();
        for (int i=0; i<jsonArray.length(); i++) {
            arrayListData.add(jsonArray.get(i));
        }
        return arrayListData;
    }

    //format number phone from text input
    public String formatPhoneNumberUS(String phone){
        if (phone.length() == 0)
            return "";
        phone = phone.replace("-", "");
        String _phone = "";
        for (int i = 0; i < phone.length(); i++) {
            _phone += String.valueOf(phone.charAt(i));
            if(i%2 == 0 && i != phone.length()-1)
                _phone += "-";
        }
        return _phone;
    }

    public static String stringToMD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static boolean isValidEmail(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        // onClick of button perform this simplest code.
        if (email.matches(emailPattern2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isValidPassword(String pwd){
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";  //((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(pwd);
        return matcher.matches();
    }

    public static boolean isAvailableNetWork(Context thiss){
        ConnectivityManager conMgr = (ConnectivityManager) thiss.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;
        return true;
    }

    public JSONObject stringToJson(String json_string){
        try {
            return new JSONObject(json_string);
        } catch (JSONException e) {
            return null;
        }
    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}
