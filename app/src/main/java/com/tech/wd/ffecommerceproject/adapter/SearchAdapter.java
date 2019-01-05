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
import com.tech.wd.ffecommerceproject.bean.SearchBean;
import com.tech.wd.ffecommerceproject.bean.ShowHotBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private Context mContext;
    private List<SearchBean.ResultBean> list;

    public SearchAdapter(Context context, List<SearchBean.ResultBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_homepage_search, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {



            holder.mTextView_name.setText(list.get(position).getCommodityName());
            holder.mTextView_sale.setText(list.get(position).getSaleNum()+"");
            holder.mTextView_price.setText("￥"+list.get(position).getPrice()+"");
            Uri uri = Uri.parse(list.get(position).getMasterPic());
            holder.mSimpleDraweeView.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_search_image)
        SimpleDraweeView mSimpleDraweeView;

        @BindView(R.id.home_search_name)
        TextView mTextView_name;

        @BindView(R.id.home_search_price)
        TextView mTextView_price;

        @BindView(R.id.home_search_saleNum)
        TextView mTextView_sale;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
