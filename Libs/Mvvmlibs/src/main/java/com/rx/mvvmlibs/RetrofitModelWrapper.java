package com.rx.mvvmlibs;

import com.rx.mvvmlibs.network.BaseParamsInterceptor;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * @className: RetrofitModelWrapper
 * @author create by Tang
 * @date 2017/4/19 上午10:46
 * @description:
 */

public class RetrofitModelWrapper {

    @Inject
    Retrofit retrofit;

    @Inject
    BaseParamsInterceptor.Builder builder;

    @Inject
    public RetrofitModelWrapper(){

    }
}
