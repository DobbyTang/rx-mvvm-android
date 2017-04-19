package com.android.rx_mvvm;

import com.android.rx_mvvm.zy.ZYViewModel;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.view.MvvmActivity;

/**
 * Created by user on 2017/4/19.
 */

public class ZyActivity extends MvvmActivity {

    ZYViewModel viewModel ;

    @Override
    public ViewModel onBindingViewModel() {
        viewModel = new ZYViewModel(this);
        return viewModel;
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.onReconnection();
    }
}
