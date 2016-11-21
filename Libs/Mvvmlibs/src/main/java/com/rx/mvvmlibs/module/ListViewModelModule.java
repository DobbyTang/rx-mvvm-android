package com.rx.mvvmlibs.module;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.rx.mvvmlibs.IErrorInfo;
import com.rx.mvvmlibs.IModel;
import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.Model;
import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.bean.ErrorBean;
import com.rx.mvvmlibs.databinding.ActivityMvvmListBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmListBinding;
import com.rx.mvvmlibs.databinding.ErrorBinding;
import com.rx.mvvmlibs.scope.ListViewModelScope;
import com.rx.mvvmlibs.view.BindingListAdapter;
import com.rx.mvvmlibs.view.ListMvvmActivity;
import com.rx.utillibs.LogUtil;

import dagger.Module;
import dagger.Provides;

/**
 * @ClassName: ListViewModelModule
 * @author create by Tang
 * @date date 16/11/19 下午3:57
 * @Description: TODO
 */

@Module
public class ListViewModelModule {

    private ListViewModel listViewModel;
    private ListMvvmActivity activity;
    private IErrorInfo errorInfo;

    public ListViewModelModule(ListViewModel listViewModel, ListMvvmActivity activity){
        this.listViewModel = listViewModel;
        this.errorInfo = listViewModel;
        this.activity = activity;
    }

    @ListViewModelScope
    @Provides
    public ActivityMvvmListBinding providesActivityMvvmListBinding(){

        return DataBindingUtil
                .setContentView(activity, R.layout.activity_mvvm_list);
    }

    @ListViewModelScope
    @Provides
    public ContentMvvmListBinding providesContentMvvmListBinding(ActivityMvvmListBinding activityMvvmBinding){
        LogUtil.d(getClass(), "providesContentMvvmBinding: ");
        return activityMvvmBinding.contentMvvmList;
    }

    @ListViewModelScope
    @Provides
    public RecyclerView providesRecyclerView(ContentMvvmListBinding contentMvvmBinding){

        return contentMvvmBinding.recyclerView;
    }

    @ListViewModelScope
    @Provides
    public BindingListAdapter providesBindingListAdapter(){
        return listViewModel.setAdapter();
    }

    @ListViewModelScope
    @Provides
    public ErrorBinding providesErrorBinding(ContentMvvmListBinding contentMvvmBinding){
        return contentMvvmBinding.error;
    }

    @ListViewModelScope
    @Provides
    public ErrorBean providesError(){
        return new ErrorBean();
    }

    @ListViewModelScope
    @Provides
    public IModel providesModel(){
        return new Model(listViewModel);
    }


}
