package com.innoria.khanhduong.library.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;

import com.innoria.khanhduong.library.Systems.Contacts.Contact;
import com.innoria.khanhduong.library.Systems.Contacts.EmailContact;
import com.innoria.khanhduong.library.Systems.Contacts.PhoneContact;
import com.scottyab.aescrypt.AESCrypt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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

    public static float convertDpToPixel(Context context, float dp){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static float convertPixelsToDp(Context context, float px){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    /***
     * get list contact of devices
     * @param ctx
     * @return
     */
    public static List<Contact> getContacts(Context ctx){
        List<Contact> contacts = new ArrayList<Contact>();
        ContentResolver cr = ctx.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(id));
                contact.setName(name);
                if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    List<PhoneContact> phoneContacts = new ArrayList<PhoneContact>();
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String type = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
//                        Log.d("DEBUG", "CONTACT==> " + "id: " + id + "Name: " + name + ", PhoneContact No: " + phoneNo + ", Type: " + type);
                        PhoneContact phoneContact = new PhoneContact();
                        phoneContact.setPhone(phoneNo);
                        phoneContact.setType(Integer.parseInt(type));
                        phoneContacts.add(phoneContact);
                    }
                    contact.setPhoneContacts(phoneContacts);
                    pCur.close();

                    Cursor eCur = cr.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    List<EmailContact> emailContacts = new ArrayList<EmailContact>();
                    while (eCur.moveToNext()) {
                        String email = eCur.getString(eCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        String type = eCur.getString(eCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
//                        Log.d("DEBUG", "CONTACT==> "+ "id: " + id + "Name: " + name + ", Email: " + email + ", Type: " + type);
                        EmailContact emailContact = new EmailContact();
                        emailContact.setEmail(email);
                        emailContact.setType(Integer.parseInt(type));
                        emailContacts.add(emailContact);
                    }
                    contact.setEmailContacts(emailContacts);
                    eCur.close();
                    contacts.add(contact);
                }
            }
        }
        return contacts;
    }

    private static final String PASSWORD_ENCRYPT = "KLibrary151693";
    private static String encrypt(String data){
        String encrypted = "";
        try {
            encrypted =  AESCrypt.decrypt(PASSWORD_ENCRYPT, data);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return encrypted;
    }

    private static String decrypt(String encryptedData) {
        String decrypted = "";
        try {
            decrypted = AESCrypt.decrypt(PASSWORD_ENCRYPT, encryptedData);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return decrypted;
    }

    /***
     *
     * @param image
     * @param maxSize
     * @return
     */
    public static Bitmap scaleRatioImage(Bitmap image, int maxSize) {
        if(image == null)
            return null;
        int width = image.getWidth();
        int height = image.getHeight();
        Bitmap bp = image;
        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        bp = Bitmap.createScaledBitmap(image, width, height, true);
        return bp;
    }
}
