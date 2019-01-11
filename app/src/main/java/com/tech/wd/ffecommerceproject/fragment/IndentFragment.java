package com.tech.wd.ffecommerceproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.indentfragment.AllOrdersFragment;
import com.tech.wd.ffecommerceproject.indentfragment.CommentFragment;
import com.tech.wd.ffecommerceproject.indentfragment.FinishFragment;
import com.tech.wd.ffecommerceproject.indentfragment.PaymentFragment;
import com.tech.wd.ffecommerceproject.indentfragment.TakeGoodsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndentFragment extends BaseFragment{  //订单页

    @BindView(R.id.indent_group)
    RadioGroup mRadioGroup;

    @BindView(R.id.indent_viewpager)
    ViewPager mViewPager;

    @Override
    public void initData(View view) {

        ButterKnife.bind(this,view);

        mViewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0:
                        fragment = new AllOrdersFragment();
                        break;
                    case 1:
                        fragment = new PaymentFragment();
                        break;
                    case 2:
                        fragment = new TakeGoodsFragment();
                        break;
                    case 3:
                        fragment = new CommentFragment();
                        break;
                    case 4:
                        fragment = new FinishFragment();
                        break;

                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.i1:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.i2:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.i3:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.i4:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.i5:
                        mViewPager.setCurrentItem(4);
                        break;
                }
            }
        });
    }

    @Override
    public int getContent() {
        return R.layout.fragment_indent;
    }
}
