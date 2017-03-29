package com.android.rx_mvvm.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.rx_mvvm.BaiduApi;
import com.android.rx_mvvm.TestAdapter;
import com.android.rx_mvvm.bean.NuoMiCategoryBean;
import com.android.rx_mvvm.viewModel.TestListViewModel;
import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.Result;
import com.rx.mvvmlibs.view.BindingListAdapter;
import com.rx.mvvmlibs.view.ListMvvmActivity;
import com.rx.utillibs.LogUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @ClassName: TestListMvvmActivity
 * @author create by Tang
 * @date date 16/11/21 下午5:23
 * @Description: TODO
 */

public class TestListMvvmActivity extends ListMvvmActivity {

    @Override
    public ListViewModel onBindingViewModel() {
        return new TestListViewModel(this);
    }
}
