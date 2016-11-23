package com.android.rx_mvvm;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.rx_mvvm.bean.NuoMiCategoryBean;
import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.view.BindingListAdapter;
import com.rx.mvvmlibs.view.ListMvvmActivity;
import com.rx.utillibs.LogUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @ClassName: TestListViewModel
 * @author create by Tang
 * @date date 16/11/21 下午5:24
 * @Description: TODO
 */

public class TestListViewModel extends ListViewModel<List<NuoMiCategoryBean>>{


    public TestListViewModel(ListMvvmActivity activity) {
        super(activity);
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
    public RecyclerView.LayoutManager setLayoutManager(Activity activity) {
        LogUtil.d("setLayoutManager");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
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
