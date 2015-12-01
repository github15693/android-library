package com.innoria.khanhduong.library.BaseActivity;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.innoria.khanhduong.library.R;

/**
 * Created by Khanh Duong on 8/24/2015.
 * Author: khanhduong@innoria.com
 * PM: Khang Tran, khangtran@innoria.com
 * Company: Innoria Solution
 * Site: www.innoria.com
 */
public class KBaseActivity extends AppCompatActivity {

    private RelativeLayout layout_left, layout_center, layout_right;
    private TextView tv_title_left, tv_menu_title;
    private ImageView iv_backspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActionBar();
    }

    public void initializeActionBar(){
        //set actionbar custom
        ActionBar actionBar = getSupportActionBar();
        // add the custom view to the action bar
        actionBar.setCustomView(R.layout.action_bar_custom);
        //get object
        layout_left = (RelativeLayout) actionBar.getCustomView().findViewById(R.id.layout_left);
        layout_center = (RelativeLayout) actionBar.getCustomView().findViewById(R.id.layout_center);
        layout_right = (RelativeLayout) actionBar.getCustomView().findViewById(R.id.layout_right);
        iv_backspace = (ImageView) actionBar.getCustomView().findViewById(R.id.iv_backspace);
        tv_title_left = (TextView) actionBar.getCustomView().findViewById(R.id.tv_title_left);
        tv_menu_title = (TextView) actionBar.getCustomView().findViewById(R.id.tv_menu_title);
        //set event
        iv_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIconBackspaceClickListener();
            }
        });
        tv_menu_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuClickListener();
            }
        });
        //set show action bar custom
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        ActivityInfo activityInfo = null;
        try {
            activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String title = activityInfo.loadLabel(getPackageManager()).toString();
        setTitleLeft(title);
    }

    //    layout left
    public void setTitleLeft(String title){
        tv_title_left.setText(title);
        setVisibilityTitleLeft(View.VISIBLE);
    }


    public void setVisibilityIconBackspace(int visibility){
        iv_backspace.setVisibility(visibility);
    }

    public void setVisibilityTitleLeft(int visibility){
        tv_title_left.setVisibility(visibility);
    }

//    layout right
    public void setMenuTitle(String menuTitle){
        tv_menu_title.setText(menuTitle);
        setVisibilityMenuTitle(View.VISIBLE);
    }

    public void setVisibilityMenuTitle(int visibility){
        tv_menu_title.setVisibility(visibility);
    }

//    comon function
    public void setMenuEnable(boolean isEnable){
        tv_menu_title.setEnabled(isEnable);
        if(isEnable){
            tv_menu_title.setTextColor(getResources().getColor(R.color.text_color_white));
        }else{
            tv_menu_title.setTextColor(getResources().getColor(R.color.disable_color));
        }
    }

    protected void onMenuClickListener(){};
    protected void onIconBackspaceClickListener(){ finish(); };
}
