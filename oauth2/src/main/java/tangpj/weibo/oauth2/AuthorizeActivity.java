package tangpj.weibo.oauth2;

import android.view.KeyEvent;
import android.webkit.WebView;

import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.view.MvvmActivity;

import javax.inject.Inject;

import tangpj.weibo.oauth2.viewmodel.AuthorizeViewModel;

public class AuthorizeActivity extends MvvmActivity {

    private AuthorizeViewModel viewModel;
    private WebView webView;

    @Override
    public ViewModel onBindingViewModel() {
        viewModel = new AuthorizeViewModel(this);
        return viewModel;
    }

    @Override
    public void init() {
        super.init();
        webView = viewModel.getBinding().authorizeWebview;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
