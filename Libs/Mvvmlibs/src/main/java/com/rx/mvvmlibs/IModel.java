package com.rx.mvvmlibs;


import com.rx.mvvmlibs.network.BaseParamsInterceptor;

import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;

/**
 * @ClassName: IModel
 * @author create by Tang
 * @date date 16/11/10 上午11:50
 * @Description: MVVM的model接口，
 */

public interface IModel<Result> {


    /**
     * @Method: setApiInterface
     * @author create by Tang
     * @date 2017/4/19 上午9:21
     * @Description: 设置接口地址
     */
    Observable setApiInterface(Retrofit retrofit);

    /**
     * @Method: enqueueRequest
     * @author create by Tang
     * @date date 16/11/14 下午3:39
     * @Description: 添加到请求队列
     */
    void enqueueRequest();

    /**
     * @Method: cancelRequest
     * @author create by Tang
     * @date date 16/11/14 下午3:39
     * @Description: 取消这次请求
     */
    void cancelRequest();

    /**
     * @Method: setResultScheduler
     * @author create by Tang
     * @date date 16/11/15 上午10:11
     * @Description: 设置回调事件处理的线程
     * 默认为ui线程
     */
    void setResultScheduler(Scheduler scheduler);

    /**
     * @Method: getBuilder
     * @author create by Tang
     * @date date 16/11/21 上午10:59
     * @Description: okHttp拦截器
     */
    BaseParamsInterceptor.Builder getBuilder();

    /**
     * @Method: setOnResult
     * @author create by Tang
     * @date 2017/4/19 上午11:50
     * @Description: 设置回调监听器
     */
    void setOnResult(Consumer<Result> onResult);

}
