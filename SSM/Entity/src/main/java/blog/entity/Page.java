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
public class Page {

    private Integer pageId;	//  站点id
    private String pageKey;	//  站点关键点
    private String pageTitle;	//  站点标题
    private String pageContent;	//  站点内容
    //  通过注解进行转换
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pageCreateTime;	//  修改时间
    //  通过注解进行转换
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pageUpdateTime;	//  创建时间
    private Integer pageViewCount;	//  站点浏览次数
    private Integer pageCommentCount;	//  评论次数
    private Integer pageStatus;	//  站点状态 0为隐藏 1为显示 2为模块


}
