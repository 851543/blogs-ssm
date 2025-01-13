package blog.service;

import blog.entity.Article;
import blog.entity.ArticleCategoryRef;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface ArticleCategoryRefService {
    //查询全部数据的业务接口
    List<ArticleCategoryRef> articleCategoryRefList();

    /**
     * 添加文章和分类关联记录
     * @param record 关联对象
     * @return 影响行数
     */
    void addArticleCategoryRef(ArticleCategoryRef record);
    /**
     * 根据文章ID获得分类ID列表
     *
     * @param articleId 文章Id
     * @return 列表
     */
    List<Integer> listCategoryIdByArticleId(Integer articleId);

    /**
     * 获得相关文章
     *
     * @param cateIds 分类ID集合
     * @param limit   数量
     * @return 列表
     */
    List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit);


    /**
     * 获取访问量较多的文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByViewCount(Integer limit);
    /**
     * 获得上一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getAfterArticle(Integer id);

    /**
     * 获得下一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getPreArticle(Integer id);


}
