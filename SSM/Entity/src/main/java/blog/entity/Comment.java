package blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//需要导入lombok包
//自动生成get、set方法和toString方法
@Data
//生成全部参数的构造器
@AllArgsConstructor
//生成无参数的构造器
@NoArgsConstructor
public class Comment {
    private Integer commentId;	//  主键
    private Integer commentPid;	//  评论分类id
    private String commentPname;	//  评论昵称
    private Integer commentArticleId;	//  作者id
    private String commentAuthorName;	//  作者名
    private String commentAuthorEmail;	//  作者邮箱
    private String commentAuthorUrl;	//  作者链接
    private String commentAuthorAvatar;	//  作者的外部网站
    private String  commentContent;	//  内容
    private String commentAgent;	//  代理评论
    private String commentIp;	//  评论IP
    //通过注解进行转化
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date commentCreateTime;	//  评论时间
    /**
     * 角色(管理员1，访客0)
     */
    private Integer commentRole;	//  评论角色

    /**
     * 评论用户ID
     */
    private Integer commentUserId;

    /**
     * 非数据库字段
     */
    private Article article;

}
