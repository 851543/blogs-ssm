package blog.service;

import blog.entity.ArticleTagRef;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface ArticleTagRefService {
    //查询全部数据的业务接口
    List<ArticleTagRef> articleCategoryRefList();

    /**
     * 添加文章和标签关联记录
     * @param record 关联对象
     * @return 影响行数
     */
    void addArticleTagRef(ArticleTagRef record);
}
