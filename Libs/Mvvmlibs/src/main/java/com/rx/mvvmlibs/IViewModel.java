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
    void cancle();
    
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
    void modelResult(Result result);
    
    /**
     * @Method: modeResultData
     * @author create by Tang
     * @date date 16/11/14 下午5:22
     * @Description: TODO
     */
    void modeResultData(Data data);
}
