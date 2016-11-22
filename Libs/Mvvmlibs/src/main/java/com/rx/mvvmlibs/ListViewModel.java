package com.rx.mvvmlibs;

import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.rx.mvvmlibs.component.DaggerListViewModelComponent;
import com.rx.mvvmlibs.module.ListViewModelModule;
import com.rx.mvvmlibs.network.Error;
import com.rx.mvvmlibs.view.ListMvvmActivity;
import com.rx.utillibs.LogUtil;

import java.util.List;


/**
 * @ClassName: ListViewModel
 * @author create by Tang
 * @date date 16/11/18 下午3:49
 * @Description: 用于处理列表型数据
 */

public abstract class ListViewModel<Data extends List> implements IListViewModel<Data>,IErrorInfo{

    //分页取数据数，默认为10
    private int count = 10;

    private boolean isSuccess;

    public ListViewModelWrapper viewModelWrapper;


    private ListMvvmActivity activity;


    Data data;

    public ListViewModel(ListMvvmActivity activity){
        this.activity = activity;
        viewModelWrapper = new ListViewModelWrapper();
        init();
        viewModelWrapper.contentMvvmListBinding.refreshLayout
                .setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP
                        ,24, activity.getResources().getDisplayMetrics()));
        enqueue();
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
            onSuccess();
            viewModelWrapper.adapter.setData(data);
        }else {
            onError(result.errNum,result.errMsg);
        }
        
    }

    @Override
    public void onSuccess() {
        isSuccess = true;
        viewModelWrapper.recyclerView.setVisibility(View.VISIBLE);
        viewModelWrapper.errorBinding.getRoot().setVisibility(View.GONE);
        showProgress(false);
    }

    @Override
    public void onNetworkError(Throwable e) {
        onError(Error.CODE_NETWORK,Error.DESC_NETWORK);
    }

    @Override
    public void showProgress(boolean enable) {
        viewModelWrapper.contentMvvmListBinding.refreshLayout.setRefreshing(enable);
    }

    @Override
    public void init() {
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
    public void setErrorImageResource(Drawable drawable) {
        viewModelWrapper.error.drawable.set(drawable);
    }

    @Override
    public void setErrorString(String msg) {
        viewModelWrapper.error.message.set(msg);
    }

    @Override
    public void onError(int errorCode, String errorDesc) {
        showProgress(false);
        if (!isSuccess){
            viewModelWrapper.recyclerView.setVisibility(View.GONE);
            viewModelWrapper.errorBinding.getRoot().setVisibility(View.VISIBLE);
            setErrorString(errorDesc);
        }
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
        enqueue();
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void refreshIndexPage() {

    }
}
