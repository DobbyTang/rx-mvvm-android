package com.rx.mvvmlibs;

import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.rx.mvvmlibs.component.DaggerListViewModelComponent;
import com.rx.mvvmlibs.module.ListViewModelModule;
import com.rx.mvvmlibs.network.BasicParamsInterceptor;
import com.rx.mvvmlibs.view.ListMvvmActivity;

import java.util.List;


/**
 * @ClassName: ListViewModel
 * @author create by Tang
 * @date date 16/11/18 下午3:49
 * @Description: 用于处理列表型数据
 */

public abstract class ListViewModel<Data extends List> implements IListViewModel<Data>,IErrorInfo{

    public ListViewModelWrapper viewModelWrapper;

    private ListMvvmActivity activity;

    Data data;

    public ListViewModel(ListMvvmActivity activity){
        this.activity = activity;
        init();
    }

    @Override
    public void enqueue() {
        showProgress(true);
        viewModelWrapper.model.enqueueRequest();

    }

    @Override
    public void cancel() {
        showProgress(false);
        viewModelWrapper.model.cancelRequest();
    }

    @Override
    public void onResult(Result<Data> result) {
        if (result.errNum == 0){
            if (data == null){
                data = result.data;
            }else {
                data.addAll(result.data);
            }
        }else {
            // TODO: 16/11/19 错误处理
        }
        
    }

    @Override
    public void onSuccess() {
        showProgress(false);
    }

    @Override
    public void onNetworkError(Throwable e) {

    }

    @Override
    public void showProgress(boolean enable) {
        viewModelWrapper.contentMvvmListBinding.refreshLayout.setEnabled(enable);
    }

    @Override
    public void init() {
        viewModelWrapper = new ListViewModelWrapper();
        DaggerListViewModelComponent.builder()
                .listViewModelModule(new ListViewModelModule(this,activity))
                .build()
                .inject(viewModelWrapper);

        viewModelWrapper.errorBinding.setError(viewModelWrapper.error);
        activity.setSupportActionBar(viewModelWrapper.activityMvvmListBinding.toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModelWrapper.contentMvvmListBinding.refreshLayout

                .setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refresh();
                    }
                });

    }

    @Override
    public Drawable setErrorImageResource() {
        return null;
    }

    @Override
    public String setErrorString() {
        return null;
    }

    @Override
    public void onError(int errorCode, String errorDesc) {
        showProgress(false);
    }

    @Override
    public int reSetRecyclerView() {
        return 0;
    }

    @Override
    public void refresh() {
        if (data != null){
            data.clear();
        }
        loading();
    }

    @Override
    public void loading () {
        // TODO: 16/11/19 处理分页数据
        enqueue();
    }

    @Override
    public void setCount(int count) {

    }

    @Override
    public void refreshIndexPage() {

    }
}
