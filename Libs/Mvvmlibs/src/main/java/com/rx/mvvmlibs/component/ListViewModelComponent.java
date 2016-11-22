package com.rx.mvvmlibs.component;

import android.support.v7.widget.RecyclerView;

import com.rx.mvvmlibs.ListViewModelWrapper;
import com.rx.mvvmlibs.databinding.ActivityMvvmBinding;
import com.rx.mvvmlibs.databinding.ActivityMvvmListBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmListBinding;
import com.rx.mvvmlibs.module.ListMvvmActivityModule;
import com.rx.mvvmlibs.module.ListViewModelModule;
import com.rx.mvvmlibs.scope.ListViewModelScope;
import com.rx.mvvmlibs.view.BindingListAdapter;
import com.rx.mvvmlibs.view.ListMvvmActivity;

import dagger.Component;

/**
 * @ClassName: ListViewModelComponent
 * @author create by Tang
 * @date date 16/11/18 下午4:03
 * @Description: TODO
 */

@ListViewModelScope
@Component(modules = ListViewModelModule.class)
public interface ListViewModelComponent {

    void inject(ListViewModelWrapper listViewModelWrapper);

    ActivityMvvmListBinding getActivityMvvmBinding();

    ContentMvvmListBinding getContentMvvmBinding();

    BindingListAdapter getAdapter();

    RecyclerView.LayoutManager getLayoutManager();
}
