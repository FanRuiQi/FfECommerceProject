package com.tech.wd.ffecommerceproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.activity.PublishComment;
import com.tech.wd.ffecommerceproject.bean.AllOrderBean;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentChildAdapter extends RecyclerView.Adapter<CommentChildAdapter.ViewHolder>{  //展示待评论Adapter

    private Context mContext;
    private List<AllOrderBean.OrderListBean.DetailListBean> list;
    private String mCommodityPic;
    private String orderId;

    public CommentChildAdapter(Context context, List<AllOrderBean.OrderListBean.DetailListBean> list,String orderId) {
        mContext = context;
        this.list = list;
        this.orderId=orderId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_comment_child, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public interface onItemClickListener{
        void onButtonZfClickListener(int commodityId, String orderId);
    }
    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
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


        holder.mButton_zf.setOnClickListener(new View.OnClickListener() {  //收货点击监听
            @Override
            public void onClick(View view) {
                int commodityId = list.get(position).getCommodityId();

                Intent intent = new Intent(mContext, PublishComment.class);
                intent.putExtra("commodityId",commodityId);
                intent.putExtra("orderId",orderId);

                mContext.startActivity(intent);
            }
        });


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

        @BindView(R.id.item_allOrder_btn_zf)  //评论
        Button mButton_zf;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
