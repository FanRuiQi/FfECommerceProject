package com.tech.wd.ffecommerceproject.activity;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.tech.wd.ffecommerceproject.Apis;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.adapter.FootPrintAdapter;
import com.tech.wd.ffecommerceproject.bean.FootPrintBean;
import com.tech.wd.ffecommerceproject.precenter.IPrecenterImpl;
import com.tech.wd.ffecommerceproject.view.IView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootPrintActivity extends BaseActivity implements IView{

    @BindView(R.id.footPrint_rv)
    RecyclerView mRecyclerView;
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

        mIPrecenter.startRequestData(Apis.URL_MY_FOOTS,null, FootPrintBean.class,2,mUserId,mSessionId);
    }

    @Override
    public int getContent() {
        return R.layout.activity_footprint;
    }

    @Override
    public void showData(Object data) {

        FootPrintBean footPrintBean = (FootPrintBean) data;
        List<FootPrintBean.ResultBean> result = footPrintBean.getResult();
        FootPrintAdapter footPrintAdapter = new FootPrintAdapter(FootPrintActivity.this, result);

        mRecyclerView.setAdapter(footPrintAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIPrecenter.onDetach();
    }
}
