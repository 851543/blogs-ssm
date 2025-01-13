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
public class ArticleCategoryRef {
    private Integer articleId;	//  文章id
    private Integer categoryId;	//  分类id

}
