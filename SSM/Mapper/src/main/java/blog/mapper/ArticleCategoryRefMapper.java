package blog.mapper;

import blog.entity.Article;
import blog.entity.ArticleCategoryRef;
import blog.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理
@Repository
public interface ArticleCategoryRefMapper {
    //查询全部数据
    @Select("select * from article_category_ref")
    List<ArticleCategoryRef> selectArticleCategoryRefs();

    @Delete("delete from article_category_ref where article_id = #{id}")
    Integer deleteByArticleId(@Param("id") Integer id);

    /**
     * 添加文章和分类关联记录
     * @param record 关联对象
     * @return 影响行数
     */
    @Insert("insert into article_category_ref values(#{articleId},#{categoryId})")
    Integer insertArticleCategoryRef(ArticleCategoryRef record);

    /**
     * 根据文章ID获得分类列表
     *
     * @param articleId 文章ID
     * @return 分类列表
     */
    @Select("select category.category_id, category.category_pid, category.category_name from category, article_category_ref" +
            " where article_category_ref.article_id = #{value} and article_category_ref.category_id = category.category_id order by category.category_pid asc")
    List<Category> listCategoryByArticleId(Integer articleId);

    /**
     * 根据文章ID查询分类ID
     *
     * @param articleId 文章ID
     * @return 分类ID列表
     */
    @Select("SELECT category_id FROM article_category_ref  WHERE article_id = #{value}")
    List<Integer> selectCategoryIdByArticleId(Integer articleId);

    /**
     * 根据分类ID
     *
     * @param categoryIds 分类ID集合
     * @param limit       查询数量
     * @return 文章列表
     */
    List<Article> findArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                           @Param("limit") Integer limit);

    /**
     * 获得访问最多的文章(猜你喜欢)
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    @Select("select * from article WHERE article_status ORDER BY article_view_count DESC,article_order DESC, article_id DESC limit #{limit}")
    List<Article> listArticleByViewCount(@Param(value = "limit") Integer limit);
    /**
     * 获得上一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getAfterArticle(@Param(value = "id") Integer id);
    /**
     * 获得下一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getPreArticle(@Param(value = "id") Integer id);




}
