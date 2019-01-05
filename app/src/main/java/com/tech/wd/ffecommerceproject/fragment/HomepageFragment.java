package com.tech.wd.ffecommerceproject.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.tech.wd.ffecommerceproject.Apis;
import com.tech.wd.ffecommerceproject.R;
import com.tech.wd.ffecommerceproject.activity.TotalActivity;
import com.tech.wd.ffecommerceproject.adapter.PopuoneAdapter;
import com.tech.wd.ffecommerceproject.adapter.PoputwoAdapter;
import com.tech.wd.ffecommerceproject.adapter.SearchAdapter;
import com.tech.wd.ffecommerceproject.adapter.ShowHotAdapter;
import com.tech.wd.ffecommerceproject.adapter.ShowMagicAdapter;
import com.tech.wd.ffecommerceproject.adapter.ShowQuaAdapter;
import com.tech.wd.ffecommerceproject.bean.BannerBean;
import com.tech.wd.ffecommerceproject.bean.PopuoneBean;
import com.tech.wd.ffecommerceproject.bean.PoputwoBean;
import com.tech.wd.ffecommerceproject.bean.SearchBean;
import com.tech.wd.ffecommerceproject.bean.ShowHotBean;
import com.tech.wd.ffecommerceproject.precenter.IPrecenterImpl;
import com.tech.wd.ffecommerceproject.view.IView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomepageFragment extends BaseFragment implements IView{

    @BindView(R.id.home_recyclerView_showHot)
    RecyclerView mRecyclerView_showHot;

    @BindView(R.id.home_recyclerView_showMagic)
    RecyclerView mRecyclerView_showMagic;

    @BindView(R.id.home_recyclerView_showQuality)
    RecyclerView mRecyclerView_showQuality;

    @BindView(R.id.home_rv_search)
    RecyclerView mRecyclerView_search;

    @BindView(R.id.home_viewpager_banner)
    XBanner mXBanner;

    @BindView(R.id.home_image_popupWindow)
    ImageView mImageView;

    @BindView(R.id.home_edit_search)
    EditText mEditText;

    @BindView(R.id.home_text_search)
    TextView mTextView_search;

    @BindView(R.id.home_image_search)
    ImageView mImageView_search;

    @BindView(R.id.home_image_noSearch)
    LinearLayout mImageView_no;

    @BindView(R.id.home_image_back)
    ImageView mImageView_back;

    @BindView(R.id.home_scrollView)
    ScrollView mScrollView;

    private XBanner.XBannerAdapter mXBannerAdapter;
    private IPrecenterImpl mIPrecenter;
    private RecyclerView mRecyclerView_popu_one;
    private RecyclerView mRecyclerView_popu_two;

    @Override
    public void initData(View view) {
        ButterKnife.bind(this,view);


        mIPrecenter = new IPrecenterImpl(this);
        requestData();
    }

    @Override
    public int getContent() {
        return R.layout.fragment_homepage;
    }

    @OnClick(R.id.home_image_popupWindow)
    public void onImgClick(){

        final View popu = View.inflate(getActivity(),R.layout.item_popupwidow_list_one, null);

        mRecyclerView_popu_one = popu.findViewById(R.id.item_popu_recycleView_one);
         mRecyclerView_popu_two = popu.findViewById(R.id.item_popu_recycleView_two);

        mIPrecenter.startRequestData(Apis.URL_CIRCLE_ONE_LIST,null, PopuoneBean.class,1,null,null);
        //mIPrecenter.startRequestData(String.format(Apis.URL_CIRCLE_TWO_LIST,"1001002"),null, PoputwoBean.class,1,null,null);

        PopupWindow window = new PopupWindow(popu, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        window.setFocusable(true);
        window.setTouchable(true);
        window.showAsDropDown(mImageView);

    }

    @OnClick(R.id.home_text_search)
    public void onTextSearchClick(){    //搜索Text点击事件

        String keyword = mEditText.getText().toString().trim();

        if (keyword.isEmpty()){
            mEditText.setVisibility(View.GONE);
            mTextView_search.setVisibility(View.GONE);

            mImageView_search.setVisibility(View.VISIBLE);
        }else {
            mImageView.setVisibility(View.GONE);

            mImageView_back.setVisibility(View.VISIBLE);

            mScrollView.setVisibility(View.GONE);
            Map<String, String> map = new HashMap<>();
            mIPrecenter.startRequestData(String.format(Apis.URL_HOMEPAGE_SEARCH_LIST,keyword),map, SearchBean.class,1,null,null);


        }


    }

    @OnClick(R.id.home_image_search)   //搜索按钮的点击事件
    public void onImgSearchClick(){

         mEditText.setVisibility(View.VISIBLE);
         mTextView_search.setVisibility(View.VISIBLE);

         mImageView_search.setVisibility(View.GONE);
    }

    @OnClick(R.id.home_image_back)   //返回按钮的点击事件
    public void onImgBackClick(){

        mImageView_no.setVisibility(View.GONE);
        mScrollView.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.VISIBLE);
        mImageView_back.setVisibility(View.GONE);
    }

    private void requestData() {
        Map<String, String> map = new HashMap<>();
        mIPrecenter.startRequestData(Apis.URL_HOMEPAGE_SHOW_SHOP,map, ShowHotBean.class,1,null,null);

        mIPrecenter.startRequestData(Apis.URL_HOMEPAGE_BANNER,map, BannerBean.class,1,null,null);

    }

    @Override
    public void showData(Object data) {
        if (data instanceof ShowHotBean){

            ShowHotBean showHotBean = (ShowHotBean) data;
            List<ShowHotBean.ResultBean.RxxpBean> rxxp = showHotBean.getResult().getRxxp();
            List<ShowHotBean.ResultBean.RxxpBean.CommodityListBean> commodityList = rxxp.get(0).getCommodityList();
            ShowHotAdapter showHotAdapter = new ShowHotAdapter(getActivity(), commodityList);
            mRecyclerView_showHot.setAdapter(showHotAdapter);
            mRecyclerView_showHot.setLayoutManager(new GridLayoutManager(getActivity(),3));
            showHotAdapter.setOnItemClickListener(new ShowHotAdapter.onItemClickListener() {
                @Override
                public void onItemClick(int commodityId) {


                }
            });

            List<ShowHotBean.ResultBean.MlssBean> mlss = showHotBean.getResult().getMlss();
            List<ShowHotBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList1 = mlss.get(0).getCommodityList();
            ShowMagicAdapter magicAdapter = new ShowMagicAdapter(getActivity(), commodityList1);
            mRecyclerView_showMagic.setAdapter(magicAdapter);
            mRecyclerView_showMagic.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

            List<ShowHotBean.ResultBean.PzshBean> pzsh = showHotBean.getResult().getPzsh();
            List<ShowHotBean.ResultBean.PzshBean.CommodityListBeanX> commodityList2 = pzsh.get(0).getCommodityList();
            ShowQuaAdapter quaAdapter = new ShowQuaAdapter(getActivity(), commodityList2);
            mRecyclerView_showQuality.setAdapter(quaAdapter);
            mRecyclerView_showQuality.setLayoutManager(new GridLayoutManager(getActivity(),2));

        }else if (data instanceof BannerBean){

            BannerBean bannerBean = (BannerBean) data;
            final List<BannerBean.ResultBean> beans = bannerBean.getResult();
            ArrayList<String> mlist = new ArrayList<>();
            for (int i = 0; i < beans.size(); i++) {
                mlist.add(beans.get(i).getImageUrl());
            }

            mXBanner.setData(beans, null);
            mXBannerAdapter = new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    //设置图片圆角角度
                    RoundedCorners roundedCorners = new RoundedCorners(10);

                    RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 150);
                    Glide.with(getActivity()).load(beans.get(position).getImageUrl()).apply(options).into((ImageView) view);
                }
            };

            mXBanner.loadImage(mXBannerAdapter);
            mXBanner.setPageTransformer(Transformer.Default);
            mXBanner.setPageTransformer(Transformer.Alpha);
            mXBanner.setPageTransformer(Transformer.ZoomFade);
            mXBanner.setPageTransformer(Transformer.ZoomCenter);
            mXBanner.setPageTransformer(Transformer.ZoomStack);
            mXBanner.setPageTransformer(Transformer.Stack);
            mXBanner.setPageTransformer(Transformer.Depth);
            mXBanner.setPageTransformer(Transformer.Zoom);
            mXBanner.setPageChangeDuration(1);

        }else if (data instanceof SearchBean){
            SearchBean searchBean = (SearchBean) data;
            List<SearchBean.ResultBean> result = searchBean.getResult();

            if (result.size()==0){  //搜索失败展示图片

                SearchAdapter searchAdapter = new SearchAdapter(getActivity(), result);
                mRecyclerView_search.setAdapter(searchAdapter);
                mRecyclerView_search.setLayoutManager(new GridLayoutManager(getActivity(),2));

                mImageView_no.setVisibility(View.VISIBLE);

            }else {   //成功则展示列表
                mImageView_no.setVisibility(View.GONE);

                SearchAdapter searchAdapter = new SearchAdapter(getActivity(), result);
                mRecyclerView_search.setAdapter(searchAdapter);
                mRecyclerView_search.setLayoutManager(new GridLayoutManager(getActivity(),2));


            }

        }else if (data instanceof PopuoneBean){

            PopuoneBean popuoneBean = (PopuoneBean) data;
            List<PopuoneBean.ResultBean> result = popuoneBean.getResult();
            PopuoneAdapter popuoneAdapter = new PopuoneAdapter(getActivity(), result);
            mRecyclerView_popu_one.setAdapter(popuoneAdapter);
            mRecyclerView_popu_one.setLayoutManager(new GridLayoutManager(getActivity(),9));

            popuoneAdapter.setOnItemClickListener(new PopuoneAdapter.onItemClickListener() {
                @Override
                public void onItemClick(String id) {
                    mIPrecenter.startRequestData(String.format(Apis.URL_CIRCLE_TWO_LIST,id),null, PoputwoBean.class,1,null,null);

                }
            });
        }else if (data instanceof PoputwoBean){

            PoputwoBean poputwoBean = (PoputwoBean) data;
            List<PoputwoBean.ResultBean> result = poputwoBean.getResult();
            PoputwoAdapter poputwoAdapter = new PoputwoAdapter(getActivity(),result);
            mRecyclerView_popu_two.setAdapter(poputwoAdapter);
            mRecyclerView_popu_two.setLayoutManager(new GridLayoutManager(getActivity(),12));

            poputwoAdapter.setOnItemClickListener(new PoputwoAdapter.onItemClickListener() {
                @Override
                public void onItemClick(String id) {

                }
            });
        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mIPrecenter.onDetach();
    }
}
