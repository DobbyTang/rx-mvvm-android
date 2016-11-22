package com.android.rx_mvvm;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.view.BindingListAdapter;
import com.rx.mvvmlibs.view.ListMvvmActivity;
import com.rx.utillibs.LogUtil;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @ClassName: TestListMvvmActivity
 * @author create by Tang
 * @date date 16/11/21 下午5:23
 * @Description: TODO
 */

public class TestListMvvmActivity extends ListMvvmActivity{

    @Override
    public ListViewModel onBindingViewModel() {
        return new ListViewModel(this) {
            @Override
            public Observable setApiInterface(Retrofit retrofit) {
                return retrofit.create(BaiduApi.class).rxGetCategory();
            }

            @Override
            public BindingListAdapter setAdapter() {
                LogUtil.d(TestListMvvmActivity.this.getClass(),"setAdapter");
                TestAdapter adapter = new TestAdapter();
                adapter.setOnItemClickListener(new BindingListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        refreshIndexPage(position);
                    }
                });
                return adapter;
            }

            @Override
            public RecyclerView.LayoutManager setLayoutManager(Activity activity) {
                LogUtil.d(TestListMvvmActivity.this.getClass(),"setLayoutManager");
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
                setCount(7);
            }
        };
    }
}
