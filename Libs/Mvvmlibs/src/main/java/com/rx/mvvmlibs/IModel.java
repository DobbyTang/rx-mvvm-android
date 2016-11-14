package com.rx.mvvmlibs;


/**
 * @ClassName: IModel
 * @author create by Tang
 * @date date 16/11/10 上午11:50
 * @Description: MVVM的model接口，
 */

public interface IModel {

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
}
