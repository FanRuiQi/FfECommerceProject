package com.tech.wd.ffecommerceproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.bean.CircleBean;
import com.tech.wd.ffecommerceproject.bean.ShowHotBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleListAdapter extends RecyclerView.Adapter<CircleListAdapter.ViewHolder>{

    private Context mContext;
    private List<CircleBean.ResultBean> list;

    public CircleListAdapter(Context context, List<CircleBean.ResultBean> list) {
        mContext = context;
        this.list = list;

    }

    public void refreash(){

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_circle_show_list, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public interface OnImgClickListener{
        void onImgClick(int id,int great);
    }

    private OnImgClickListener mOnImgClickListener;

    public void setOnImgClickListener(OnImgClickListener onImgClickListener){
        mOnImgClickListener=onImgClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

            holder.mTextView_name.setText(list.get(position).getNickName());
            holder.mTextView_date.setText(list.get(position).getCreateTime()+"");
            holder.mTextView_nr.setText(list.get(position).getContent());
            holder.mTextView_dzNum.setText(list.get(position).getGreatNum()+"");
            Uri uri = Uri.parse(list.get(position).getHeadPic());
            holder.mSimpleDraweeView_tx.setImageURI(uri);
            Uri uri1 = Uri.parse(list.get(position).getImage());
            holder.mSimpleDraweeView_rn1.setImageURI(uri1);
            Uri uri2 = Uri.parse(list.get(position).getImage());
            holder.mSimpleDraweeView_rn2.setImageURI(uri2);

            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnImgClickListener!=null){
                        /*if (list.get(position).getBo()==0){

                        }else {

                        }
                        int bo = list.get(position).getBo();
                        if (bo!=0){

                            int greatNum = list.get(position).getGreatNum();
                            greatNum++;
                        }*/
                        int id = list.get(position).getId();
                        int great = list.get(position).getWhetherGreat();

                        mOnImgClickListener.onImgClick(id,great);
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_circle_img_tx)
        SimpleDraweeView mSimpleDraweeView_tx;

        @BindView(R.id.item_circle_img_rn1)
        SimpleDraweeView mSimpleDraweeView_rn1;

        @BindView(R.id.item_circle_img_rn2)
        SimpleDraweeView mSimpleDraweeView_rn2;

        @BindView(R.id.item_circle_img_dz)
        ImageView mImageView;

        @BindView(R.id.item_circle_text_name)
        TextView mTextView_name;

        @BindView(R.id.item_circle_text_date)
        TextView mTextView_date;

        @BindView(R.id.item_circle_text_nr)
        TextView mTextView_nr;

        @BindView(R.id.item_circle_text_dzNum)
        TextView mTextView_dzNum;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
