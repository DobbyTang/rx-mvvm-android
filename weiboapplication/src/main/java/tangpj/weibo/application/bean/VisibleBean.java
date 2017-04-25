package tangpj.weibo.application.bean;

/**
 * @className: VisibleBean
 * @author create by Tang
 * @date 2017/4/25 上午11:09
 * @description: 
 */

public class VisibleBean {

    /*
     * type取值，0：普通微博，1：私密微博，
     * 3：指定分组微博，4：密友微博；
     */
    public int type;

    //分组的组号
    public String list_id;
}
