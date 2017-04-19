package com.rx.mvvmlibs;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;


/**
 * @ClassName: IViewModel
 * @author create by Tang
 * @date date 16/11/14 下午3:38
 * @Description: TODO
 */

public interface IViewModel extends IRetrofitViewModel{

    /**
     * @Method: onCreateView
     * @author create by Tang
     * @date date 16/11/15 下午4:01
     * @Description: 创建子布局
     */
    ViewDataBinding onCreateBinding(LayoutInflater inflater, ViewGroup parent);

    /**
     * @Method: init
     * @author create by Tang
     * @date date 16/11/18 上午10:54
     * @Description: 可以在此方法作一些初始化操作
     */
    void init();




}
