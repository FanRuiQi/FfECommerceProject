package com.tech.wd.ffecommerceproject.model;

import com.tech.wd.ffecommerceproject.callback.MyCallBack;

import java.util.Map;

public interface IModel {

    void requestData(String url, Map<String,String> map, Class clazz,int type, String userId, String sessionId, MyCallBack myCallBack);
}
