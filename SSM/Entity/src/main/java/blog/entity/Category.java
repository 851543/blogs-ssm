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
public class Category {
    private Integer categoryId;		//  分类id
    private Integer categoryPid;			//  父级id
    private String categoryName;		//  分类名
    private String categoryDescription;			//  分类简介
    private Integer categoryOrder;			//  是否排序(1排序)
    private String categoryIcon;	//  分类的图标

    /**
     * 文章数量(非数据库字段)
     */
    private Integer articleCount;


    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 未分类
     *
     * @return 分类
     */
    public static Category Default() {
        return new Category(100000000, "未分类");
    }

    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
