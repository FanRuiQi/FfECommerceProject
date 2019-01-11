package com.tech.wd.ffecommerceproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.bean.ShowShopCatBean;
import com.tech.wd.ffecommerceproject.view.AddSubView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubmitOrderAdapter extends RecyclerView.Adapter<SubmitOrderAdapter.ViewHolder>{

    private Context mContext;
    private List<ShowShopCatBean.ResultBean> list;

    public SubmitOrderAdapter(Context context, List<ShowShopCatBean.ResultBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_shopcat_show, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public interface onItemClickListener{
        void onItemClick(int commodityId);
        void onCheckBoxClickListener(int position);
        void oJJChangeListener(int position, int number);
    }
    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


            holder.mTextView_name.setText(list.get(position).getCommodityName());
            holder.mTextView_price.setText("￥"+list.get(position).getPrice()+".00");
            holder.mAddSubView.setNumber(list.get(position).getCount());
            holder.mCheckBox.setChecked(list.get(position).getSelected()==1);
            Uri uri = Uri.parse(list.get(position).getPic());
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

          holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  mOnItemClickListener.onCheckBoxClickListener(position);

              }
          });

          holder.mAddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
              @Override
              public void onNumberChange(int number) {

                  mOnItemClickListener.oJJChangeListener(position,number);
              }
          });
    }



    public void changeSp(int i){
        list.get(i).setSelected(list.get(i).getSelected()==0?1:0);
    }

    public int getTotalPrice(){

        int totalPrice=0;
        for (int x=0;x<list.size();x++){
            if (list.get(x).getSelected()==1){
                int price = list.get(x).getPrice();
                int count = list.get(x).getCount();
                totalPrice+=price*count;
            }
        }
        return totalPrice;
    }

    public void ChangeNumber(int i,int number){
        list.get(i).setCount(number);
    }

    public Boolean getAllSpSelectState(){   //获取所有的商品选中状态
        for (int i=0;i<list.size();i++){
            if (list.get(i).getSelected()==0){
                return false;
            }
        }
        return true;
    }

    public Boolean getSpSelectIsSelected(){

        for (int i=0;i<list.size();i++){
            if (list.get(i).getSelected()==1){
                ShowShopCatBean.ResultBean resultBean = list.get(i);

                return true;
            }
        }
        return false;
    }

    public List<ShowShopCatBean.ResultBean> getSpSelectedData(){

        List<ShowShopCatBean.ResultBean> lists = null;
        for (int i=0;i<list.size();i++){
            if (list.get(i).getSelected()==1){
                ShowShopCatBean.ResultBean resultBean = list.get(i);
                lists.add(resultBean);

            }
        }
        return lists;
    }

    public void qxChangeAllSp(boolean isSelected){       //全选改变商品是否选中
        for (int i=0;i<list.size();i++){
            list.get(i).setSelected(isSelected?1:0);

        }
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

        @BindView(R.id.item_shopCat_check)
        CheckBox mCheckBox;

        @BindView(R.id.item_shopCat_add)
        AddSubView mAddSubView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
