package com.android.rx_mvvm.fragment;

import com.android.rx_mvvm.viewModel.TestListViewModel;
import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.view.ListMvvmFragment;

/**
 * Created by guohao on 16/11/23.
 */

public class TestMvvmListFragment extends ListMvvmFragment {
    @Override
    public ListViewModel onBindingViewModel() {
        return new TestListViewModel(this);
    }
}
