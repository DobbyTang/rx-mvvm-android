package tangpj.weibo.weibo.fragment;

import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.view.ListMvvmFragment;

import tangpj.weibo.weibo.viewmodel.WeiboViewModel;


/**
 * @className: WeiboFragment
 * @author create by Tang
 * @date 2017/4/25 下午2:50
 * @description:
 */

public class WeiboFragment extends ListMvvmFragment{

    @Override
    public ListViewModel onBindingViewModel() {
        return new WeiboViewModel(this);
    }

    @Override
    public void init() {
        super.init();
    }
}
