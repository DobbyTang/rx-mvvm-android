package com.rx.mvvmlibs;

import rx.Observable;

/**
 * @ClassName: IViewModel
 * @author create by Tang
 * @date date 16/11/14 下午3:38
 * @Description: TODO
 */

public interface IViewModel<Data> {

    /**
     * @Method: enqueue
     * @author create by Tang
     * @date date 16/11/14 下午3:44
     * @Description:
     * 通知model的enqueue方法把请求添加到请求队列中
     */
    void enqueue();

    /**
     * @Method: cancle
     * @author create by Tang
     * @date date 16/11/14 下午3:44
     * @Description:
     * 通知model层取消对应的请求
     */
    void cancel();
    
    /**
     * @Method: setApiInterface
     * @author create by Tang
     * @date date 16/11/14 下午3:46
     * @Description:
     * 设置对应的接口文件
     */
    Observable setApiInterface();


    /**
     * @Method: modelResult
     * @author create by Tang
     * @date date 16/11/14 下午4:08
     * @Description: 
     * Model层回调的数据
     */
    void onResult(Result<Data> result);

    /**
     * @Method: onSuccess
     * @author create by Tang
     * @date date 16/11/15 上午10:02
     * @Description: 请求成功回调
     */
    void onSuccess();

    /**
     * @Method: onNetworkError
     * @author create by Tang
     * @date date 16/11/15 上午10:03
     * @Description: 网络错误
     */
    void onNetworkError(Throwable e);

    /**
     * @Method: showProgress
     * @author create by Tang
     * @date date 16/11/15 下午5:24
     * @Description: TODO
     */
    void showProgress(boolean enable);

    /**
     * @Method: init
     * @author create by Tang
     * @date date 16/11/18 上午10:54
     * @Description: 可以在此方法作一些初始化操作
     */
    void init();




}
