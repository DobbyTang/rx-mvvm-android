package com.android.rx_mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.rx_mvvm.bean.NuoMiCategoryBean;
import com.android.rx_mvvm.databinding.ListItemStringBinding;
import com.rx.mvvmlibs.view.BindingListAdapter;

/**
 * @ClassName: TestAdapter
 * @author create by Tang
 * @date date 16/11/21 下午5:41
 * @Description: TODO
 */

public class TestAdapter extends BindingListAdapter<NuoMiCategoryBean> {


    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, ViewGroup parent) {
        return DataBindingUtil.inflate(inflater,R.layout.list_item_string,parent,false);
    }

    @Override
    public void onBinding(ViewDataBinding binding, int realPosition, NuoMiCategoryBean nuoMiCategoryBean) {
        ((ListItemStringBinding)binding).setCategory(nuoMiCategoryBean);
    }
}
