package com.tech.wd.ffecommerceproject.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tech.wd.ffecommerceproject.Apis;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.adapter.CircleListAdapter;
import com.tech.wd.ffecommerceproject.bean.CircleBean;
import com.tech.wd.ffecommerceproject.precenter.IPrecenterImpl;
import com.tech.wd.ffecommerceproject.view.IView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleFragment extends BaseFragment implements IView{

    @BindView(R.id.fragment_circle_rv)
    RecyclerView mRecyclerView;
    private IPrecenterImpl mIPrecenter;
    private String mSsionId;

    private Boolean bo=true;

    @Override
    public void initData(View view) {

        ButterKnife.bind(this,view);

        mIPrecenter = new IPrecenterImpl(this);

        SharedPreferences sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        mSsionId = sp.getString("sessionId", "");
        requestData();
    }

    private void requestData() {

        HashMap<String, String> map = new HashMap<>();
        mIPrecenter.startRequestData(Apis.URL_CIRCLE_SHOW_LIST, map,CircleBean.class,2,"615",mSsionId);

    }

    @Override
    public int getContent() {
        return R.layout.fragment_circle;
    }

    @Override
    public void showData(Object data) {

        CircleBean circleBean = (CircleBean) data;
        List<CircleBean.ResultBean> result = circleBean.getResult();
        final CircleListAdapter listAdapter = new CircleListAdapter(getActivity(), result);
        mRecyclerView.setAdapter(listAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        listAdapter.setOnImgClickListener(new CircleListAdapter.OnImgClickListener() {  //得到圈子ID
            @Override
            public void onImgClick(int id,int great) {

                if (great==2){
                    Map<String, String> map = new HashMap<>();
                    map.put("circleId",id+"");
                    Toast.makeText(getActivity(),id+"",Toast.LENGTH_SHORT).show();

                    mIPrecenter.startRequestData(Apis.URL_CIRCLE_DZ,map,null,3,"615",mSsionId);
                    mIPrecenter.startRequestData(Apis.URL_CIRCLE_SHOW_LIST, map,CircleBean.class,2,"615",mSsionId);

                }else {

                    Map<String, String> map1 = new HashMap<>();
                    map1.put("circleId",id+"");
                    Toast.makeText(getActivity(),id+"",Toast.LENGTH_SHORT).show();

                    mIPrecenter.startRequestData(Apis.URL_CIRCLE_QZ_DZ,map1,null,4,"615",mSsionId);
                    mIPrecenter.startRequestData(Apis.URL_CIRCLE_SHOW_LIST, map1,CircleBean.class,2,"615",mSsionId);

                }

            }
        });
    }
}
