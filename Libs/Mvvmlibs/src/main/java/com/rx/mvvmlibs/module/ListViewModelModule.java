package com.rx.mvvmlibs.module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.rx.mvvmlibs.IModel;
import com.rx.mvvmlibs.RetrofitListViewModel;
import com.rx.mvvmlibs.RetrofitModel;
import com.rx.mvvmlibs.bean.ErrorBean;
import com.rx.mvvmlibs.databinding.ContentRetrofitMvvmListBinding;
import com.rx.mvvmlibs.databinding.ErrorBinding;
import com.rx.mvvmlibs.scope.ListViewModelScope;
import com.rx.mvvmlibs.view.BindingListAdapter;

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

    private RetrofitListViewModel listViewModel;
    private Context context;
    private ContentRetrofitMvvmListBinding contentMvvmListBinding;


    public ListViewModelModule(RetrofitListViewModel listViewModel, Context context
            , ContentRetrofitMvvmListBinding binding){

        this.listViewModel = listViewModel;
        this.context = context;
        this.contentMvvmListBinding = binding;
    }

    @ListViewModelScope
    @Provides
    public ContentRetrofitMvvmListBinding providesContentMvvmListBinding(){
        return contentMvvmListBinding;
    }

    @ListViewModelScope
    @Provides
    public RecyclerView providesRecyclerView(ContentRetrofitMvvmListBinding contentMvvmBinding
            ,BindingListAdapter adapter, RecyclerView.LayoutManager layoutManager){

        contentMvvmBinding.recyclerView.setLayoutManager(layoutManager);
        contentMvvmBinding.recyclerView.setAdapter(adapter);
        return contentMvvmBinding.recyclerView;
    }

    @ListViewModelScope
    @Provides
    public BindingListAdapter providesBindingListAdapter(){
        return listViewModel.setAdapter();
    }

    @ListViewModelScope
    @Provides
    public RecyclerView.LayoutManager providesLayoutManager(){
        return listViewModel.setLayoutManager(context);
    }

    @ListViewModelScope
    @Provides
    public ErrorBinding providesErrorBinding(ContentRetrofitMvvmListBinding contentMvvmBinding){
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
        return new RetrofitModel(listViewModel);
    }


}
