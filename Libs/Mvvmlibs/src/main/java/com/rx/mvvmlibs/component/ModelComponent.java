package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.Model;
import com.rx.mvvmlibs.module.ModelModule;
import com.rx.mvvmlibs.module.RetrofitModule;
import com.rx.mvvmlibs.scope.RetrofitScope;

import dagger.Component;

/**
 * @ClassName: ModelComponent
 * @author create by Tang
 * @date date 16/11/21 上午9:37
 * @Description: TODO
 */

@RetrofitScope
@Component(modules = {RetrofitModule.class,ModelModule.class})
public interface ModelComponent {

    void inject(Model model);
}
