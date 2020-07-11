package com.example.asus.organization2.Activity.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.asus.organization2.R;
import com.example.asus.organization2.Message.Message_Local;
import com.example.asus.organization2.Message.Message_SharedPreferences;
import com.mob.MobSDK;
import com.mob.OperationCallback;


/**
  *  第一步缓冲，用于登录前的缓冲，读取sharepreferences中的文件，如果状态为已登录则直接跳转到第二步缓冲，否则则先登录
 **/
public class Activity_Load extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        Message_SharedPreferences.loadMessage(Activity_Load.this);
        submitPrivacyGrantResult(true);
        if(Message_Local.loginStatus==1){

            intent = new Intent(Activity_Load.this, Activity_Load2.class);
        }else{
            intent = new Intent(Activity_Load.this,Activity_Login.class);
        }
        finish();
        startActivity(intent);
    }
    private void submitPrivacyGrantResult(boolean granted) {
        MobSDK.submitPolicyGrantResult(granted, new OperationCallback<Void>() {
            @Override
            public void onComplete(Void data) {
                Log.d("dmj", "隐私协议授权结果提交：成功");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("dmj", "隐私协议授权结果提交：失败");
            }
        });
    }

}
