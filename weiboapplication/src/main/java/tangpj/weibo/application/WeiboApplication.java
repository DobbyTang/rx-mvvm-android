package tangpj.weibo.application;

import com.google.gson.Gson;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.GsonBuilder;
import com.rx.mvvmlibs.RxMvvmApplication;
import com.rx.mvvmlibs.internal.ObservableFieldTypeAdapter;

/**
 * @className: WeiboApplication
 * @author create by Tang
 * @date 2017/4/19 下午8:00
 * @description:
 */

public class WeiboApplication extends RxMvvmApplication {

    private static final String WEIBO_URL = "https://api.weibo.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

    @Override
    public String setServerUrl() {
        return WEIBO_URL;
    }

    @Override
    public int setDefaultDrawableResource() {
        return R.mipmap.ic_launcher;
    }

    @Override
    protected Gson initGson() {

        return new GsonBuilder()
                .setDateFormat("EEE MMM d HH:mm:ss Z yyyy")
                .registerTypeAdapter(ObservableFieldTypeAdapter.class,new ObservableFieldTypeAdapter())
                .create();
    }

    @Override
    protected void init() {
        super.init();
    }
}
