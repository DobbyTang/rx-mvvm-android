package com.android.rx_mvvm.fragment;

import com.android.rx_mvvm.viewModel.TestViewModel;
import com.android.rx_mvvm.databinding.ActivityTestBinding;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.view.MvvmFragment;

/**
 * @ClassName: TestMvvmFragment
 * @author create by Tang
 * @date date 16/11/23 上午11:12
 * @Description: TODO
 */

public class TestMvvmFragment extends MvvmFragment {

    private ActivityTestBinding binding;

    TestViewModel testViewModel;

    @Override
    public ViewModel onBindingViewModel() {
        testViewModel = new TestViewModel(this);
        return testViewModel;
    }
}
