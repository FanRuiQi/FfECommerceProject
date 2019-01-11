package com.tech.wd.ffecommerceproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.bean.FootPrintBean;
import com.tech.wd.ffecommerceproject.bean.ShowHotBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootPrintAdapter extends RecyclerView.Adapter<FootPrintAdapter.ViewHolder>{

    private Context mContext;
    private List<FootPrintBean.ResultBean> list;

    public FootPrintAdapter(Context context, List<FootPrintBean.ResultBean> list) {
        mContext = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_my_footprint, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public interface onItemClickListener{
        void onItemClick(int commodityId);
    }
    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


            Glide.with(mContext).load(list.get(position).getMasterPic()).into(holder.mSimpleDraweeView);
            holder.mTextView_title.setText(list.get(position).getCommodityName());
            holder.mTextView_price.setText("￥"+list.get(position).getPrice()+"");
            holder.mTextView_browseNum.setText("已浏览"+list.get(position).getBrowseNum()+"次");
            holder.mTextView_date.setText(list.get(position).getBrowseTime()+"");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener!=null){
                        int commodityId = list.get(position).getCommodityId();
                        mOnItemClickListener.onItemClick(commodityId);

                    }
                }
            });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_my_img)
        ImageView mSimpleDraweeView;

        @BindView(R.id.item_my_title)
        TextView mTextView_title;

        @BindView(R.id.item_my_price)
        TextView mTextView_price;

        @BindView(R.id.item_my_browseNum)
        TextView mTextView_browseNum;

        @BindView(R.id.item_my_date)
        TextView mTextView_date;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
