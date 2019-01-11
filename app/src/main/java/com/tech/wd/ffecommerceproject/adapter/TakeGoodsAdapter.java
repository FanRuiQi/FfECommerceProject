package com.tech.wd.ffecommerceproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.bean.AllOrderBean;
import com.tech.wd.ffecommerceproject.view.AddSubView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TakeGoodsAdapter extends RecyclerView.Adapter<TakeGoodsAdapter.ViewHolder>{  //展示待收货Adapter

    private Context mContext;
    private List<AllOrderBean.OrderListBean> list;

    public TakeGoodsAdapter(Context context, List<AllOrderBean.OrderListBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_takegoods, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public interface onItemClickListener{
        void onButtonZfClickListener(String orderId);
    }
    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {



        holder.mTextView_ddh.setText("订单号   "+list.get(position).getOrderId());
        holder.mTextView_pjgs.setText("派件公司   "+list.get(position).getExpressCompName());
        holder.mTextView_kddh.setText("快递单号   "+list.get(position).getExpressSn());

        //获取当前时间
        Date date = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = time.format(date);
        holder.mTextView_date.setText(format);

        final List<AllOrderBean.OrderListBean.DetailListBean> detailList = list.get(position).getDetailList();

        final TakeGoodsChildAdapter childAdapter = new TakeGoodsChildAdapter(mContext,detailList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        holder.mRecyclerView.setLayoutManager(linearLayoutManager);
        holder.mRecyclerView.setAdapter(childAdapter);


        holder.mButton_zf.setOnClickListener(new View.OnClickListener() {  //收货点击监听
            @Override
            public void onClick(View view) {
                String orderId = list.get(position).getOrderId();   //获取订单ID
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onButtonZfClickListener(orderId);
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

        @BindView(R.id.item_allOrder_text_pjgs)  //派件公司
        TextView mTextView_pjgs;

        @BindView(R.id.item_allOrder_text_kddh)  //快递单号
        TextView mTextView_kddh;


        @BindView(R.id.item_allOrder_btn_zf)  //收货
        Button mButton_zf;

        @BindView(R.id.fragmentallorders_rv_child)
        RecyclerView mRecyclerView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
