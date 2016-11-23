package com.rx.mvvmlibs.view.iview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName: IMvvmActivity
 * @author create by Tang
 * @date date 16/10/26 下午1:47
 * @Description: BaseActivity对应接口
 */

public interface IMvvmActivity {

    View setContentView(LayoutInflater inflater, ViewGroup container);
    
}
