package com.rx.mvvmlibs.bean;

import android.databinding.ObservableField;

import javax.inject.Inject;

/**
 * @ClassName: ProgressBean
 * @author create by Tang
 * @date date 16/11/15 下午5:00
 * @Description: 进度条实体类，用于控制进度条
 */

public class ProgressBean {

    @Inject
    public ProgressBean(){

    }

    //默认进度条
    public static final int PROGRESS_TYPE_DEFAULT = 1;

    //对话框进度条
    public static final int PROGRESS_TYPE_DIALOG = 2;

    //下拉进度条
    public static final int PROGRESS_TYPE_DROP_DOWN = 3;

    //默认对加载进度条
    public ObservableField<Integer> progressType = new ObservableField<>();

    //是否选看是进度条
    public ObservableField<Boolean> showProgress = new ObservableField<>();

    public void setProgressType(int type){

    }

}
