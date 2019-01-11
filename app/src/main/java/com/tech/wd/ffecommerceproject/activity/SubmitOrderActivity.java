package com.tech.wd.ffecommerceproject.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.adapter.ShopCatAdapter;
import com.tech.wd.ffecommerceproject.adapter.SubmitOrderAdapter;
import com.tech.wd.ffecommerceproject.bean.ShowShopCatBean;
import com.tech.wd.ffecommerceproject.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubmitOrderActivity extends BaseActivity implements IView{  //提交订单页

    @BindView(R.id.submitOrder_rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.submit_text_allmoney)
    TextView mTextView_num_allMoney;
    private List<ShowShopCatBean.ResultBean> mListData;

    @Override
    public void initData() {

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        SubmitOrderAdapter submitOrderAdapter = new SubmitOrderAdapter(SubmitOrderActivity.this, mListData);
        mRecyclerView.setAdapter(submitOrderAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(SubmitOrderActivity.this,LinearLayoutManager.VERTICAL,false));


    }

    @Override
    public int getContent() {
        return R.layout.activity_submitorder;
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void message(List<ShowShopCatBean.ResultBean> mlist){
        mListData= mlist;
    }


    @Override
    public void showData(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
