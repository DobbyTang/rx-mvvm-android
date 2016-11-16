package com.android.rx_mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rx.mvvmlibs.IViewModel;
import com.rx.mvvmlibs.view.MvvmActivity;

/**
 * @ClassName: TestMvvmActivity
 * @author create by Tang
 * @date date 16/11/15 上午11:37
 * @Description: TODO
 */

public class TestMvvmActivity extends MvvmActivity{

    @Override
    public ViewDataBinding onCreateView(LayoutInflater inflater, ViewGroup parent) {
        new TestViewModel(this);
        return DataBindingUtil.inflate(inflater,R.layout.content_main,parent,false);
    }

    @Override
    public IViewModel setViewModel() {
        return null;
    }
}
