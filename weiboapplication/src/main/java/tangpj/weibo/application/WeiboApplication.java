package tangpj.weibo.application;

import com.rx.mvvmlibs.RxMvvmApplication;

/**
 * @className: WeiboApplication
 * @author create by Tang
 * @date 2017/4/19 下午8:00
 * @description:
 */

public class WeiboApplication extends RxMvvmApplication {

    private static final String WEIBO_URL = "https://api.weibo.com/";

    @Override
    public String setServerUrl() {
        return WEIBO_URL;
    }

    @Override
    public int setDefaultDrawableResource() {
        return R.mipmap.ic_launcher;
    }
}
