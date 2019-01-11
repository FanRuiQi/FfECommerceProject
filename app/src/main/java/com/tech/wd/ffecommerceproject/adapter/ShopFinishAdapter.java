package com.tech.wd.ffecommerceproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.bean.AllOrderBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopFinishAdapter extends RecyclerView.Adapter<ShopFinishAdapter.ViewHolder>{  //展示已完成Adapter

    private Context mContext;
    private List<AllOrderBean.OrderListBean> list;

    public ShopFinishAdapter(Context context, List<AllOrderBean.OrderListBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_finish, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public interface onItemClickListener{
        void onImgDeleteListener(String orderId);
    }
    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        //获取当前时间
        Date date = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = time.format(date);
        holder.mTextView_date.setText(format);
        holder.mTextView_ddh.setText("订单号   "+list.get(position).getOrderId());

        final List<AllOrderBean.OrderListBean.DetailListBean> detailList = list.get(position).getDetailList();

        final ShopFinishChildAdapter childAdapter = new ShopFinishChildAdapter(mContext,detailList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        holder.mRecyclerView.setLayoutManager(linearLayoutManager);
        holder.mRecyclerView.setAdapter(childAdapter);



        holder.mImageView.setOnClickListener(new View.OnClickListener() {  //删除点击监听
            @Override
            public void onClick(View view) {
                String orderId = list.get(position).getOrderId();   //获取订单ID
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onImgDeleteListener(orderId);
                }
            }
        });



    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_allOrder_text_ddh)  //订单号
        TextView mTextView_ddh;

        @BindView(R.id.item_allOrder_text_date)  //日期
        TextView mTextView_date;

        @BindView(R.id.item_allOrder_img)  //点击删除
        ImageView mImageView;

        @BindView(R.id.fragmentallorders_rv_child)
        RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
