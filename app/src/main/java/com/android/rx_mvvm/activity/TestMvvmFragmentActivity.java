package com.android.rx_mvvm.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.rx_mvvm.R;
import com.android.rx_mvvm.databinding.ActivityTestFragmentBinding;
import com.rx.mvvmlibs.view.BaseActivity;

/**
 * @ClassName: TestMvvmFragmentActivity
 * @author create by Tang
 * @date date 16/11/23 上午11:21
 * @Description: TODO
 */

public class TestMvvmFragmentActivity extends BaseActivity{


    @Override
    public ViewDataBinding setContentBinding(LayoutInflater inflater, ViewGroup container) {
        ActivityTestFragmentBinding binding
                = DataBindingUtil.inflate(inflater,R.layout.activity_test_fragment,container,false);
        return binding;
    }
}
