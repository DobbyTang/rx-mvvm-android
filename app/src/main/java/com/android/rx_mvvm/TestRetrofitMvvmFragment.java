package com.android.rx_mvvm;

import com.android.rx_mvvm.databinding.ActivityTestBinding;
import com.rx.mvvmlibs.RetrofitViewModel;
import com.rx.mvvmlibs.view.RetrofitMvvmFragment;

/**
 * @ClassName: TestRetrofitMvvmFragment
 * @author create by Tang
 * @date date 16/11/23 上午11:12
 * @Description: TODO
 */

public class TestRetrofitMvvmFragment extends RetrofitMvvmFragment {

    private ActivityTestBinding binding;

    TestRetrofitViewModel testViewModel;

    @Override
    public RetrofitViewModel onBindingViewModel() {
        testViewModel = new TestRetrofitViewModel(this);
        return testViewModel;
    }
}
