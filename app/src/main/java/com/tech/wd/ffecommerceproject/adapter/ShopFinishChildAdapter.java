package com.tech.wd.ffecommerceproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.activity.PublishComment;
import com.tech.wd.ffecommerceproject.bean.AllOrderBean;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopFinishChildAdapter extends RecyclerView.Adapter<ShopFinishChildAdapter.ViewHolder>{  //展示已完成Adapter

    private Context mContext;
    private List<AllOrderBean.OrderListBean.DetailListBean> list;
    private String mCommodityPic;

    public ShopFinishChildAdapter(Context context, List<AllOrderBean.OrderListBean.DetailListBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_finish_child, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        holder.mTextView_name.setText(list.get(position).getCommodityName());
        holder.mTextView_price.setText("￥"+list.get(position).getCommodityPrice()+".00");

         mCommodityPic = list.get(position).getCommodityPic();

        Pattern pattern = Pattern.compile("\\,");
        String[] img = pattern.split(mCommodityPic);


        Uri uri = Uri.parse(img[0]);
        holder.mSimpleDraweeView.setImageURI(uri);




    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_shopCat_img)
        SimpleDraweeView mSimpleDraweeView;

        @BindView(R.id.item_shopCat_title)
        TextView mTextView_name;

        @BindView(R.id.item_shopCat_price)
        TextView mTextView_price;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
