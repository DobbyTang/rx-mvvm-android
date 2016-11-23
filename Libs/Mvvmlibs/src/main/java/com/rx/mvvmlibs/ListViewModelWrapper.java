package com.rx.mvvmlibs;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.rx.mvvmlibs.bean.ErrorBean;
import com.rx.mvvmlibs.databinding.ContentRetrofitMvvmListBinding;
import com.rx.mvvmlibs.databinding.ErrorBinding;
import com.rx.mvvmlibs.view.BindingListAdapter;

import javax.inject.Inject;

/**
 * @ClassName: ListViewModelWrapper
 * @author create by Tang
 * @date date 16/11/18 下午3:52
 * @Description: TODO
 */

public class ListViewModelWrapper {

    @Inject
    ContentRetrofitMvvmListBinding contentMvvmListBinding;

    @Inject
    RecyclerView recyclerView;

    @Inject
    BindingListAdapter adapter;

    @Inject
    RecyclerView.LayoutManager layoutManager;

    @Inject
    ErrorBinding errorBinding;

    @Inject
    ErrorBean error;

    @Inject
    public IModel model;

    @Inject
    public ListViewModelWrapper(){

    }
}