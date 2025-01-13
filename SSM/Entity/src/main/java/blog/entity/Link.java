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
public class Link {
    private Integer linkId;	//  主键
    private String linkUrl;	//  链接
    private String linkName;	//  链接名
    private String linkImage;	//  图片
    private String linkDescription;	//  简介
    private String linkOwnerNickname;	//  拥有者昵称
    private String linkOwnerContact;	//  练习方式
    //  通过注解进行转换
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date linkUpdateTime;	//  链接修改时间
    //  通过注解进行转换
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date linkCreateTime;	//  链接创建时间
    private Integer linkOrder;	//  排序
    private Integer linkStatus;	//  状态

}
