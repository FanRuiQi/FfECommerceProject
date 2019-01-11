package com.tech.wd.ffecommerceproject.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tech.wd.ffecommerceproject.Apis;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.netutil.HttpUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InformationActivity extends BaseActivity {
    //组件
    private SimpleDraweeView information_simple_headpic;
    private TextView information_text_name;
    private TextView information_text_phone;
    SharedPreferences mSpf;//全局的存储数据的SharedPreferences
    String path = Environment.getExternalStorageState()+"/image.png";


    public void initView() {//初始化  View
        information_simple_headpic = (SimpleDraweeView) findViewById(R.id.information_simple_headpic);
        information_text_name = (TextView) findViewById(R.id.information_text_name);
        information_text_phone = (TextView) findViewById(R.id.information_text_phone);

        mSpf = this.getSharedPreferences("sp", MODE_PRIVATE);

        /*initView();
        initData();
        */
        information_simple_headpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改头像
                Update_HeadIcon();

            }
        });
        information_text_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改昵称
                Updata_Name();
            }
        });

        //修改密码
        information_text_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update_Pwd();
            }
        });
    }
    //该密码
    //两个连环的弹框   确定旧密码后输入新密码
    private void Update_Pwd() {
        final EditText oldPwd = new EditText(InformationActivity.this);
        oldPwd.setHint("输入旧密码");
        Dialog dialog = new AlertDialog.Builder(InformationActivity.this)
                .setTitle("输入旧密码")
                .setView(oldPwd)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final EditText newPwd = new EditText(InformationActivity.this);
                                newPwd.setHint("输入新密码");
                                Dialog dialog1 = new AlertDialog.Builder(InformationActivity.this)
                                        .setTitle("输入新密码")
                                        .setView(newPwd)
                                        .setPositiveButton("确定",
                                                new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        UpdatePwd(oldPwd, newPwd);

                                                    }
                                                })
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                Toast.makeText(InformationActivity.this, "您已取消操作",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }).create();
                                dialog1.show();
                            }
                        })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(InformationActivity.this, "您已取消操作", Toast.LENGTH_LONG).show();
                    }
                }).create();
        dialog.show();
    }
    //改昵称
    private void Updata_Name() {
        //修改昵称
        final EditText et = new EditText(InformationActivity.this);
        Dialog dialog = new AlertDialog.Builder(InformationActivity.this)
                .setTitle("设置昵称")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Map<String, String> map = new HashMap<>();
                        map.put("nickName", et.getText().toString());
                        String sessionId = mSpf.getString("sessionId", "");
                        String userId = mSpf.getString("userId", null);
                        if (TextUtils.isEmpty(et.getText().toString())) {
                            Toast.makeText(InformationActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        HttpUtil.getInterface().doHeadPut(Apis.URL_UPDATE_PASSWORD, map, userId, sessionId, new HttpUtil.HttpUtilInterface() {
                            @Override
                            public void HttpFailure() {

                            }

                            @Override
                            public void HttpResponse(String json) {

                                if (json.equals("{\"message\":\"修改成功\",\"status\":\"0000\"}")) {
                                    Toast.makeText(InformationActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor edit = mSpf.edit();
                                    edit.putString("nickName", et.getText().toString());
                                    edit.commit();
                                    information_text_name.setText(et.getText().toString());
                                } else if (json.equals("{\"message\":\"该昵称已存在\",\"status\":\"0000\"}")) {
                                    Toast.makeText(InformationActivity.this, "该昵称已存在", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(InformationActivity.this, "您的账号已在异地登陆", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(InformationActivity.this, "您已取消操作", Toast.LENGTH_LONG).show();
                    }
                }).create();
        dialog.show();
    }
    //上传头像
    private void Update_HeadIcon() {
        Dialog dialog = new AlertDialog.Builder(InformationActivity.this)
                .setTitle("选择新头像")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(InformationActivity.this, "您已取消操作", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("打开相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(InformationActivity.this, "打开相册", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 1000);
                    }
                })
                .setNeutralButton("启动相机", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(InformationActivity.this, "启动相机", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                        startActivityForResult(intent, 3000);
                    }
                }).create();
        dialog.show();
    }
    //修改密码的逻辑
    private void UpdatePwd(final EditText oldPwd, final EditText newPwd) {

        Map<String, String> map = new HashMap<>();
        //获取到两个输入框的内容后进行网络请求
        map.put("oldPwd", oldPwd.getText().toString());
        map.put("newPwd", newPwd.getText().toString());
        String sessionId = mSpf.getString("sessionId", "");
        String userId = mSpf.getString("userId", null);
        if (TextUtils.isEmpty(oldPwd.getText().toString()) || TextUtils.isEmpty(oldPwd.getText().toString())) {
            Toast.makeText(InformationActivity.this, "两个都不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        HttpUtil.getInterface().doHeadPut(Apis.URL_UPDATE_PASSWORD, map, userId, sessionId, new HttpUtil.HttpUtilInterface() {
            @Override
            public void HttpFailure() {

            }

            @Override
            public void HttpResponse(String json) {

                if (json.equals("{\"message\":\"修改成功\",\"status\":\"0000\"}")) {
                    Toast.makeText(InformationActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor edit = mSpf.edit();
                    edit.putString("pwd", newPwd.getText().toString());
                    edit.commit();
                    information_text_name.setText(oldPwd.getText().toString());
                } else if (json.equals("{\"message\":\"修改失敗\",\"status\":\"1001\"}")) {
                    Toast.makeText(InformationActivity.this, "密码填写错误", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InformationActivity.this, "您的账号已在异地登陆", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void initData() {
        //Unable to start activity ComponentInfo
        initView();
        Date date = new Date();
        long time = date.getTime();
        path= Environment.getExternalStorageDirectory()+"a_"+time+".jpg";
        //if (mSpf!=null){
        information_simple_headpic.setImageURI(mSpf.getString("HeadPic", "http://img3.imgtn.bdimg.com/it/u=2519824424,1132423651&fm=26&gp=0.jpg"));
        information_text_name.setText(mSpf.getString("nickName", ""));
        information_text_phone.setText(mSpf.getString("phone", ""));
        //}
    }

    @Override
    public int getContent() {
        return R.layout.activity_informention;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1000 && resultCode == RESULT_OK){
            try{
                Uri uri = data.getData();//相册的相片裁剪
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(uri, "image/*");

                intent.putExtra("crop", true);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 250);
                intent.putExtra("outputY", 250);
                intent.putExtra("return-data", true);

                startActivityForResult(intent, 2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(requestCode == 2000 && resultCode == RESULT_OK){
            //上传头像  暂时youbug
            Bitmap bitmap = data.getParcelableExtra("data");

            String defaultPath = getApplicationContext().getFilesDir()
                    .getAbsolutePath() + "/defaultGoodInfo";
            File file = new File(defaultPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String defaultImgPath = defaultPath + "/messageImg.jpg";
            file = new File(defaultImgPath);
            try {
                file.createNewFile();
                FileOutputStream fOut = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 20, fOut);
                fOut.flush();
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*mPersenter.postHeadImage(AllUrl.UPDATE_HEADIMAGE_URL,file,
                    ""+mSpf.getInt("userId",0),
                    mSpf.getString("sessionId", ""), new MyPLoginInterface() {
                        @Override
                        public void HttpFailure() {
                            Toast.makeText(InformationActivity.this,"HttpFailure",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void HttpResponse(String json) {
                            Toast.makeText(InformationActivity.this,"HttpResponse"+json,Toast.LENGTH_SHORT).show();
                        }
                    });*/
        }

        if(requestCode == 3000 && resultCode == RESULT_OK){
            //相机的照片截取
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");

            intent.putExtra("crop", true);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 250);
            intent.putExtra("outputY", 250);
            intent.putExtra("return-data", true);

            startActivityForResult(intent, 2000);
        }
    }

}
