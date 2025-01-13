package blog.service.impl;


import blog.entity.*;
import blog.mapper.*;
import blog.service.ArticleService;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//自动注入到spring容器中
@Service
public class ArticleServiceImpl implements ArticleService {
    //对类成员变量、方法及构造函数进行标注,让 spring 完成 bean 自动装配的工作
    //注入
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 文章和标签中间接口
     */
    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;

    /**
     * 用户接口
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 文章和分类中间接口
     */
    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    /**
     * 评论接口
     * @return
     */
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Article> articleList() {
        List<Article> list = articleMapper.selectArticles();
        return list;
    }
    //全部文章的查询全部数据
    @Override
    public List<Article> selectArticleListCe() {
        List<Article> list = articleMapper.selectArticlesCe();
        return list;
    }
    //全部文章的删除
    @Override
    public Integer deletearticleList(Integer id) {
        Integer ro = articleMapper.deleteArticles(id);
        return ro;
    }

    //全部文章的增加
    @Override
    public Integer insertArticles(Article article) {
        Integer rows = articleMapper.insertArticles(article);
        return rows;
    }

    //查询全部文章，放在一进后台那里
    @Override
    public List<Article> selArticleList() {
        List<Article> list = articleMapper.selArticles();
        return list;
    }

    @Override
    public List<Article> articleId() {
        List<Article> articleList=articleMapper.selectArticlesId();

        return articleList;
    }

    @Override
    public List<Article> articleSuiJi() {
        List<Article> articleList=articleMapper.selectArticlesuiji();
        return articleList;
    }

    /**
     * 分页文章处理
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示多少
     * @param criteria  查询条件
     * @return
     */
    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        // 初始页 页面数量
        PageHelper.startPage(pageIndex, pageSize);
        //文章查询
        List<Article> articleList = articleMapper.findAll(criteria);
        //遍历Article信息
        for (int i = 0; i < articleList.size(); i++) {
            //封装CategoryList
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(articleList.get(i).getArticleId());
            //给个空值填充
            if (categoryList == null || categoryList.size() == 0) {
                categoryList = new ArrayList<>();
                categoryList.add(Category.Default());
            }

            //分类填充
            articleList.get(i).setCategoryList(categoryList);
            //用户填充
            articleList.get(i).setUser(userMapper.selectByIdUser(articleList.get(i).getArticleUserId()));
        }
        return new PageInfo<>(articleList);
    }


    /**
     * 添加文章
     *
     * @param article 文章
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertArticle(Article article) {
        //添加文章
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(1);
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        article.setArticleOrder(1);
        if (StringUtils.isEmpty(article.getArticleThumbnail())) {
            //文章图片显示
            article.setArticleThumbnail("/img/former/img_" + RandomUtil.randomNumbers(1) + ".jpg");
        }
        //添加文章
        articleMapper.insertArticles(article);

        //添加分类和文章关联
        for (int i = 0; i < article.getCategoryList().size(); i++) {
            ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(), article.getCategoryList().get(i).getCategoryId());
            articleCategoryRefMapper.insertArticleCategoryRef(articleCategoryRef);
        }

        //添加标签和文章关联
        for (int i = 0; i < article.getTagList().size(); i++) {
            ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), article.getTagList().get(i).getTagId());
            articleTagRefMapper.insertArticleTagRef(articleTagRef);
        }

    }

    /**
     * 删除文章
     *
     * @param id 文章ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticles(id);
        // 删除分类关联
        articleCategoryRefMapper.deleteByArticleId(id);
        // 删除标签管理
        articleTagRefMapper.deleteByArticleId(id);
        // 删除评论
        commentMapper.deleteComments(id);
    }

    /**
     * 文章详情页面显示
     * @param id     文章ID
     * @return 文章
     */
    @Override
    public Article getArticleByStatusAndId(Integer status,Integer id) {
        Article article = articleMapper.getArticleByStatusAndId(status,id);
        if (article != null) {
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(article.getArticleId());
            List<Tag> tagList = articleTagRefMapper.listTagByArticleId(article.getArticleId());
            article.setCategoryList(categoryList);
            article.setTagList(tagList);
        }
        return article;
    }

    /**
     * 修改文章详细信息
     *
     * @param article 文章
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleDetail(Article article) {
        article.setArticleUpdateTime(new Date());
        articleMapper.updateArticle(article);

        if (article.getTagList() != null) {
            //删除标签和文章关联
            articleTagRefMapper.deleteByArticleId(article.getArticleId());
            //添加标签和文章关联
            for (int i = 0; i < article.getTagList().size(); i++) {
                ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), article.getTagList().get(i).getTagId());
                articleTagRefMapper.insertArticleTagRef(articleTagRef);
            }
        }

        if (article.getCategoryList() != null) {
            //添加分类和文章关联
            articleCategoryRefMapper.deleteByArticleId(article.getArticleId());
            //删除分类和文章关联
            for (int i = 0; i < article.getCategoryList().size(); i++) {
                ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(), article.getCategoryList().get(i).getCategoryId());
                articleCategoryRefMapper.insertArticleCategoryRef(articleCategoryRef);
            }
        }
    }

    /**
     * 获得最新文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    @Override
    public List<Article> listRecentArticle(Integer userId, Integer limit) {
        return articleMapper.listArticleByLimit(userId, limit);
    }

    /**
     * 获取文章总数
     *
     * @param status 状态
     * @return 数量
     */
    @Override
    public Integer countArticle(Integer status) {
        Integer count = 0;
        count = articleMapper.countArticle(status);
        return count;
    }

    /**
     * 获得最后更新记录
     *
     * @return 文章
     */
    @Override
    public Article getLastUpdateArticle() {
        return articleMapper.getLastUpdateArticle();
    }

    /**
     * 获取评论总数
     *
     * @return 数量
     */
    @Override
    public Integer countArticleComment() {
        Integer count = 0;
        count = articleMapper.countArticleComment();
        return count;
    }

    /**
     * 获得浏览量总数
     *
     * @return 数量
     */
    @Override
    public Integer countArticleView() {
        Integer count = 0;
        count = articleMapper.countArticleView();
        return count;
    }


    @Override
    public List<Article> listRandomArticle(Integer limit) {
        return articleMapper.listRandomArticle(limit);
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return articleMapper.listArticleByCommentCount(limit);
    }

    @Override
    public List<Article> listAllNotWithContent() {
        return articleMapper.listAllNotWithContent();
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void updateCommentCount(Integer articleId) {
        articleMapper.updateCommentCount(articleId);
    }
}

