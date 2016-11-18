package com.android.rx_mvvm.bean;

import android.databinding.ObservableField;

/**
 * Created by guohao on 16/11/14.
 */

public class TestBean {

    public ObservableField<Integer> testInt = new ObservableField<>();
    public ObservableField<String> testString = new ObservableField<>();
    public ObservableField<Boolean> testBoolean = new ObservableField<>();
    public ObservableField<Float> testFloat = new ObservableField<>();
}
