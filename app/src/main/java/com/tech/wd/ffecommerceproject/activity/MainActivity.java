package com.tech.wd.ffecommerceproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tech.wd.ffecommerceproject.Apis;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.bean.LoginBean;
import com.tech.wd.ffecommerceproject.bean.RegsiterBean;
import com.tech.wd.ffecommerceproject.precenter.IPrecenterImpl;
import com.tech.wd.ffecommerceproject.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity implements IView{  //登录页面
    @BindView(R.id.login_edit_phone)   //手机号
    TextView mTextView_phone;

    @BindView(R.id.login_edit_pwd)     //密码
    TextView mTextView_pwd;

    @BindView(R.id.login_checkbox_rememberPwd)  //记住密码
    CheckBox mCheckBox_rememberPwd;

    @BindView(R.id.login_text_register)    //注册
    TextView mTextView_register;

    @BindView(R.id.login_btn_login)          //登录按钮
    Button mButton_login;
    private boolean b=true;
    private IPrecenterImpl mIPrecenter;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mIPrecenter = new IPrecenterImpl(this);

        mPreferences = getSharedPreferences("sp", MODE_PRIVATE);

        boolean check = mPreferences.getBoolean("check", false);
        String String_phone = mPreferences.getString("phone", null);
        String String_pwd = mPreferences.getString("pwd", null);
        if (check){
            mTextView_phone.setText(String_phone);
            mTextView_pwd.setText(String_pwd);
            mCheckBox_rememberPwd.setChecked(true);
        }
    }

    @OnClick(R.id.login_btn_login)
    public void onLoginButtonClickListener(){  //登录按钮监听

        String phone = mTextView_phone.getText().toString().trim();
        String pwd = mTextView_pwd.getText().toString().trim();

        if (phone.isEmpty()||pwd.isEmpty()){

            Toast.makeText(MainActivity.this,"输入不能为空!",Toast.LENGTH_SHORT).show();
        }else {



            Map<String, String> map = new HashMap<>();
            map.put("phone",phone);
            map.put("pwd",pwd);

            mIPrecenter.startRequestData(Apis.URL_LOGIN,map, LoginBean.class,0,null,null);
        }
    }


    @OnClick(R.id.login_text_register)
    public void onTextView_registerClickListener(){
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
    }

    @OnClick(R.id.login_text_login)
    public void onTextView_LoginClickListener(){
        startActivity(new Intent(MainActivity.this,TotalActivity.class));
    }

    @OnTouch(R.id.login_img_show)            //明文密文
    public boolean onTouch(View v, MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mTextView_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
            case MotionEvent.ACTION_UP:
                mTextView_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    break;

        }
        return true;
    }



    @Override
    protected void onDestroy() {  //避免内存泄漏
        super.onDestroy();
        mIPrecenter.onDetach();
    }

    @Override
    public void showData(Object data) {   //请求返回的数据

       LoginBean loginBean = (LoginBean) data;
        String message = loginBean.getMessage();
        if (message.equals("登录成功")){
            if (mCheckBox_rememberPwd.isChecked()){  //保存账号密码
                SharedPreferences.Editor edit = mPreferences.edit();
                edit.putString("phone",mTextView_phone.getText().toString().trim());
                edit.putString("pwd",mTextView_pwd.getText().toString().trim());
                edit.putBoolean("check",true);
                edit.commit();

            }else {

            }
            LoginBean.ResultBean beanResult = loginBean.getResult();
            String sessionId = beanResult.getSessionId();

            SharedPreferences.Editor edit = mPreferences.edit();  //保存用户的sessionId
            edit.putString("sessionId",sessionId);
            edit.commit();

            startActivity(new Intent(MainActivity.this,TotalActivity.class));
            //Toast.makeText(MainActivity.this,"登录成功!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this,"登录失败!",Toast.LENGTH_SHORT).show();
        }
    }
}
