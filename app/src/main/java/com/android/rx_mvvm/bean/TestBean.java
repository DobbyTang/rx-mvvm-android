package com.android.rx_mvvm.bean;

import android.databinding.ObservableField;

/**
 * @ClassName: TestBean
 * @author create by Tang
 * @date date 16/11/19 上午9:57
 * @Description: 用于测试gson解析
 */

public class TestBean {

    public ObservableField<Integer> testInt = new ObservableField<>();
    public ObservableField<String> testString = new ObservableField<>();
    public ObservableField<Boolean> testBoolean = new ObservableField<>();
    public ObservableField<Float> testFloat = new ObservableField<>();
}
