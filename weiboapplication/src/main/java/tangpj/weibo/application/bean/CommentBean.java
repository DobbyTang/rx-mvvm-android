package tangpj.weibo.application.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @className: CommentBean
 * @author create by Tang
 * @date 2017/4/25 下午1:35
 * @description: 
 */

public class CommentBean {

    //评论创建时间
    @SerializedName("created_at")
    public String createdAt;

    //评论的ID
    public long id;

    //评论的内容
    public String text;

    //评论的来源
    public String source;

    //评论作者的用户信息字段
    public UserBean user;

    //评论的MID
    public String mid;

    //字符串型的评论ID
    @SerializedName("idstr")
    public String idStr;

    //评论的微博信息字段
    @SerializedName("status")
    public WeiboBean weibo;

    //评论来源评论，当本评论属于对另一评论的回复时返回此字段
    @SerializedName("reply_comment")
    public CommentBean replyComment;
}
