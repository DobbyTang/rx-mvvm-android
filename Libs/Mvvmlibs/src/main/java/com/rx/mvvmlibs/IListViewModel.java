package com.rx.mvvmlibs;

/**
 * @ClassName: IListViewModel
 * @author create by Tang
 * @date date 16/11/19 上午10:13
 * @Description: 列表型ViewModel
 */

public interface IListViewModel<Data> extends IViewModel<Data>{

    /**
     * @Method: reSetRecyclerView
     * @author create by Tang
     * @date date 16/11/19 上午10:20
     * @Description: 重设RecyclerView布局
     */
    int reSetRecyclerView();

    /**
     * @Method: refresh
     * @author create by Tang
     * @date date 16/11/19 下午3:03
     * @Description: 刷新列表
     */
    void refresh();

    /**
     * @Method: lodding
     * @author create by Tang
     * @date date 16/11/19 下午3:06
     * @Description: 加载下一页
     */
    void loading();

    /**
     * @Method: setCount
     * @author create by Tang
     * @date date 16/11/19 下午3:06
     * @Description: 设置取数据条数
     */
    void setCount(int count);

    /**
     * @Method: refreshIndexPage
     * @author create by Tang
     * @date date 16/11/19 下午3:05
     * @Description: 刷新指定页面
     */
    void refreshIndexPage();
}
