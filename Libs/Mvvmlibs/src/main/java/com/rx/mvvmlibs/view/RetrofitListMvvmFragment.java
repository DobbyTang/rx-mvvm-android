package com.rx.mvvmlibs.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rx.mvvmlibs.RetrofitListViewModel;
import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.component.DaggerRetrofitListMvvmFragmentComponent;
import com.rx.mvvmlibs.databinding.ContentRetrofitMvvmListBinding;
import com.rx.mvvmlibs.module.BindListViewModelModule;
import com.rx.mvvmlibs.view.iview.BindListViewModel;
import com.rx.mvvmlibs.view.iview.IListMvvmFragment;

import javax.inject.Inject;

/**
 * @ClassName: RetrofitListMvvmFragment
 * @author create by Tang
 * @date date 16/11/23 下午4:15
 * @Description: TODO
 */

public abstract class RetrofitListMvvmFragment extends Fragment implements IListMvvmFragment,BindListViewModel {

    @Inject
    RetrofitListViewModel listViewModel;

    private ContentRetrofitMvvmListBinding contentListBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentListBinding = DataBindingUtil.inflate(inflater,R.layout.content_retrofit_mvvm_list,container,false);
        init();
        return contentListBinding.getRoot();
    }


    @Override
    public ContentRetrofitMvvmListBinding getContentMvvmListBinding() {
        return contentListBinding;
    }

    @Override
    public void init() {
        DaggerRetrofitListMvvmFragmentComponent
                .builder()
                .bindListViewModelModule(new BindListViewModelModule(this))
                .build()
                .inject(this);
    }
}
