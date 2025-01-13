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
public class User {
    /*
    驼峰命名法
     */
    private Integer userId;	//  主键
    private String userName;	//  账号名
    private String userPass;	//密码
    private String userNickname;	//用户昵称
    private String userEmail;	//  用户的邮箱
    private String userUrl;	//  用户的链接
    private String userAvatar;	//  用户头像
    private String userLastLoginIp;	//  用户最后登录ip
    //  通过注解进行转换
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date userRegisterTime;	//  用户注册时间
    //  通过注解进行转换
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date userLastLoginTime;	//  用户最后登录时间
    private Integer userStatus;	//  用户状态 1为正常 0为禁用
    /**
     * 用户角色：admin/user
     */
    private String userRole;

    /**
     * 文章数量
     */
    private Integer articleCount;
}
