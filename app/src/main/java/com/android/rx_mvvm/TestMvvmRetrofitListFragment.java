package com.android.rx_mvvm;

import com.rx.mvvmlibs.RetrofitListViewModel;
import com.rx.mvvmlibs.view.RetrofitListMvvmFragment;

/**
 * Created by guohao on 16/11/23.
 */

public class TestMvvmRetrofitListFragment extends RetrofitListMvvmFragment {
    @Override
    public RetrofitListViewModel onBindingViewModel() {
        return new TestRetrofitListViewModel(this);
    }
}
