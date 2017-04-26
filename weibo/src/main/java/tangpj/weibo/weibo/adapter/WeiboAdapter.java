package tangpj.weibo.weibo.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rx.mvvmlibs.view.BindingListAdapter;

import tangpj.weibo.application.bean.WeiboBean;
import tangpj.weibo.weibo.R;
import tangpj.weibo.weibo.databinding.ItemWeiboBinding;

/**
 * @className: WeiboAdapter
 * @author create by Tang
 * @date 2017/4/25 下午2:50
 * @description:
 */

public class WeiboAdapter extends BindingListAdapter<WeiboBean>{


    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, ViewGroup parent) {
        return DataBindingUtil.inflate(inflater, R.layout.item_weibo,parent,false);
    }

    @Override
    public void onBinding(ViewDataBinding binding, int realPosition, WeiboBean weiboBean) {
        ((ItemWeiboBinding) binding).setWeibo(weiboBean);
    }


}
