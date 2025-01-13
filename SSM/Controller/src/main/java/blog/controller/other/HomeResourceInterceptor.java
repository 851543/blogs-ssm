package blog.controller.other;

import blog.entity.Article;
import blog.entity.Category;
import blog.entity.Menu;
import blog.entity.Options;
import blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行加载的资源
 * @author
 */
@Component
public class HomeResourceInterceptor implements HandlerInterceptor {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private MenuService menuService;

    /**
     * 在请求处理之前执行，该方法主要是用于准备资源数据的，然后可以把它们当做请求属性放到WebRequest中
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws IOException {

        // 菜单显示
        List<Menu> menuList = menuService.selectList();
        request.setAttribute("menuList", menuList);
        //分类
        List<Category> categoryList = categoryService.listCategory();
        request.setAttribute("allCategoryList", categoryList);

        //获得网站概况
        List<String> siteBasicStatistics = new ArrayList<String>();
        //文章总数 1为已分布
        siteBasicStatistics.add(articleService.countArticle(1) + "");
        //评论总数
        siteBasicStatistics.add(articleService.countArticleComment() + "");
        //分类总数
        siteBasicStatistics.add(categoryService.countCategory() + "");
        //标签总数
        siteBasicStatistics.add(tagService.countTag() + "");
        //链接总数 1为显示
        siteBasicStatistics.add(linkService.countLink(1) + "");
        //浏览量总数
        siteBasicStatistics.add(articleService.countArticleView() + "");
        //封装数据
        request.setAttribute("siteBasicStatistics", siteBasicStatistics);

        //最后更新的文章
        Article lastUpdateArticle = articleService.getLastUpdateArticle();
        //封装数据
        request.setAttribute("lastUpdateArticle", lastUpdateArticle);

        //页脚显示
        //博客基本信息显示(Options)
        Options options = optionsService.selOptions();
        request.setAttribute("options", options);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)  {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)  {

    }
}