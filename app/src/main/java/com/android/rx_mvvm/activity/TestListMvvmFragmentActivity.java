package com.android.rx_mvvm.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.rx_mvvm.R;
import com.rx.mvvmlibs.view.BaseActivity;

/**
 * Created by guohao on 16/11/23.
 */

public class TestListMvvmFragmentActivity extends BaseActivity{

    @Override
    public ViewDataBinding setContentBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater,R.layout.activity_test_fragment,container,false);
    }
}
