package com.tech.wd.ffecommerceproject;

public class Apis {

    //注册
    public static final String URL_REGISTER="http://172.17.8.100/small/user/v1/register";
    //登录
    public static final String URL_LOGIN="http://172.17.8.100/small/user/v1/login";
    //首页商品展示
    public static final String URL_HOMEPAGE_SHOW_SHOP="http://172.17.8.100/small/commodity/v1/commodityList";
    //轮播图
    public static final String URL_HOMEPAGE_BANNER="http://172.17.8.100/small/commodity/v1/bannerShow";
    //圈子列表展示
    public static final String URL_CIRCLE_SHOW_LIST="http://172.17.8.100/small/circle/v1/findCircleList?page=4&count=25";
    //查询一级商品类目
    public static final String URL_CIRCLE_ONE_LIST="http://172.17.8.100/small/commodity/v1/findFirstCategory";
    //查询二级商品类目
    public static final String URL_CIRCLE_TWO_LIST="http://172.17.8.100/small/commodity/v1/findSecondCategory?firstCategoryId=%s";
    //搜索列表
    public static final String URL_HOMEPAGE_SEARCH_LIST="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=%s&page=2&count=5";
    //商品详情
    public static final String URL_HOMEPAGE_SHOP_XQ="http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=$s";
    //点赞
    public static final String URL_CIRCLE_DZ="http://172.17.8.100/small/circle/verify/v1/addCircleGreat";
    //取消点赞
    public static final String URL_CIRCLE_QZ_DZ="http://172.17.8.100/small/circle/verify/v1/cancelCircleGreat";
    //根据二级类目查询商品信息
    public static final String URL_BY_TWO_LIST_SHOW_LIST="http://172.17.8.100/small/commodity/v1/findCommodityByCategory?categoryId=%s";


}
