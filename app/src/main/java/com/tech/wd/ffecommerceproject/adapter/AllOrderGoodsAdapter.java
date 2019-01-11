package com.tech.wd.ffecommerceproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.bean.AllOrderBean;
import com.tech.wd.ffecommerceproject.view.AddSubView;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllOrderGoodsAdapter extends RecyclerView.Adapter<AllOrderGoodsAdapter.ViewHolder>{  //全部订单

    private Context mContext;
    private List<AllOrderBean.OrderListBean.DetailListBean> list;
    private String mCommodityPic;

    public AllOrderGoodsAdapter(Context context, List<AllOrderBean.OrderListBean.DetailListBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_allorder_child, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    public interface onItemClickListener{
        void onItemClick(int commodityId);
        void oJJChangeListener(int position, int number);
    }
    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        holder.mTextView_name.setText(list.get(position).getCommodityName());
        holder.mTextView_price.setText("￥"+list.get(position).getCommodityPrice()+".00");
        holder.mAddSubView.setNumber(list.get(position).getCommodityCount());

         mCommodityPic = list.get(position).getCommodityPic();

        Pattern pattern = Pattern.compile("\\,");
        String[] img = pattern.split(mCommodityPic);


        Uri uri = Uri.parse(img[0]);
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


        holder.mAddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int number) {

                mOnItemClickListener.oJJChangeListener(position,number);
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

        @BindView(R.id.item_shopCat_add)
        AddSubView mAddSubView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
