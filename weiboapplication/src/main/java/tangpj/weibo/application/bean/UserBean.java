package tangpj.weibo.application.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @className: UserBean
 * @author create by Tang
 * @date 2017/4/25 上午9:44
 * @description: 用户信息
 * 布尔变量：true：是，false：否
 */
public class UserBean {

    @SerializedName("idstr")
    public String id;

    @SerializedName("screen_name")
    public String screenName;

    public String name;

    //用户所在省级ID
    public int province;

    //用户所在城市ID
    public String city;

    //	用户所在地
    public String location;

    public String description;

    //用户博客地址
    public String url;

    //用户头像
    @SerializedName("profile_image_url")
    public String avatar;

    //用户头像地址（大图)
    @SerializedName("avatar_large")
    public String avatarLarge;

    //用户头像地址（高清
    @SerializedName("avatar_hd")
    public String avatarHd;

    //用户的微博统一URL地址
    @SerializedName("profile_url")
    public String profileUrl;

    //用户的个性化域名
    public String domain;

    //用户的微号
    public String weihao;

    //性别，m：男、f：女、n：未知
    public String gender;

    //粉丝数
    @SerializedName("followers_count")
    public int followersCount;

    //关注数
    @SerializedName("friends_count")
    public int friendsCount;

    //微博数
    @SerializedName("statuses_count")
    public int statusesCount;

    //收藏数
    @SerializedName("favourites_count")
    public int favouritesCount;

    //用户创建（注册）时间
    @SerializedName("created_at")
    public String createdAt;

    //是否允许所有人给我发私信
    @SerializedName("allow_all_act_msg")
    public boolean allowAllActMsg;

    //是否允许标识用户的地理位置
    @SerializedName("geo_enabled")
    public boolean geoEnabled;

    //是否是微博认证用户，即加V用户
    public boolean verified;

    //用户备注信息
    public String remark;

    //用户的最近一条微博信息字段
    public String status;

    //是否允许所有人对我的微博进行评论
    @SerializedName("allow_all_comment")
    public boolean allowAllComment;

    //认证原因
    @SerializedName("verified_reason")
    public String verifiedReason;

    //该用户是否关注当前登录用户
    @SerializedName("follow_me")
    public String followMe;

    //用户的在线状态，0：不在线、1：在线
    @SerializedName("online_status")
    public int onlineStatus;

    //用户的互粉数
    @SerializedName("bi_followers_count")
    public int biFollowersCount;

    //用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语
    public String lang;

}
