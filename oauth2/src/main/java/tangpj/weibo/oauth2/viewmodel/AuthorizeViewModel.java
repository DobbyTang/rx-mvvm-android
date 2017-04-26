package tangpj.weibo.oauth2.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rx.mvvmlibs.RetrofitModel;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.bean.ProgressBean;
import com.rx.mvvmlibs.view.MvvmActivity;
import com.rx.utillibs.LogUtil;

import tangpj.weibo.oauth2.Oauth2Api;
import tangpj.weibo.oauth2.R;
import tangpj.weibo.oauth2.databinding.ActivityAuthorizeBinding;
import tangpj.weibo.oauth2.result.AccessTokenResult;

/**
 * @className: AuthorizeViewModel
 * @author create by Tang
 * @date 2017/4/19 下午8:06
 * @description:
 */

public class AuthorizeViewModel extends ViewModel {

    private static final String REDIRECT_URI = "http://tangpj.com/";
    private static final String CLIENT_ID = "126886166";
    private static final String CLIENT_SECRET = "1f2b85605592c1ed9eeef9502c119338";

    private static final String AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize?" +
            "client_id=" + CLIENT_ID + "&" +
            "redirect_uri="+ REDIRECT_URI + "&" +
            "scope=all";

    private ActivityAuthorizeBinding binding;

    RetrofitModel<AccessTokenResult> accessTokenModel;

    private WebView webView;
    private String authorizeCode;

    public AuthorizeViewModel(MvvmActivity activity) {
        super(activity);
    }

    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, ViewGroup parent) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_authorize,parent,false);
        return binding;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void init() {
        super.init();
        setProgressType(ProgressBean.PROGRESS_TYPE_DROP_DOWN);
        webView = binding.authorizeWebview;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (url.contains("http://tangpj.com/?code=")){
                    authorizeCode = url.split("=")[1];
                    view.stopLoading();
                    LogUtil.d("code = " + authorizeCode);
                    accessTokenModel.enqueueRequest();
                    return;
                }
                super.onPageStarted(view, url, favicon);
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100){
                    showProgress(false);
                }else {
                    showProgress(true);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        webView.loadUrl(AUTHORIZE_URL);

        accessTokenModel = new RetrofitModel<>(this);
        accessTokenModel.setApiInterface(retrofit -> retrofit.create(Oauth2Api.class)
                .getAccessToken(CLIENT_ID,CLIENT_SECRET,"authorization_code",authorizeCode,REDIRECT_URI))
                .setOnResult(accessTokenResult -> LogUtil.d("token = " + accessTokenResult.accessToken));


    }

    public ActivityAuthorizeBinding getBinding(){
        return binding;
    }

}
