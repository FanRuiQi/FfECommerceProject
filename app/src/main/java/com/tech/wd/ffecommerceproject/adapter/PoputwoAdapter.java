package com.tech.wd.ffecommerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.bean.PopuoneBean;
import com.tech.wd.ffecommerceproject.bean.PoputwoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PoputwoAdapter extends RecyclerView.Adapter<PoputwoAdapter.ViewHolder>{

    private Context mContext;
    private List<PoputwoBean.ResultBean> list;

    public PoputwoAdapter(Context context, List<PoputwoBean.ResultBean> list) {
        mContext = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_popu_two, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public interface onItemClickListener{
        void onItemClick(String id);
    }
    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {



            holder.mTextView_name.setText(list.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener!=null){
                        String id = list.get(position).getId();
                        mOnItemClickListener.onItemClick(id);

                    }
                }
            });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_popu_text)
        TextView mTextView_name;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
