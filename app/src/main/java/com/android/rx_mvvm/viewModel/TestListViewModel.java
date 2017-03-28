package com.android.rx_mvvm.viewModel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.rx_mvvm.BaiduApi;
import com.android.rx_mvvm.TestAdapter;
import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.view.BindingListAdapter;
import com.rx.mvvmlibs.view.ListMvvmActivity;
import com.rx.mvvmlibs.view.ListMvvmFragment;
import com.rx.utillibs.LogUtil;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @ClassName: TestListMvvmActivity
 * @author create by Tang
 * @date date 16/11/21 下午5:24
 * @Description: TODO
 */

public class TestListViewModel extends ListViewModel {


    public TestListViewModel(ListMvvmActivity activity) {
        super(activity);
    }

    public TestListViewModel(ListMvvmFragment fragment) {
        super(fragment);
    }

    @Override
    public Observable setApiInterface(Retrofit retrofit) {
        return retrofit.create(BaiduApi.class).rxGetCategory();
    }

    @Override
    public BindingListAdapter setAdapter() {
        LogUtil.d("setAdapter");
        return new TestAdapter();
    }

    @Override
    public RecyclerView.LayoutManager setLayoutManager(Context context) {
        LogUtil.d("setLayoutManager");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }

    @Override
    public void init() {
        super.init();
        viewModelWrapper.model
                .getBuilder()
                .addHeader("apikey","05cecef32508c4bd5853a0fed178e322");
    }
}
