package com.rx.mvvmlibs.view.iview;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @ClassName: IMvvmFragment
 * @author create by Tang
 * @date date 16/11/21 下午4:10
 * @Description: TODO
 */

public interface IMvvmFragment {

    /**
     * @Method: onCreateBinding
     * @author create by Tang
     * @date date 16/11/21 下午4:19
     * @Description: TODO
     */
    ViewDataBinding onCreateBinding(LayoutInflater inflater, ViewGroup parent);
}
