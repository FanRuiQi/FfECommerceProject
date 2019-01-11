package com.tech.wd.ffecommerceproject.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.wd.ffecommerceproject.R;

public class AddSubView extends LinearLayout implements View.OnClickListener{

    public int number=1;
    private TextView mAdd;
    private TextView mSub;
    private TextView mNum;

    public AddSubView(Context context) {
        this(context,null);
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = inflate(context, R.layout.add_remove, this);

        mAdd = view.findViewById(R.id.sub_jia);
        mSub = view.findViewById(R.id.sub_jian);
        mNum = view.findViewById(R.id.sub_num);
        mAdd.setOnClickListener(this);
        mSub.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.sub_jian:
                if (number>1){
                    number--;

                    mNum.setText(number+"");
                    if (mOnNumberChangeListener!=null){
                        mOnNumberChangeListener.onNumberChange(number);
                    }
                }else {
                    Toast.makeText(getContext(), "不能再少了", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.sub_jia:
                if (number<5){
                    number++;

                    mNum.setText(number+"");
                    if (mOnNumberChangeListener!=null){
                        mOnNumberChangeListener.onNumberChange(number);
                    }

                }else {
                    Toast.makeText(getContext(), "不能再多了", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int number){
        this.number=number;
        mNum.setText(number+"");
    }

    public interface OnNumberChangeListener{
        void onNumberChange(int number);
    }

    OnNumberChangeListener mOnNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener){

        mOnNumberChangeListener=onNumberChangeListener;
    }

}
