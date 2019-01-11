package com.tech.wd.ffecommerceproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.tech.wd.ffecommerceproject.Apis;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.bean.RegsiterBean;
import com.tech.wd.ffecommerceproject.indentfragment.CommentFragment;
import com.tech.wd.ffecommerceproject.indentfragment.FinishFragment;
import com.tech.wd.ffecommerceproject.precenter.IPrecenterImpl;
import com.tech.wd.ffecommerceproject.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishComment extends BaseActivity implements IView{
    @BindView(R.id.publish_edit)
    EditText mEditText;

    @BindView(R.id.publish_img_picture)
    ImageView mImageView;

    @BindView(R.id.publish_radioButton)
    RadioButton mRadioButton;
    private int mCommodityId;
    private String mOrderId;
    private String mSessionId;
    private String mUserId;
    private IPrecenterImpl mIPrecenter;


    @Override
    public void initData() {

        ButterKnife.bind(this);

        Intent intent = getIntent();

         mCommodityId = intent.getIntExtra("commodityId",0);
         mOrderId = intent.getStringExtra("orderId");
         mIPrecenter = new IPrecenterImpl(this);

        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
        mSessionId = sp.getString("sessionId", null);
        mUserId = sp.getString("userId", null);
    }



    @OnClick(R.id.publish_btn)
    public void onButtonClick(){  //去评论

        String trim = mEditText.getText().toString().trim();

        if (trim.isEmpty()){
            Toast.makeText(PublishComment.this,R.string.Is_Input_null,Toast.LENGTH_SHORT).show();
        }else {

            if (mRadioButton.isChecked()){

            }

            Map<String, String> map = new HashMap<>();
            map.put("commodityId",mCommodityId+"");
            map.put("orderId",mOrderId);
            map.put("content",trim);
            map.put("image","ff");
            mIPrecenter.startRequestData(Apis.URL_COMMENT,map, RegsiterBean.class,3,mUserId,mSessionId);
        }

    }

    @OnClick(R.id.publish_btn)
    public void onImgClick() {  //添加照片


    }

    @Override
    public int getContent() {
        return R.layout.activity_publishcomment;
    }

    @Override
    public void showData(Object data) {

        RegsiterBean regsiterBean = (RegsiterBean) data;
        if (regsiterBean.getMessage().equals("评论成功")){
            Toast.makeText(PublishComment.this,regsiterBean.getMessage(),Toast.LENGTH_SHORT).show();
            CommentFragment commentFragment = new CommentFragment();
            commentFragment.refreshCommentPage();

            FinishFragment finishFragment = new FinishFragment();
            finishFragment.requestData();
            finish();
        }else {
            Toast.makeText(PublishComment.this,regsiterBean.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIPrecenter.onDetach();
    }
}
