package com.android.rx_mvvm.bean;

import android.databinding.ObservableField;

import java.util.List;

/**
 * @ClassName: NuoMiCategoryBean
 * @author create by Tang
 * @date date 16/10/12 下午5:41
 * @Description: 糯米分类实体类
 */

public class NuoMiCategoryBean {


    public ObservableField<String> cat_id;

    //一级分类名称
    public ObservableField<String> cat_name;

    //二级分类列表
    public List<Subcategories> subcategories;

    public class Subcategories{

        //二级分类id
        public String subcat_id;

        //二级分类名称
        public String subcat_name;

    }

}
