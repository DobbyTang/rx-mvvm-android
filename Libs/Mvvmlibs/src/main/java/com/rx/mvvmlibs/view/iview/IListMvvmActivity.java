package com.rx.mvvmlibs.view.iview;

import com.rx.mvvmlibs.ListViewModel;

/**
 * @ClassName: IListMvvmActivity
 * @author create by Tang
 * @date date 16/11/18 下午3:55
 * @Description: TODO
 */

public interface IListMvvmActivity {

    /**
     * @ClassName: IListMvvmActivity
     * @author create by Tang
     * @date date 16/11/18 下午4:05
     * @Description: TODO
     */
    ListViewModel onBindingViewModel();

    /**
     * @Method: init
     * @author create by Tang
     * @date date 16/11/18 下午5:29
     * @Description: 初始化操作
     */
    void init();
}
