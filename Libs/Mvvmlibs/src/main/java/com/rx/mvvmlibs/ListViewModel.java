package com.rx.mvvmlibs;

import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.rx.mvvmlibs.component.DaggerListViewModelComponent;
import com.rx.mvvmlibs.module.ListMvvmActivityModule;
import com.rx.mvvmlibs.module.ListViewModelModule;
import com.rx.mvvmlibs.view.ListMvvmActivity;

import java.util.List;

import rx.Observable;

/**
 * @ClassName: ListViewModel
 * @author create by Tang
 * @date date 16/11/18 下午3:49
 * @Description: 用于处理列表型数据
 */

public class ListViewModel<Data extends List> implements IListViewModel<Data>,IErrorInfo{

    public ListViewModelWrapper viewModelWrapper;

    private ListMvvmActivity acitivty;

    Data data;

    public ListViewModel(ListMvvmActivity acitivty){
        this.acitivty = acitivty;
        init();
    }

    @Override
    public void enqueue() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public Observable setApiInterface() {
        return null;
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

    }

    @Override
    public void onNetworkError(Throwable e) {

    }

    @Override
    public void showProgress(boolean enable) {

    }

    @Override
    public void init() {
        viewModelWrapper = new ListViewModelWrapper();
        DaggerListViewModelComponent.builder()
                .listViewModelModule(new ListViewModelModule(this,acitivty))
                .build()
                .inject(viewModelWrapper);

        viewModelWrapper.errorBinding.setError(viewModelWrapper.error);
        acitivty.setSupportActionBar(viewModelWrapper.activityMvvmListBinding.toolbar);
        acitivty.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        enqueue();
    }

    @Override
    public void loading () {

    }

    @Override
    public void setCount(int count) {

    }

    @Override
    public void refreshIndexPage() {

    }
}
