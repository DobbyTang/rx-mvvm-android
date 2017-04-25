package com.rx.mvvmlibs.view;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tangpj.recyclerviewutils.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: BindingListAdapter
 * @author create by Tang
 * @date date 16/10/21 下午3:02
 * @Description: 只支持一种布局
 * 要支持多布局请自己实现
 * @Data: 列表项实体类
 */

public abstract class BindingListAdapter<Data> extends SimpleAdapter<Data>{


    @Override
    public RecyclerView.ViewHolder onCreateNormalView(ViewGroup viewGroup) {
        return new BindHolder(onCreateBinding(
                LayoutInflater.from(viewGroup.getContext()),viewGroup).getRoot());
    }

    @Override
    public void onBindNormalView(RecyclerView.ViewHolder viewHolder, int i, Data o) {
        onBinding(((BindHolder)viewHolder).getBinding(),i,o);
    }

    public abstract ViewDataBinding onCreateBinding(LayoutInflater inflater, ViewGroup parent);
    public abstract void onBinding(ViewDataBinding binding, int realPosition, Data data);

    protected class BindHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public BindHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding(){
            return binding;
        }
    }
}
