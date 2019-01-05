package com.tech.wd.ffecommerceproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity implements IView{

    @BindView(R.id.register_edit_phone)   //手机号
    TextView mTextView_phone;

    @BindView(R.id.register_edit_password)     //密码
    TextView mTextView_pwd;

    private IPrecenterImpl mIPrecenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        mIPrecenter = new IPrecenterImpl(this);
    }

    @OnClick(R.id.btn_register)
    public void onRegisterButtonClickListener() {  //注册按钮监听
        String phone = mTextView_phone.getText().toString().trim();
        String pwd = mTextView_pwd.getText().toString().trim();

        if (phone.isEmpty()||pwd.isEmpty()){

            Toast.makeText(RegisterActivity.this,"输入不能为空!",Toast.LENGTH_SHORT).show();
        }else {
            Map<String, String> map = new HashMap<>();
            map.put("phone",phone);
            map.put("pwd",pwd);

            mIPrecenter.startRequestData(Apis.URL_REGISTER,map, RegsiterBean.class,0,null,null);
        }
    }

    @OnClick(R.id.register_had)
    public void onTextView_registerClickListener(){
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
    }

    @Override
    public void showData(Object data) {
        RegsiterBean regsiterBean = (RegsiterBean) data;
        String message = regsiterBean.getMessage();
        if (message.equals("注册成功")){
            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        }else {
            Toast.makeText(RegisterActivity.this,"注册失败,已存在此用户!",Toast.LENGTH_SHORT).show();
        }
    }
}
