package com.tech.wd.ffecommerceproject.precenter;

import com.tech.wd.ffecommerceproject.callback.MyCallBack;

import java.util.Map;

public interface IPrecenter {

    void startRequestData(String url, Map<String, String> map, Class clazz,int type ,String userId, String sessionId);
}
