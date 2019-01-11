package com.tech.wd.ffecommerceproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class DetailScrollView extends ScrollView{
    public DetailScrollView(Context context) {
        this(context,null);
    }

    public DetailScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DetailScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollViewListener !=null){
            mScrollViewListener.onScrollChange(this,l,t,oldl,oldt);
        }
    }


    public interface ScrollViewListener{
        void onScrollChange(DetailScrollView scrollView, int l, int t, int oldl, int oldt);
    }

    private ScrollViewListener  mScrollViewListener;

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        mScrollViewListener = scrollViewListener;
    }
}
