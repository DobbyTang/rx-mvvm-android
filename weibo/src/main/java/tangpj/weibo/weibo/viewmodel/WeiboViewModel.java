package tangpj.weibo.weibo.viewmodel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rx.mvvmlibs.ListResult;
import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.view.BindingListAdapter;
import com.rx.mvvmlibs.view.ListMvvmFragment;
import com.rx.utillibs.LogUtil;
import com.tangpj.recyclerviewutils.decoration.SimpleDecoration;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import tangpj.weibo.application.bean.WeiboBean;
import tangpj.weibo.weibo.WeiboApi;
import tangpj.weibo.weibo.adapter.WeiboAdapter;

/**
 * @className: WeiboViewModel
 * @author create by Tang
 * @date 2017/4/25 下午2:20
 * @description: 微博ViewModel
 */

public class WeiboViewModel extends ListViewModel<WeiboBean> {

    private WeiboAdapter weiboAdapter;

    public WeiboViewModel(ListMvvmFragment fragment) {
        super(fragment);
    }

    @Override
    public Observable setListApiInterface(Retrofit retrofit) {
        return retrofit.create(WeiboApi.class)
                .publicTimeLine("2.00SIRyVC0Et5aIef11285233aFdSRD");
    }

    @Override
    public BindingListAdapter setAdapter() {
        LogUtil.d("");
        return new WeiboAdapter();
    }

    @Override
    public RecyclerView.LayoutManager setLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Override
    public void init() {
        super.init();
        getRecyclerView().get().addItemDecoration(SimpleDecoration.newTransparentDivider(getContext(),8));
    }
}
