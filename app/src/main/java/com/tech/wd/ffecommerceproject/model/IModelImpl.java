package com.tech.wd.ffecommerceproject.model;

import com.google.gson.Gson;
import com.tech.wd.ffecommerceproject.callback.MyCallBack;
import com.tech.wd.ffecommerceproject.netutil.HttpUtil;

import java.util.Map;

public class IModelImpl implements IModel{
    @Override
    public void requestData(String url, Map<String, String> map, final Class clazz,int type, String userId, String sessionId, final MyCallBack myCallBack) {

        if (type==0){
            HttpUtil.getInterface().doPost(url, map, new HttpUtil.HttpUtilInterface() {
                @Override
                public void HttpFailure() {

                }

                @Override
                public void HttpResponse(String json) {

                    if (myCallBack!=null){
                        Object o = new Gson().fromJson(json, clazz);
                        myCallBack.setData(o);
                    }
                }
            });
        }else if (type==1){
            HttpUtil.getInterface().doGet(url, new HttpUtil.HttpUtilInterface() {
                @Override
                public void HttpFailure() {

                }

                @Override
                public void HttpResponse(String json) {

                    if (myCallBack!=null){
                        Object o = new Gson().fromJson(json, clazz);
                        myCallBack.setData(o);
                    }
                }
            });
        }else if (type==2){
            HttpUtil.getInterface().doHeadGet(url, userId, sessionId, new HttpUtil.HttpUtilInterface() {
                @Override
                public void HttpFailure() {

                }

                @Override
                public void HttpResponse(String json) {
                    Object o = new Gson().fromJson(json, clazz);
                    myCallBack.setData(o);
                }
            });
        }else if (type==3){
            HttpUtil.getInterface().doHeadPost(url,map, userId, sessionId, new HttpUtil.HttpUtilInterface() {
                @Override
                public void HttpFailure() {

                }

                @Override
                public void HttpResponse(String json) {

                    Object o = new Gson().fromJson(json, clazz);
                    myCallBack.setData(o);
                }
            });
        }else if (type==4){
            HttpUtil.getInterface().doHeadPostDelete(url,map, userId, sessionId, new HttpUtil.HttpUtilInterface() {
                @Override
                public void HttpFailure() {

                }

                @Override
                public void HttpResponse(String json) {

                    Object o = new Gson().fromJson(json, clazz);
                    myCallBack.setData(o);
                }
            });
        }else if (type==5){
            HttpUtil.getInterface().doHeadPut(url,map, userId, sessionId, new HttpUtil.HttpUtilInterface() {
                @Override
                public void HttpFailure() {

                }

                @Override
                public void HttpResponse(String json) {

                    Object o = new Gson().fromJson(json, clazz);
                    myCallBack.setData(o);
                }
            });
        }else if (type==6){
            HttpUtil.getInterface().doHeadPostPut(url,map, userId, sessionId, new HttpUtil.HttpUtilInterface() {
                @Override
                public void HttpFailure() {

                }

                @Override
                public void HttpResponse(String json) {

                    Object o = new Gson().fromJson(json, clazz);
                    myCallBack.setData(o);
                }
            });
        }

    }
}
