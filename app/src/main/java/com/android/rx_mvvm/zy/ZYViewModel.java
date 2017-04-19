package com.android.rx_mvvm.zy;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.rx_mvvm.R;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.view.MvvmActivity;
import com.rx.utillibs.LogUtil;

/**
 * @className: ZYViewModel
 * @author create by Tang
 * @date 2017/4/19 下午2:47
 * @description:
 */

public class ZYViewModel extends ViewModel{

    private ZYModel model;

    public ZYViewModel(MvvmActivity activity) {
        super(activity);
    }

    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, ViewGroup parent) {
        return DataBindingUtil.inflate(inflater, R.layout.content_main,parent,false);
    }

    @Override
    public void onReconnection() {
        super.onReconnection();
        model.enqueueRequest();
    }

    @Override
    public void init() {
        super.init();
        model = new ZYModel(this);
        model.setOnResult(zyResult -> LogUtil.d(zyResult.zhaiyan));
    }
}
