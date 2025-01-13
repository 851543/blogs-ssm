package blog.service.impl;

import blog.entity.ArticleCategoryRef;
import blog.entity.ArticleTagRef;
import blog.mapper.ArticleCategoryRefMapper;
import blog.mapper.ArticleTagRefMapper;
import blog.service.ArticleTagRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//自动注入到spring容器中
@Service
public class ArticleTagRefServiceImpl implements ArticleTagRefService {

    //对类成员变量、方法及构造函数进行标注,让 spring 完成 bean 自动装配的工作
    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;

    @Override
    public List<ArticleTagRef> articleCategoryRefList() {
        List<ArticleTagRef> list = articleTagRefMapper.selectArticleTagRefs();
        return list;
    }

    @Override
    public void addArticleTagRef(ArticleTagRef record) {
        articleTagRefMapper.insertArticleTagRef(record);
    }
}
