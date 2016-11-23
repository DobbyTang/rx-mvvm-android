package com.android.rx_mvvm;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rx.mvvmlibs.RetrofitListViewModel;
import com.rx.mvvmlibs.view.BindingListAdapter;
import com.rx.mvvmlibs.view.RetrofitListMvvmActivity;
import com.rx.utillibs.LogUtil;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @ClassName: TestRetrofitListMvvmActivity
 * @author create by Tang
 * @date date 16/11/21 下午5:23
 * @Description: TODO
 */

public class TestRetrofitListMvvmActivity extends RetrofitListMvvmActivity {

    @Override
    public RetrofitListViewModel onBindingViewModel() {
        return new RetrofitListViewModel(this) {
            @Override
            public ViewDataBinding onCreateBinding(LayoutInflater inflater, ViewGroup parent) {
                return null;
            }

            @Override
            public Observable setApiInterface(Retrofit retrofit) {
                return retrofit.create(BaiduApi.class).rxGetCategory();
            }

            @Override
            public BindingListAdapter setAdapter() {
                LogUtil.d("setAdapter");
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
                setCount(7);
            }
        };
    }
}
