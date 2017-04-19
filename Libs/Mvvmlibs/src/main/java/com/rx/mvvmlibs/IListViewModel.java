package com.rx.mvvmlibs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.rx.mvvmlibs.params.PaginationParams;
import com.rx.mvvmlibs.view.BindingListAdapter;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @ClassName: IListViewModel
 * @author create by Tang
 * @date date 16/11/19 上午10:13
 * @Description: 列表型ViewModel
 */

public interface IListViewModel<Data> extends IRetrofitViewModel{

    /**
     * @Method: enqueue
     * @author create by Tang
     * @date date 16/11/14 下午3:44
     * @Description:
     * 通知model的enqueue方法把请求添加到请求队列中
     */
    void listEnqueue();


    /**
     * @Method: setApiInterface
     * @author create by Tang
     * @date date 16/11/14 下午3:46
     * @Description:
     * 设置对应的接口文件
     */
    Observable setListApiInterface(Retrofit retrofit);


    /**
     * @Method: modelResult
     * @author create by Tang
     * @date date 16/11/14 下午4:08
     * @Description:
     * Model层回调的数据
     */
    void onListResult(ListResult<Data> result);

    /**
     * @Method: refresh
     * @author create by Tang
     * @date date 16/11/19 下午3:03
     * @Description: 刷新列表
     */
    void refresh();

    /**
     * @Method: loading
     * @author create by Tang
     * @date date 16/11/19 下午3:06
     * @Description: 加载下一页
     */
    void loading();

    BindingListAdapter setAdapter();

    RecyclerView.LayoutManager setLayoutManager(Context context);

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
    void refreshIndexPage(int index);

    /**
     * @Method: init
     * @author create by Tang
     * @date date 16/11/18 上午10:54
     * @Description: 可以在此方法作一些初始化操作
     */
    void init();


}
