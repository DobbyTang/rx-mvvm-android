package com.rx.mvvmlibs.view.iview;

import com.rx.mvvmlibs.RetrofitViewModel;


/**
 * @ClassName: BindViewModel
 * @author create by Tang
 * @date date 16/11/15 下午3:12
 * @Description: BindViewModel
 */

public interface BindViewModel {

    /**
     * @Method: onCreateViewModel
     * @author create by Tang
     * @date date 16/11/15 下午4:02
     * @Description: 绑定ViewModel
     * 必须在该方法中生成ViewModel实例
     */
    RetrofitViewModel onBindingViewModel();

    /**
     * @Method: init
     * @author create by Tang
     * @date date 16/11/18 下午5:16
     * @Description: 可以在这里做一些初始化操作d
     */
    void init();

}