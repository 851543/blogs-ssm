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
public class Tag {
    private Integer tagId;	//  标题id
    private String tagName;	//  标题名
    private String tagDescription; 	//  标题简介

    /**
     * 文章数量(不是数据库字段)
     */
    private Integer articleCount;

    public Tag(Integer tagId) {
        this.tagId = tagId;
    }

}
