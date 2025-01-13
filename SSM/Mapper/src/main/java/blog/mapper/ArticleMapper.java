package blog.mapper;

import blog.entity.Article;
import blog.entity.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

//用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理
@Repository
public interface ArticleMapper {
    //查询全部数据
    @Select("select * from article")
    List<Article> selectArticles();

    @Select("select article_title,article_summary,article_status,article_create_time,article_user_id from article")
    List<Article> selectArticlesCe();

    //全部文章的删除
    @Delete("delete from article where article_id=#{articleId}")
    Integer deleteArticles(Integer id);

    //进后台首页首先展示的是文章的标题和发布时间
    @Select("select article_create_time,article_title from article")
    List<Article> selArticles();

    //根据访问量进行查询热评文章
    @Select("select * from article  order by article_view_count desc limit 9")
    List<Article> selectArticlesId();

    //    根据评论数进行查询随机文章
    @Select("select * from article  order by article_comment_count ASC limit 6")
    List<Article> selectArticlesuiji();

    /**
     * 文章详情页面显示
     *
     * @param status 状态
     * @param id     文章ID
     * @return 文章
     */
    Article getArticleByStatusAndId(@Param("status") Integer status, @Param("id") Integer id);

    /**
     * 文章分页操作
     *
     * @param status    状态
     * @param pageIndex 从第几页开始
     * @param pageSize  数量
     * @return 文章列表
     */
    @Select("select * from article where article_status=#{status} order by article_status asc, article_order desc, article_id desc" +
            "        limit #{pageIndex},#{pageSize}")
    List<Article> pageArticle(@Param(value = "status") Integer status,
                              @Param(value = "pageIndex") Integer pageIndex,
                              @Param(value = "pageSize") Integer pageSize);

    /**
     * 获得所有的文章
     *
     * @param criteria 查询条件
     * @return 文章列表
     */
    List<Article> findAll(HashMap<String, Object> criteria);


    /**
     * 添加文章
     * Options接收自增id
     * @param article 文章
     * @return 文章
     */
    @Insert("insert into article values(#{articleId},#{articleUserId},#{articleTitle},#{articleContent},#{articleViewCount},#{articleCommentCount}," +
            "#{articleLikeCount},#{articleIsComment},#{articleStatus},#{articleOrder},#{articleUpdateTime},#{articleCreateTime},#{articleSummary},#{articleThumbnail})")
    @Options(useGeneratedKeys=true, keyProperty="articleId",keyColumn = "article_id")
    Integer insertArticles(Article article);

    /**
     * 更新文章
     *
     * @param article 文章
     * @return 影响行数
     */
    Integer updateArticle(Article article);

    /**
     * 获得最新文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByLimit(@Param("userId") Integer userId, @Param("limit") Integer limit);

    /**
     * 获取文章总数
     *
     * @param status 状态
     * @return 数量
     */
    @Select("select count(*) from article where article_status = #{status}")
    Integer countArticle(@Param(value = "status") Integer status);

    /**
     * 获得最后更新的记录
     *
     * @return 文章
     */
    @Select("select * from article where article_status = 1 and article_update_time = (select max(article_update_time) from article)")
    Article getLastUpdateArticle();

    /**
     * 获得留言总数
     *
     * @return 数量
     */
    @Select("select  sum(article_comment_count) from article where article_status = 1")
    Integer countArticleComment();

    /**
     * 获得浏览量总数
     *
     * @return 文章数量
     */
    @Select("select sum(article_view_count) from article where article_status = 1")
    Integer countArticleView();


    /**
     * 获得随机文章
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    @Select("select * from  article WHERE article_status = 1 ORDER BY  RAND() limit #{limit}")
    List<Article> listRandomArticle(@Param(value = "limit") Integer limit);

    /**
     * 热评文章
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    @Select("select * from  article WHERE article_status = 1 ORDER BY article_comment_count DESC,article_order DESC, article_id DESC limit #{limit}")
    List<Article> listArticleByCommentCount(@Param(value = "limit") Integer limit);


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
     * 文章归档
     *
     * @return
     */
    @Select("select article_id, article_user_id, article_title, article_create_time, article_thumbnail from article where article_status = 1 order by article_id desc")
    List<Article> listAllNotWithContent();

    /**
     * 更新文章
     *
     * @param article 文章
     * @return 影响行数
     */
    Integer update(Article article);

    /**
     * 更新文章的评论数
     *
     * @param articleId 文章ID
     */
    @Update("update article set article_comment_count= (select count(*) from comment where article.article_id=comment.comment_article_id) where article_id=#{articleId}")
    void updateCommentCount(@Param(value = "articleId") Integer articleId);

    /**
     * 获得一个用户的文章id集合
     *
     * @param userId
     * @return
     */
    @Select("select * from article  where article_user_id=#{userId}")
    List<Integer> listArticleIdsByUserId(Integer userId);
}
