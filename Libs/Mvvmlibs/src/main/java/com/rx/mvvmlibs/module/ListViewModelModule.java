package com.rx.mvvmlibs.module;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;

import com.rx.mvvmlibs.IErrorInfo;
import com.rx.mvvmlibs.IModel;
import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.Model;
import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.bean.ErrorBean;
import com.rx.mvvmlibs.databinding.ActivityMvvmListBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmListBinding;
import com.rx.mvvmlibs.databinding.ErrorBinding;
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

    private ActivityMvvmListBinding activityMvvmBinding;
    private ContentMvvmListBinding contentMvvmBinding;

    public ListViewModelModule(ListViewModel listViewModel, ListMvvmActivity activity){
        this.listViewModel = listViewModel;
        this.errorInfo = listViewModel;
        this.activity = activity;
    }

    @Provides
    public ActivityMvvmListBinding providesActivityMvvmListBinding(){
        activityMvvmBinding = DataBindingUtil
                .setContentView(activity, R.layout.activity_mvvm);
        return activityMvvmBinding;
    }

    @Provides
    public ContentMvvmListBinding providesContentMvvmListBinding(){
        LogUtil.d(getClass(), "providesContentMvvmBinding: ");
        contentMvvmBinding = activityMvvmBinding.contentMvvmList;
        return contentMvvmBinding;
    }

    @Provides
    public ViewDataBinding providesChildBinding(){
        int childLayoutId;
        if (listViewModel.reSetRecyclerView() == 0){
            childLayoutId = R.layout.default_list;
        }else {
            childLayoutId = listViewModel.reSetRecyclerView();
        }
        SwipeRefreshLayout.LayoutParams lp = new SwipeRefreshLayout.LayoutParams(
                SwipeRefreshLayout.LayoutParams.MATCH_PARENT
                ,SwipeRefreshLayout.LayoutParams.MATCH_PARENT);
        ViewDataBinding childBinding = DataBindingUtil.inflate(
                activity.getLayoutInflater()
                ,childLayoutId
                ,contentMvvmBinding.refreshLayout
                ,false);
        if (childBinding != null){
            contentMvvmBinding.refreshLayout.addView(childBinding.getRoot(),lp);
        }
        return childBinding;
    }

    @Provides
    public ErrorBinding providesErrorBinding(){
        return contentMvvmBinding.error;
    }

    @Provides
    public ErrorBean providesError(){
        return new ErrorBean(errorInfo.setErrorImageResource(),errorInfo.setErrorString());
    }

    @Provides
    public IModel providesModel(){
        return new Model(listViewModel);
    }


}
