package com.tech.wd.ffecommerceproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.bean.ShowHotBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowQuaAdapter extends RecyclerView.Adapter<ShowQuaAdapter.ViewHolder>{

    private Context mContext;
    private List<ShowHotBean.ResultBean.PzshBean.CommodityListBeanX> list;

    public ShowQuaAdapter(Context context, List<ShowHotBean.ResultBean.PzshBean.CommodityListBeanX> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_homepage_showquality, null);
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



            holder.mTextView_name.setText(list.get(position).getCommodityName());
            holder.mTextView_price.setText("￥"+list.get(position).getPrice()+"");
            Uri uri = Uri.parse(list.get(position).getMasterPic());
            holder.mSimpleDraweeView.setImageURI(uri);

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
        @BindView(R.id.item_home_qua_simple)
        SimpleDraweeView mSimpleDraweeView;

        @BindView(R.id.item_home_qua_name)
        TextView mTextView_name;

        @BindView(R.id.item_home_qua_price)
        TextView mTextView_price;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
