package com.rx.mvvmlibs;

import retrofit2.Call;

/**
 * @ClassName: IModel
 * @author create by Tang
 * @date date 16/11/10 上午11:50
 * @Description: MVVM的model接口，
 */

public interface IModel {

    void enqueue();

    void cancel();
}
