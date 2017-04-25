package com.rx.mvvmlibs;

import android.content.Context;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.rx.mvvmlibs.component.DaggerListViewModelComponent;
import com.rx.mvvmlibs.databinding.ActivityMvvmListBinding;
import com.rx.mvvmlibs.module.ListViewModelModule;
import com.rx.mvvmlibs.network.Error;
import com.rx.mvvmlibs.params.PaginationParams;
import com.rx.mvvmlibs.view.ListMvvmActivity;
import com.rx.mvvmlibs.view.ListMvvmFragment;
import com.rx.utillibs.ListUtils;
import com.rx.utillibs.LogUtil;

import java.util.List;
import java.util.Optional;

import io.reactivex.Observable;
import retrofit2.Retrofit;


/**
 * @ClassName: ListMvvmActivity
 * @author create by Tang
 * @date date 16/11/18 下午3:49
 * @Description: 用于处理列表型数据
 */

@BindingMethods({
        @BindingMethod(type = android.widget.ImageView.class,
                attribute = "app:srcCompat",
                method = "setImageDrawable") })
public abstract class ListViewModel<Data> implements IListViewModel<Data>,IErrorControl {

    //分页取数据数，默认为10
    private int mCount = 10;

    //开始页
    private int mStartPage = 0;

    //需要刷新的数据项位置
    private int mIndex = -1;

    //需要刷新的页码（根据mIndex计算）
    private int mPage;

    PaginationParams paginationParams = setPaginationParams();

    private boolean isSuccess;

    private boolean progressEnable = true;

    public ListViewModelWrapper viewModelWrapper;

    private Context context;

    List<Data> data;

    private RetrofitModel<ListResult<Data>> listModel
            = new RetrofitModel<>(this);

    private int lastVisibleItem;
    private int[] lastPositions;

    public ListViewModel(ListMvvmActivity activity){
        viewModelWrapper = new ListViewModelWrapper();
        this.context = activity;
        initActivity(activity);
        refresh();
    }

    public ListViewModel(ListMvvmFragment fragment){
        viewModelWrapper = new ListViewModelWrapper();
        this.context = fragment.getContext();
        initFragment(fragment);
        refresh();
    }

    @Override
    public void listEnqueue() {
        showProgress(true);
       listModel.getBuilder()
               .addParams(paginationParams.toMap());
        listModel.enqueueRequest();

    }

    @Override
    public void cancel() {
        showProgress(false);
//        viewModelWrapper.model.cancelRequest();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onListResult(ListResult<Data> result) {
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

    }

    @Override
    public void onSuccess() {
        isSuccess = true;
        viewModelWrapper.recyclerView.setVisibility(View.VISIBLE);
        viewModelWrapper.errorBinding.getRoot().setVisibility(View.GONE);
        showProgress(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onNetworkError(Throwable e) {
        onError("network error",Error.CODE_NETWORK,Error.DESC_NETWORK);
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
        listModel.setOnResult(result -> onListResult(result))
                .setApiInterface(retrofit -> setListApiInterface(retrofit));
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

        viewModelWrapper.contentMvvmListBinding.refreshLayout

                .setOnRefreshListener(() -> {
                    progressEnable = false;
                    refresh();
                });
    }

    @Override
    public Drawable setErrorImageDrawable() {
        return null;
    }

    @Override
    public String setErrorString() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onError(String errorApi,int errorCode, String errorDesc) {
        showProgress(false);
        if (!isSuccess){
            viewModelWrapper.recyclerView.setVisibility(View.GONE);
            viewModelWrapper.errorBinding.getRoot().setVisibility(View.VISIBLE);
            Optional<String> errorStr = Optional.ofNullable(setErrorString());
            Optional<Drawable> errorDrawable = Optional.ofNullable(setErrorImageDrawable());
            viewModelWrapper.error.message.set(errorStr.orElse(errorDesc));
            viewModelWrapper.error.drawable.set(errorDrawable
                    .orElseGet(() -> context
                            .getResources()
                            .getDrawable(
                                    RxMvvmApplication.getInstance().setDefaultDrawableResource())));
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
        listEnqueue();
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

            paginationParams.count = mCount;
            paginationParams.page = mPage;

            listEnqueue();
        }
    }

    private void initActivity(ListMvvmActivity activity){
        ActivityMvvmListBinding activityMvvmListBinding
                = DataBindingUtil.setContentView(activity, R.layout.activity_mvvm_list);
        DaggerListViewModelComponent.builder()
                .listViewModelModule(new ListViewModelModule(
                        this,activity,activityMvvmListBinding.contentMvvmList))
                .build()
                .inject(viewModelWrapper);

        activity.setSupportActionBar(activityMvvmListBinding.toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void initFragment(ListMvvmFragment fragment){
        DaggerListViewModelComponent.builder()
                .listViewModelModule(new ListViewModelModule(
                        this,fragment.getContext(),fragment.getContentMvvmListBinding()))
                .build()
                .inject(viewModelWrapper);


        init();
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

    /**
     * @Method: setPaginationParams
     * @author create by Tang
     * @date date 17/3/28 下午3:31
     * @Description: 默认方法，用于设置分页字段
     */
    public PaginationParams setPaginationParams(){
        return new PaginationParams("count","page");
    }

    public Context getContext(){
        return context;
    }

    public Optional<RecyclerView> getRecyclerView(){
        return Optional.of(viewModelWrapper.recyclerView);
    }
}
