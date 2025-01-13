package blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//需要导入lombok包
//自动生成get、set方法和toString方法
@Data
//生成全部参数的构造器
@AllArgsConstructor
//生成无参数的构造器
@NoArgsConstructor
public class Article {

    /*
    驼峰命名法
     */
    private Integer articleId;	//  主键
    private Integer articleUserId;	//  用户id(对应用户表主键)
    private String articleTitle;	//  文章标题
    private String articleContent;	//  文章内容
    private Integer articleViewCount;	//  观看数量
    private Integer articleCommentCount;	//  评论数量
    private Integer articleLikeCount;		//  点赞量
    private Integer articleIsComment;	//  是否可以评论(1可以、0不可以)
    private Integer articleStatus;	//  文章状态
    private Integer articleOrder;	//  文章是否显示
    //通过注解进行时间转换
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date articleUpdateTime;	//  修改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date articleCreateTime;	//  创建时间
    private String articleSummary;	//  文章概要
    private String articleThumbnail; // 缩略图

    private User user; //   用户信息

    private List<Tag> tagList;  //  标题集合

    private List<Category> categoryList;    //  分类集合
}
