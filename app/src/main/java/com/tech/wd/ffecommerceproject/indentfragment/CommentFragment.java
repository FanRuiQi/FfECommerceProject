package com.tech.wd.ffecommerceproject.indentfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.tech.wd.ffecommerceproject.Apis;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.activity.MainActivity;
import com.tech.wd.ffecommerceproject.activity.PublishComment;
import com.tech.wd.ffecommerceproject.adapter.CommentAdapter;
import com.tech.wd.ffecommerceproject.adapter.CommentChildAdapter;
import com.tech.wd.ffecommerceproject.adapter.TakeGoodsAdapter;
import com.tech.wd.ffecommerceproject.bean.AllOrderBean;
import com.tech.wd.ffecommerceproject.bean.RegsiterBean;
import com.tech.wd.ffecommerceproject.fragment.BaseFragment;
import com.tech.wd.ffecommerceproject.precenter.IPrecenterImpl;
import com.tech.wd.ffecommerceproject.view.IView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

public class CommentFragment extends BaseFragment implements IView{ //待评论

    @BindView(R.id.fragmentallorders_rv)
    RecyclerView mRecyclerView;
    private String mSessionId;
    private String mUserId;
    private IPrecenterImpl mIPrecenter;
    @Override
    public void initData(View view) {

        ButterKnife.bind(this,view);

        SharedPreferences sp = getActivity().getSharedPreferences("sp", MODE_PRIVATE);
        mSessionId = sp.getString("sessionId", null);
        mUserId = sp.getString("userId", null);
        mIPrecenter = new IPrecenterImpl(this);
        requestData();
    }

    public void requestData() {
        mIPrecenter.startRequestData(String.format(Apis.URL_AllOrders,3),null, AllOrderBean.class,2,mUserId,mSessionId);
    }

    @Override
    public int getContent() {
        return R.layout.indentfragment_comments;
    }

    @Override
    public void showData(Object data) {

        if (data instanceof AllOrderBean){

            AllOrderBean allOrderBean = (AllOrderBean) data;
            List<AllOrderBean.OrderListBean> orderList = allOrderBean.getOrderList();

            CommentAdapter commentAdapter = new CommentAdapter(getActivity(), orderList);
            mRecyclerView.setAdapter(commentAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

            commentAdapter.setOnItemClickListener(new CommentAdapter.onItemClickListener() {
                @Override
                public void onImgDeleteListener(String orderId) {  //点击图片删除

                    Map<String, String> map = new HashMap<>();
                    map.put("orderId",orderId);
                    mIPrecenter.startRequestData(Apis.URL_DeleteDd,map, RegsiterBean.class,4,mUserId,mSessionId);

                }
            });

        }else if (data instanceof RegsiterBean){
            RegsiterBean regsiterBean = (RegsiterBean) data;

            Toast.makeText(getActivity(),regsiterBean.getMessage(),Toast.LENGTH_SHORT).show();
            mIPrecenter.startRequestData(String.format(Apis.URL_AllOrders,3),null, AllOrderBean.class,2,mUserId,mSessionId);

        }


    }

    public void refreshCommentPage(){
        mIPrecenter.startRequestData(String.format(Apis.URL_AllOrders,3),null, AllOrderBean.class,2,mUserId,mSessionId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIPrecenter.onDetach();
    }
}
