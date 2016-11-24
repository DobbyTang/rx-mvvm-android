package com.rx.mvvmlibs.view.iview;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rx.mvvmlibs.databinding.ActivityBaseBinding;

/**
 * @ClassName: IBaseActivity
 * @author create by Tang
 * @date date 16/10/26 下午1:47
 * @Description: BaseActivity对应接口
 */

public interface IBaseActivity {

    ViewDataBinding setContentBinding(LayoutInflater inflater, ViewGroup container);

    ActivityBaseBinding getBaseBinding();

    void init();
    
}
