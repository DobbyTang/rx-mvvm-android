package com.android.rx_mvvm;

import com.rx.mvvmlibs.RetrofitViewModel;
import com.rx.mvvmlibs.view.RetrofitMvvmActivity;
import com.rx.utillibs.LogUtil;

/**
 * @ClassName: TestRetrofitMvvmActivity
 * @author create by Tang
 * @date date 16/11/15 上午11:37
 * @Description: TODO
 */

public class TestRetrofitMvvmActivity extends RetrofitMvvmActivity {


    TestRetrofitViewModel testViewModel;

    @Override
    public RetrofitViewModel onBindingViewModel() {
        LogUtil.d("");
        testViewModel = new TestRetrofitViewModel(this);

        return testViewModel;
    }

    @Override
    public void init() {
        super.init();
    }
}
