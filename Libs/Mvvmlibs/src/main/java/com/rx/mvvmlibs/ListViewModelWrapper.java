package com.rx.mvvmlibs;

import com.rx.mvvmlibs.bean.ErrorBean;

import javax.inject.Inject;

/**
 * @ClassName: ListViewModelWrapper
 * @author create by Tang
 * @date date 16/11/18 下午3:52
 * @Description: TODO
 */

public class ListViewModelWrapper {

    @Inject
    ErrorBean error;

    @Inject
    IModel model;
}
