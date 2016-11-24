package com.rx.mvvmlibs.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.component.DaggerListMvvmFragmentComponent;
import com.rx.mvvmlibs.databinding.ContentMvvmListBinding;
import com.rx.mvvmlibs.module.BindListViewModelModule;
import com.rx.mvvmlibs.view.iview.BindListViewModel;
import com.rx.mvvmlibs.view.iview.IListMvvmFragment;

import javax.inject.Inject;

/**
 * @ClassName: ListMvvmFragment
 * @author create by Tang
 * @date date 16/11/23 下午4:15
 * @Description: TODO
 */

public abstract class ListMvvmFragment extends Fragment implements IListMvvmFragment,BindListViewModel {

    @Inject
    ListViewModel listViewModel;

    private ContentMvvmListBinding contentListBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentListBinding = DataBindingUtil.inflate(inflater,R.layout.content_mvvm_list,container,false);
        init();
        return contentListBinding.getRoot();
    }


    @Override
    public ContentMvvmListBinding getContentMvvmListBinding() {
        return contentListBinding;
    }

    @Override
    public void init() {
        DaggerListMvvmFragmentComponent
                .builder()
                .bindListViewModelModule(new BindListViewModelModule(this))
                .build()
                .inject(this);
    }
}
