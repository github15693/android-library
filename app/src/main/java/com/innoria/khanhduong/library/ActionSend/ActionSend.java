package com.innoria.khanhduong.library.ActionSend;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Created by KhanhDuong on 8/13/2015.
 * Author Khanh Duong
 * Email khanhduong@innoria.com
 * Company www.innoria.com
 */
public class ActionSend {

    //show list action can send data (not commplete to use)
    public static void startChooserSend(Context ctx){
        try {
            String[] TO = {"khanhduong@innoria.com"};
            String[] CC = {""};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
            Intent chooserIntent = Intent.createChooser(emailIntent, "Action Send");
            chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(chooserIntent);
        }
        catch (android.content.ActivityNotFoundException ex) {
            Log.d("startChooserSend", "startChooserSend " + ex.getMessage());
        }
    }

    //show list action send email can attach (not commplete to use)
    public static void startSendEmail(Context ctx){
        String[] TO = {"khanhduong@innoria.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        // set the type to 'email', settype must below setdata
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email Subject");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
        Intent chooserIntent = Intent.createChooser(emailIntent, "Action Send");
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(chooserIntent);
    }

    //show list action send email not attach
    public static void startSendEmailNotAttachment(Context ctx, String[] mailTo, String[] cc, String subject, String message){
        String[] TO = mailTo;
        String[] CC = cc;
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(emailIntent);
    }
}
