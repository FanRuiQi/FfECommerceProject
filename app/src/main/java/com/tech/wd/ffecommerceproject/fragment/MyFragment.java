package com.tech.wd.ffecommerceproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.activity.FootPrintActivity;
import com.tech.wd.ffecommerceproject.activity.InformationActivity;
import com.tech.wd.ffecommerceproject.activity.MineAddressShowActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFragment extends BaseFragment{  //我的页面


    @Override
    public void initData(View view) {
        ButterKnife.bind(this,view);
    }

    @OnClick(R.id.mine_layout_Footprint)
    public void onFootPrintClickListener(){

        startActivity(new Intent(getActivity(), FootPrintActivity.class));
    }

    @OnClick(R.id.mine_layout_Information)
    public void onInforClickListener(){

        startActivity(new Intent(getActivity(), InformationActivity.class));
    }

    @OnClick(R.id.mine_layout_Address)
    public void onAddressClickListener(){

        startActivity(new Intent(getActivity(), MineAddressShowActivity.class));
    }

    @Override
    public int getContent() {
        return R.layout.fragment_my;
    }
}
