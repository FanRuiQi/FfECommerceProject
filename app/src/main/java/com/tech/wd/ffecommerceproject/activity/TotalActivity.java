package com.tech.wd.ffecommerceproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.fragment.CircleFragment;
import com.tech.wd.ffecommerceproject.fragment.HomepageFragment;
import com.tech.wd.ffecommerceproject.fragment.IndentFragment;
import com.tech.wd.ffecommerceproject.fragment.MyFragment;
import com.tech.wd.ffecommerceproject.fragment.StopstolleyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TotalActivity extends BaseActivity{
    @BindView(R.id.total_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.total_group)
    RadioGroup mRadioGroup;

    @Override
    public void initData() {
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0:
                        fragment = new HomepageFragment();
                        break;
                    case 1:
                        fragment = new CircleFragment();
                        break;
                    case 2:
                        fragment = new StopstolleyFragment();
                        break;
                    case 3:
                        fragment = new IndentFragment();
                        break;
                    case 4:
                        fragment = new MyFragment();
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
                    case R.id.tab_home_bottom_shouye:
                        mViewPager.setCurrentItem(0);

                        break;
                    case R.id.tab_home_bottom_quanzi:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_home_bottom_che:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.tab_home_bottom_dingdan:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.tab_home_bottom_wode:
                        mViewPager.setCurrentItem(4);
                        break;
                }
            }
        });
    }

    @Override
    public int getContent() {
        return R.layout.activity_total;
    }
}
