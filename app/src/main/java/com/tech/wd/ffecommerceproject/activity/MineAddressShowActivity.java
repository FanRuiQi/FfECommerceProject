package com.tech.wd.ffecommerceproject.activity;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.tech.wd.ffecommerceproject.Apis;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.adapter.MineAddressAdapter;
import com.tech.wd.ffecommerceproject.bean.MyAddressBean;
import com.tech.wd.ffecommerceproject.precenter.IPrecenterImpl;
import com.tech.wd.ffecommerceproject.view.IView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineAddressShowActivity extends BaseActivity implements IView{//我的收货地址列表


    @BindView(R.id.mine_address_rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.mine_address_text_finish)
    TextView mTextView;

    @BindView(R.id.mine_btn_addAddress)
    Button mButton;
    private IPrecenterImpl mIPrecenter;
    private String mSessionId;
    private String mUserId;

    @Override
    public void initData() {
        ButterKnife.bind(this);
        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
        mSessionId = sp.getString("sessionId", null);
        mUserId = sp.getString("userId", null);
        mIPrecenter = new IPrecenterImpl(this);
        requestData();
    }

    private void requestData() {

        mIPrecenter.startRequestData(Apis.URL_ADDRESS_SHOW,null, MyAddressBean.class,2,mUserId,mSessionId);
    }

    @Override
    public int getContent() {
        return R.layout.activity_mine_address;
    }

    @Override
    public void showData(Object data) {

        MyAddressBean myAddressBean = (MyAddressBean) data;
        List<MyAddressBean.ResultBean> result = myAddressBean.getResult();
        MineAddressAdapter addressAdapter = new MineAddressAdapter(MineAddressShowActivity.this, result);

        mRecyclerView.setAdapter(addressAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MineAddressShowActivity.this,LinearLayoutManager.VERTICAL,false));

        addressAdapter.setOnItemClickListener(new MineAddressAdapter.onItemClickListener() {
            @Override
            public void onRadioButtonClick(int id) {

            }

            @Override
            public void onButtonDelete(int layoutPosition) {

            }

            @Override
            public void onButtonUpdate(int id) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIPrecenter.onDetach();
    }
}
