package com.tech.wd.ffecommerceproject.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.wd.ffecommerceproject.Apis;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.activity.SubmitOrderActivity;
import com.tech.wd.ffecommerceproject.adapter.ShopCatAdapter;
import com.tech.wd.ffecommerceproject.bean.ShowShopCatBean;
import com.tech.wd.ffecommerceproject.precenter.IPrecenterImpl;
import com.tech.wd.ffecommerceproject.view.IView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

public class StopstolleyFragment extends BaseFragment implements IView{

    @BindView(R.id.shoppingcar_recycle_show)
    RecyclerView mRecyclerView;

    @BindView(R.id.shoppingcar_checkbox_checkall)
    CheckBox mCheckBox_checkAll;

    @BindView(R.id.shoppingcar_text_allmoney)
    TextView mTextView_totalPrice;

    @BindView(R.id.shoppingcar_button_pay)
    Button mButton_js;
    private IPrecenterImpl mIPrecenter;

    private String mSessionId;
    private String mUserId;
    ShopCatAdapter shopCatAdapter;

    @Override
    public void initData(View view) {

        ButterKnife.bind(this,view);

        SharedPreferences sp = getActivity().getSharedPreferences("sp", MODE_PRIVATE);
        mSessionId = sp.getString("sessionId", null);
        mUserId = sp.getString("userId", null);
        mIPrecenter = new IPrecenterImpl(this);
        requestData();
    }

    private void requestData() {

        mIPrecenter.startRequestData(Apis.URL_SELECT_SHOP_CAT,null, ShowShopCatBean.class,2,mUserId,mSessionId);


    }

    @OnClick(R.id.shoppingcar_checkbox_checkall)
    public void onCheckBoxClick(){   //全选

        Boolean allSpSelectState = shopCatAdapter.getAllSpSelectState();
        shopCatAdapter.qxChangeAllSp(!allSpSelectState);
        shopCatAdapter.notifyDataSetChanged();
        refresh();

    }

    @OnClick(R.id.shoppingcar_button_pay)
    public void onBtnPayClickListener(){   //去结算

        Boolean spSelectIsSelected = shopCatAdapter.getSpSelectIsSelected();

        if (spSelectIsSelected){

            List<ShowShopCatBean.ResultBean> ListData = shopCatAdapter.getSpSelectedData();

            EventBus.getDefault().postSticky(ListData);
            startActivity(new Intent(getActivity(),SubmitOrderActivity.class));

        }else {
            Toast.makeText(getActivity(), R.string.ts_SpIsSelected,Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public int getContent() {
        return R.layout.fragment_shopstolley;
    }

    private void refresh(){
        Boolean allSpSelectState = shopCatAdapter.getAllSpSelectState();
        mCheckBox_checkAll.setChecked(allSpSelectState);

        int totalPrice = shopCatAdapter.getTotalPrice();
        mTextView_totalPrice.setText("￥:"+totalPrice+".00");
    }

    @Override
    public void showData(Object data) {

        ShowShopCatBean showShopCatBean = (ShowShopCatBean) data;
        final List<ShowShopCatBean.ResultBean> result = showShopCatBean.getResult();
        shopCatAdapter = new ShopCatAdapter(getActivity(), result);
        mRecyclerView.setAdapter(shopCatAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        shopCatAdapter.setOnItemClickListener(new ShopCatAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int commodityId) {

            }

            @Override
            public void onCheckBoxClickListener(int position) {

                shopCatAdapter.changeSp(position);
                shopCatAdapter.notifyDataSetChanged();
                refresh();

            }

            @Override
            public void oJJChangeListener(int position,int number) {

                shopCatAdapter.ChangeNumber(position,number);
                shopCatAdapter.notifyDataSetChanged();
                refresh();
            }

            @Override
            public void onBtnDelete(int layoutPosition) {
                shopCatAdapter.deleteItem(layoutPosition);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIPrecenter.onDetach();
    }
}
