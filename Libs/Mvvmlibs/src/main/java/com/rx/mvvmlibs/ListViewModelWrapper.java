package com.rx.mvvmlibs;

import android.databinding.ViewDataBinding;

import com.rx.mvvmlibs.bean.ErrorBean;
import com.rx.mvvmlibs.databinding.ActivityMvvmListBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmListBinding;
import com.rx.mvvmlibs.databinding.ErrorBinding;

import javax.inject.Inject;

/**
 * @ClassName: ListViewModelWrapper
 * @author create by Tang
 * @date date 16/11/18 下午3:52
 * @Description: TODO
 */

public class ListViewModelWrapper {

    @Inject
    ActivityMvvmListBinding activityMvvmListBinding;

    @Inject
    ContentMvvmListBinding contentMvvmListBinding;

    @Inject
    ViewDataBinding childBinding;

    @Inject
    ErrorBinding errorBinding;

    @Inject
    ErrorBean error;

    @Inject
    IModel model;
}
