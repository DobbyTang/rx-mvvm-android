package com.rx.mvvmlibs;

import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.rx.mvvmlibs.component.DaggerListViewModelComponent;
import com.rx.mvvmlibs.module.ListViewModelModule;
import com.rx.mvvmlibs.network.Error;
import com.rx.mvvmlibs.params.PaginationParams;
import com.rx.mvvmlibs.view.ListMvvmActivity;
import com.rx.utillibs.ListUtils;
import com.rx.utillibs.LogUtil;

import java.util.List;

import javax.inject.Inject;


/**
 * @ClassName: ListViewModel
 * @author create by Tang
 * @date date 16/11/18 下午3:49
 * @Description: 用于处理列表型数据
 */

public abstract class ListViewModel<Data extends List> implements IListViewModel<Data>,IErrorInfo{

    //分页取数据数，默认为10
    private int mCount = 10;

    //开始页
    private int mStartPage = 0;

    //需要刷新的数据项位置
    private int mIndex = -1;

    //需要刷新的页码（根据mIndex计算）
    private int mPage;

    PaginationParams paginationParams = new PaginationParams();

    private boolean isSuccess;

    private boolean progressEnable = true;

    @Inject
    public ListViewModelWrapper viewModelWrapper;


    private ListMvvmActivity activity;

    Data data;

    private int lastVisibleItem;
    private int[] lastPositions;

    public ListViewModel(ListMvvmActivity activity){
        this.activity = activity;
        viewModelWrapper = new ListViewModelWrapper();
        init();
        refresh();
    }

    @Override
    public void enqueue() {
        showProgress(true);
        viewModelWrapper.model
                .getBuilder()
                .addParams(paginationParams.toMap());
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
                if (mIndex < 0){
                    data.addAll(result.data);
                }else {
                    //计算出需要替换的第一个数据在dataList中的位置
                    int start = (mPage - mStartPage) * mCount;
                    LogUtil.d("refresh index = " + mPage);
                    ListUtils.replaceAssign(start,data,result.data);
                    mIndex = -1;
                }
            }
            viewModelWrapper.adapter.setData(data);
            onSuccess();

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
        if (progressEnable){
            viewModelWrapper.contentMvvmListBinding.refreshLayout.setRefreshing(enable);
        }else {
            progressEnable = true;
        }
    }

    @Override
    public void init() {
        DaggerListViewModelComponent.builder()
                .listViewModelModule(new ListViewModelModule(this,activity))
                .build()
                .inject(viewModelWrapper);

        viewModelWrapper.contentMvvmListBinding.recyclerView
                .addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if ( newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == viewModelWrapper.adapter.getItemCount()) {
                    loading();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (viewModelWrapper.layoutManager instanceof LinearLayoutManager){

                    lastVisibleItem = ((LinearLayoutManager)viewModelWrapper.layoutManager)
                            .findLastVisibleItemPosition();

                }else if (viewModelWrapper.layoutManager instanceof GridLayoutManager){

                    lastVisibleItem = ((GridLayoutManager)viewModelWrapper.layoutManager)
                            .findLastVisibleItemPosition();

                }else if (viewModelWrapper.layoutManager instanceof StaggeredGridLayoutManager){

                    StaggeredGridLayoutManager staggeredGridLayoutManager
                            = (StaggeredGridLayoutManager) viewModelWrapper.layoutManager;

                    if (lastPositions == null){
                        lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                    }

                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                    lastVisibleItem = findMax(lastPositions);
                }
            }
        });

        viewModelWrapper.errorBinding.setError(viewModelWrapper.error);
        activity.setSupportActionBar(viewModelWrapper.activityMvvmListBinding.toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModelWrapper.contentMvvmListBinding.refreshLayout

                .setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        progressEnable = false;
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
        LogUtil.d("onRefresh");
        if (data != null){
            data.clear();
        }
        loading();
    }

    @Override
    public void loading () {
        if (data != null){
            paginationParams.page = data.size() / mCount + mStartPage;
        }else {
            paginationParams.page = mStartPage;
        }
        paginationParams.count = mCount;
        enqueue();
    }

    @Override
    public void setCount(int count) {
        this.mCount = count;
    }

    @Override
    public void refreshIndexPage(int index) {
        if (index > data.size()){
            //如果index超出数组长度则加载下一页
            loading();
        }else {
            /**
             * 注需要根据服务器实际情况来计算
             * 这里假设服务器第一页数据的下标为1
             * 如果下表为0，mPage = index / mCount;
             */
            mIndex = index;
            mPage = index / mCount + mStartPage;
            if (paginationParams == null){
                paginationParams =  new PaginationParams();
                paginationParams.count = mCount;
                paginationParams.page = mPage;
            }else {
                paginationParams.count = mCount;
                paginationParams.page = mPage;
            }

            enqueue();
        }
    }

    /**
     * @Method: findMax
     * @author create by Tang
     * @date date 16/10/24 下午3:48
     * @Description: 用于查找int数组中最大的数
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
