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
    public static final String URL_HOMEPAGE_SHOP_XQ="http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=%s";
    //点赞
    public static final String URL_CIRCLE_DZ="http://172.17.8.100/small/circle/verify/v1/addCircleGreat";
    //取消点赞
    public static final String URL_CIRCLE_QZ_DZ="http://172.17.8.100/small/circle/verify/v1/cancelCircleGreat";
    //根据二级类目查询商品信息
    public static final String URL_BY_TWO_LIST_SHOW_LIST="http://172.17.8.100/small/commodity/v1/findCommodityByCategory?categoryId=%s";
    //我的足迹
    public static final String URL_MY_FOOTS="http://172.17.8.100/small/commodity/verify/v1/browseList?page=1&count=5";
    //同步购物车
    public static final String URL_ADD_SHOP_CAT="http://172.17.8.100/small/order/verify/v1/syncShoppingCart";
    //查询购物车
    public static final String URL_SELECT_SHOP_CAT="http://172.17.8.100/small/order/verify/v1/findShoppingCart";
    //修改用户密码
    public static final String URL_UPDATE_PASSWORD="http://172.17.8.100/small/user/verify/v1/modifyUserPwd";
    //收货地址列表
    public static final String URL_ADDRESS_SHOW="http://172.17.8.100/small/user/verify/v1/receiveAddressList";
    //新增收货地址
    public static final String URL_ADD_NEW_ADDRESS="http://172.17.8.100/small/user/verify/v1/addReceiveAddress";
    //设置默认收货地址
    public static final String URL_SET_MORE_ADDRESS="http://172.17.8.100/small/user/verify/v1/setDefaultReceiveAddress";
    //修改收货地址
    public static final String URL_UPDATE_ADDRESS="http://172.17.8.100/small/user/verify/v1/changeReceiveAddress";
    //全部订单
    public static final String URL_AllOrders="http://172.17.8.100/small/order/verify/v1/findOrderListByStatus?status=%s&page=1&count=5";
    //创建订单
    public static final String URL_CreateOrders="http://172.17.8.100/small/order/verify/v1/createOrder";
    //支付
    public static final String URL_Zf="http://172.17.8.100/small/order/verify/v1/pay";
    //删除订单
    public static final String URL_DeleteDd="http://172.17.8.100/small/order/verify/v1/deleteOrder";
    //收货
    public static final String URL_TakeGoods="http://172.17.8.100/small/order/verify/v1/confirmReceipt";
    //商品评论
    public static final String URL_COMMENT="http://172.17.8.100/small/commodity/verify/v1/addCommodityComment";


}
