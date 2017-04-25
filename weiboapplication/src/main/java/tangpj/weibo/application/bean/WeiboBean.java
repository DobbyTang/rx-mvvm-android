package tangpj.weibo.application.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @className: WeiboBean
 * @author create by Tang
 * @date 2017/4/25 上午10:44
 * @description: 微博实体类
 */

public class WeiboBean {

    //微博创建时间
    @SerializedName("created_at")
    public String createdAt;

    //微博ID
    public long id;

    //微博MID
    public long mid;

    //字符串型的微博ID
    @SerializedName("idstr")
    public String idStr;

    //微博信息内容
    public String text;

    //微博来源
    public String source;

    //是否已收藏
    public boolean favorited;

    //是否被截断
    public boolean truncated;

    //缩略图片地址，没有时不返回此字段
    @SerializedName("thumbnail_pic")
    public String thumbnailPic;

    //中等尺寸图片地址，没有时不返回此字段
    @SerializedName("bmiddle_pic")
    public String bmiddlePic;

    //原始图片地址，没有时不返回此字段
    @SerializedName("original_pic")
    public String originalPic;

    //地理信息字段
    public Geo geo;

    //微博作者的用户信息字段
    public UserBean user;

    //被转发的原微博信息字段，当该微博为转发微博时返回
    @SerializedName("retweeted_status")
    public WeiboBean retweetedWeibo;

    //转发数
    @SerializedName("reposts_count")
    public int forwardingCount;

    //评论数
    @SerializedName("comments_count")
    public int commentsCount;

    //表态数?点赞？
    @SerializedName("attitudes_count")
    public int attitudesCount;

    //微博的可见性及指定可见分组信息。
    public VisibleBean visible;

    @SerializedName("pic_urls")
    public List<Pic> picUrls;


    private class Pic {

        @SerializedName("thumbnail_pic")
        public String thumbnailPic;
    }

}
