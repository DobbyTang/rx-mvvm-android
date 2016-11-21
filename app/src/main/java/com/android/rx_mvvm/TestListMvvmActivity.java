package com.android.rx_mvvm;

import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.view.ListMvvmActivity;

/**
 * @ClassName: TestListMvvmActivity
 * @author create by Tang
 * @date date 16/11/21 下午5:23
 * @Description: TODO
 */

public class TestListMvvmActivity extends ListMvvmActivity{
    @Override
    public ListViewModel onReBindingViewModel() {
        return new TestListViewModel(this);
    }
}
