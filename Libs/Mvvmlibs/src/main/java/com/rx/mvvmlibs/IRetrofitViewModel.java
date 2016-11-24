package com.rx.mvvmlibs;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @ClassName: IRetrofitViewModel
 * @author create by Tang
 * @date date 16/11/24 上午10:23
 * @Description: 需要使用Retrofit的View需要实现该接口
 */

public interface IRetrofitViewModel<Data> {
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
    Observable setApiInterface(Retrofit retrofit);


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
}
