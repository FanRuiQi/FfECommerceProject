package com.tech.wd.ffecommerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.bean.FootPrintBean;
import com.tech.wd.ffecommerceproject.bean.MyAddressBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineAddressAdapter extends RecyclerView.Adapter<MineAddressAdapter.ViewHolder>{  //我的收货地址列表页面

    private Context mContext;
    private List<MyAddressBean.ResultBean> list;

    public MineAddressAdapter(Context context, List<MyAddressBean.ResultBean> list) {
        mContext = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.activity_mine_address_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public interface onItemClickListener{
        void onRadioButtonClick(int id);
        void onButtonDelete(int layoutPosition);
        void onButtonUpdate(int id);
    }
    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

            holder.mTextView_name.setText(list.get(position).getRealName());
            holder.mTextView_phone.setText(list.get(position).getPhone());
            holder.mTextView_text.setText(list.get(position).getAddress());

            holder.mRadioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener!=null){
                        int id = list.get(position).getId();
                        mOnItemClickListener.onRadioButtonClick(id);

                    }
                }
            });

        holder.mButton_dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onButtonDelete(layoutPosition);

                }
            }
        });

        holder.mButton_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    int id = list.get(position).getId();
                    mOnItemClickListener.onButtonUpdate(id);

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_address_check)
        RadioButton mRadioButton;

        @BindView(R.id.item_address_name)
        TextView mTextView_name;

        @BindView(R.id.item_address_phone)
        TextView mTextView_phone;

        @BindView(R.id.item_address_textView)
        TextView mTextView_text;

        @BindView(R.id.item_address_delete)
        Button mButton_dele;
        @BindView(R.id.item_address_update)
        Button mButton_update;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
