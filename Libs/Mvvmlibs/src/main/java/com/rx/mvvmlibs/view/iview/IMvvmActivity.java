package com.rx.mvvmlibs.view.iview;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rx.mvvmlibs.ViewModel;


/**
 * @ClassName: IMvvmActivity
 * @author create by Tang
 * @date date 16/11/15 下午3:12
 * @Description: IMvvmActivity
 */

public interface IMvvmActivity {

    /**
     * @Method: onCreateView
     * @author create by Tang
     * @date date 16/11/15 下午4:01
     * @Description: 创建子Activity布局
     */
    ViewDataBinding onCreateView(LayoutInflater inflater, ViewGroup parent);

    /**
     * @Method: onCreateViewModel
     * @author create by Tang
     * @date date 16/11/15 下午4:02
     * @Description:
     * 绑定ViewModel
     * 必须在该方法中生成ViewModel实例
     */
    ViewModel onBindingViewModel();






}
