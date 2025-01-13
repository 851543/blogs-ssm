package blog.service.impl;

import blog.entity.Article;
import blog.entity.ArticleCategoryRef;
import blog.mapper.ArticleCategoryRefMapper;
import blog.mapper.ArticleMapper;
import blog.service.ArticleCategoryRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//自动注入到spring容器中
@Service
public class ArticleCategoryRefServiceImpl implements ArticleCategoryRefService {

    //对类成员变量、方法及构造函数进行标注,让 spring 完成 bean 自动装配的工作
    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;
    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public List<ArticleCategoryRef> articleCategoryRefList() {
        List<ArticleCategoryRef> list = articleCategoryRefMapper.selectArticleCategoryRefs();
        return list;
    }

    @Override
    public void addArticleCategoryRef(ArticleCategoryRef record) {
        articleCategoryRefMapper.insertArticleCategoryRef(record);
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return articleCategoryRefMapper.selectCategoryIdByArticleId(articleId);
    }


    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        if (cateIds == null || cateIds.size() == 0) {
            return null;
        }
        return articleCategoryRefMapper.findArticleByCategoryIds(cateIds, limit);
    }
    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        return articleCategoryRefMapper.listArticleByViewCount(limit);
    }
    @Override
    public Article getAfterArticle(Integer id) {
        return articleCategoryRefMapper.getAfterArticle(id);
    }

    @Override
    public Article getPreArticle(Integer id) {
        return articleCategoryRefMapper.getPreArticle(id);
    }


}
