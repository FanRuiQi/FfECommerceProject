package com.tech.wd.ffecommerceproject.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tech.wd.ffecommerceproject.Apis;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.adapter.DetailsBannerAdapter;
import com.tech.wd.ffecommerceproject.bean.AddShopCatBean;
import com.tech.wd.ffecommerceproject.bean.DetailsBean;
import com.tech.wd.ffecommerceproject.precenter.IPrecenterImpl;
import com.tech.wd.ffecommerceproject.view.DetailScrollView;
import com.tech.wd.ffecommerceproject.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements IView{

    @BindView(R.id.details_scroll_changeTitleColor)
    DetailScrollView mDetailScrollView;

    @BindView(R.id.details_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.details_tv_showNum)
    TextView mTextView_showNum;

    @BindView(R.id.details_tv_price)
    TextView mTextView_price;

    @BindView(R.id.details_tv_sold)
    TextView mTextView_sold;

    @BindView(R.id.details_tv_shopName)
    TextView mTextView_shopName;

    @BindView(R.id.details_tv_Weight)
    TextView mTextView_weight;

    @BindView(R.id.details_Shop_Image)
    SimpleDraweeView mSimpleDraweeView_img;

    @BindView(R.id.details_tv_describe)  //商品介绍
    TextView mTextView_describe;

    @BindView(R.id.details_Image_describe)
    SimpleDraweeView mSimpleDraweeView_describe;

    @BindView(R.id.details_rv_comments)  //评论
    RecyclerView mRecyclerView_comments;

    @BindView(R.id.details_tv_comments)
    TextView mTextView_comments;

    @BindView(R.id.details_image_return)
    ImageView mImageView_return;

    @BindView(R.id.details_relative_changer)
    RelativeLayout mRelativeLayout_changer;

    @BindView(R.id.details_relat_changecolor)
    RelativeLayout mRelativeLayout_relat;

    @BindView(R.id.details_text_goodsT)
    TextView mTextView_goods;

    @BindView(R.id.details_text_detailsT)
    TextView mTextView_details;

    @BindView(R.id.details_text_commentsT)
    TextView mTextView_comment;

    @BindView(R.id.details_text_goods)
    TextView mTextView_good;

    @BindView(R.id.details_text_details)
    TextView mTextView_detail;

    @BindView(R.id.details_text_comments)
    TextView mTextView_commen;

    @BindView(R.id.details_relative_addShoppingCar)
    RelativeLayout mRelativeLayout_add;

    @BindView(R.id.details_relative_pay)
    RelativeLayout mRelativeLayout_pay;
    private IPrecenterImpl mIPrecenter;

    private int mCount;

    int id;
    private String mSessionId;
    private String mUserId;
    DetailsBean.ResultBean result;
    private IPrecenterImpl mIPrecenter1;

    @Override
    public void initData() {

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

         mIPrecenter1 = new IPrecenterImpl(this);

        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
         mSessionId = sp.getString("sessionId", null);
        mUserId = sp.getString("userId", null);
        mIPrecenter = new IPrecenterImpl(this);
        requestData();
        setListener();
    }



    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void message(Integer mId){
         id= mId;
        Log.e("ff",id+"");
    }

    private void requestData() {

        mIPrecenter.startRequestData(String.format(Apis.URL_HOMEPAGE_SHOP_XQ,id), null,DetailsBean.class,2,mUserId,mSessionId);

    }

    @OnClick(R.id.details_image_return)  //点击返回
    public void onBackClickListener(){
        finish();
    }

    @OnClick(R.id.details_relative_addShoppingCar)  //添加购物车
    public void onAddShopCatClickListener(){

        int commodityId = result.getCommodityId();
        Map<String, String> map = new HashMap<>();
        map.put("data","[{\"commodityId\":"+commodityId+",\"count\":1}]");

        mIPrecenter.startRequestData(Apis.URL_ADD_SHOP_CAT,map, AddShopCatBean.class,5,mUserId,mSessionId);

    }

    @Override
    public int getContent() {
        return R.layout.activity_details;
    }

    @Override
    public void showData(Object data) {

        if (data instanceof DetailsBean){

            DetailsBean detailsBean = (DetailsBean) data;
            result = detailsBean.getResult();
            mTextView_price.setText("￥" + result.getPrice());
            mTextView_sold.setText("已售" + result.getSaleNum() + "件");
            mTextView_shopName.setText(result.getCommodityName());
            mTextView_weight.setText(result.getWeight() + "kg");
            mTextView_describe.setText(result.getDescribe());

            String Pictures = result.getPicture().trim();
            String[] split = Pictures.split(",");

            mSimpleDraweeView_img.setImageURI(split[0]);
            mSimpleDraweeView_describe.setImageURI(split[1]);

            DetailsBannerAdapter adapter = new DetailsBannerAdapter(this, split);
            mCount = adapter.getCount();
            mViewPager.setAdapter(adapter);
        }else if (data instanceof AddShopCatBean){

            AddShopCatBean addShopCatBean = (AddShopCatBean) data;
            Toast.makeText(DetailActivity.this,addShopCatBean.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }



    private void setListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTextView_showNum.setText((position + 1) + "/" + mCount);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //滑动变色
        mDetailScrollView.setScrollViewListener(new DetailScrollView.ScrollViewListener() {
            @Override
            public void onScrollChange(DetailScrollView scrollView, int l, int t, int oldl, int oldt) {
                if (t <= 0) {
                    mRelativeLayout_changer.setVisibility(View.VISIBLE);
                    mRelativeLayout_relat.setBackgroundColor(Color.argb(0, 0, 0, 0));
                } else if (t > 0 && t < 200) {
                    mRelativeLayout_changer.setVisibility(View.VISIBLE);
                    float scale = (float) t / 200;
                    float alpha = 255 * scale;
                    mRelativeLayout_relat.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                }
                if (t < 700) {
                    mTextView_good.setBackgroundColor(Color.RED);
                    mTextView_detail.setBackgroundColor(Color.WHITE);
                    mTextView_commen.setBackgroundColor(Color.WHITE);
                } else if (701 < t && t < 1400) {
                    mTextView_good.setBackgroundColor(Color.WHITE);
                    mTextView_detail.setBackgroundColor(Color.RED);
                    mTextView_commen.setBackgroundColor(Color.WHITE);
                } else if (t > 1400) {
                    mTextView_good.setBackgroundColor(Color.WHITE);
                    mTextView_detail.setBackgroundColor(Color.WHITE);
                    mTextView_commen.setBackgroundColor(Color.RED);
                }
            }

        });

        mTextView_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDetailScrollView.setScrollY(0);
            }
        });
        mTextView_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDetailScrollView.setScrollY(702);
            }
        });
        mTextView_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDetailScrollView.setScrollY(1501);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mIPrecenter.onDetach();
    }
}
