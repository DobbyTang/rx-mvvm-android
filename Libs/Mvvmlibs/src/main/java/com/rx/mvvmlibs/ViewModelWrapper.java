package com.rx.mvvmlibs;

import android.app.ProgressDialog;
import android.databinding.ViewDataBinding;

import com.rx.mvvmlibs.bean.ProgressBean;
import com.rx.mvvmlibs.databinding.ActivityMvvmBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmBinding;
import com.rx.mvvmlibs.databinding.DefaultProgressBinding;
import com.rx.mvvmlibs.databinding.ErrorBinding;

import javax.inject.Inject;

/**
 * @ClassName: ViewModelWrapper
 * @author create by Tang
 * @date date 16/11/16 上午11:44
 * @Description:
 * 用于给ViewModel注入需要的对象
 */

public class ViewModelWrapper {

    @Inject
    ProgressBean progress;

    @Inject
    IModel model;

    @Inject
    ActivityMvvmBinding activityMvvmBinding;

    @Inject
    ContentMvvmBinding contentMvvmBinding;

    @Inject
    public ViewDataBinding childBinding;

    @Inject
    DefaultProgressBinding defaultProgressBinding;

    @Inject
    ErrorBinding errorBinding;

    @Inject
    ProgressDialog progressDialog;

}
