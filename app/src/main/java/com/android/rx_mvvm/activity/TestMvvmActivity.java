package com.android.rx_mvvm.activity;

import com.android.rx_mvvm.viewModel.TestViewModel;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.view.MvvmActivity;
import com.rx.utillibs.LogUtil;

/**
 * @ClassName: TestMvvmActivity
 * @author create by Tang
 * @date date 16/11/15 上午11:37
 * @Description: TODO
 */

public class TestMvvmActivity extends MvvmActivity {


    TestViewModel testViewModel;

    @Override
    public ViewModel onBindingViewModel() {
        LogUtil.d("");
        testViewModel = new TestViewModel(this);

        return testViewModel;
    }

    @Override
    public void init() {
        super.init();
    }
}
