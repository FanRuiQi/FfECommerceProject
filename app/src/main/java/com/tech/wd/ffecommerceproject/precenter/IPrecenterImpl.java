package com.tech.wd.ffecommerceproject.precenter;

import com.tech.wd.ffecommerceproject.callback.MyCallBack;
import com.tech.wd.ffecommerceproject.model.IModelImpl;
import com.tech.wd.ffecommerceproject.view.IView;

import java.util.Map;

public class IPrecenterImpl implements IPrecenter{  //MVP-P

    private IView mIView;
    private IModelImpl mIModel;

    public IPrecenterImpl(IView IView) {
        mIView = IView;
        mIModel = new IModelImpl();

    }

    @Override
    public void startRequestData(String url, Map<String, String> map, Class clazz,int type, String userId, String sessionId) {

        mIModel.requestData(url, map, clazz, type,userId, sessionId, new MyCallBack() {
            @Override
            public void setData(Object data) {

                mIView.showData(data);
            }
        });
    }

    public void onDetach(){
        if (mIModel!=null){
            mIModel=null;
        }

        if (mIView!=null){
            mIView=null;
        }
    }
}
