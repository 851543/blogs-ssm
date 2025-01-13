package blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//需要导入lombok包
//自动生成get、set方法和toString方法
@Data
//生成全部参数的构造器
@AllArgsConstructor
//生成无参数的构造器
@NoArgsConstructor
public class Menu {

    private Integer menuId;	//  菜单id
    private String menuName;	//  菜单名
    private String menuUrl;	//  菜单链接
    private Integer menuLevel;	//  菜单级别
    private String menuIcon;	//  菜单图标
    private Integer menuOrder;	//  排序



}
