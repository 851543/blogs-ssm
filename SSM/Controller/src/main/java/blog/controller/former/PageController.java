package blog.controller.former;

import blog.entity.*;
import blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @date
 */
@Controller
public class PageController implements HandlerInterceptor {

    @Autowired
    private PageService pageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private TagService tagService;

    /**
     * 页面详情页面
     *
     * @param key
     * @return
     */
    @RequestMapping("/{key}")
    public String pageDetail(@PathVariable("key")String key, Model model) {
        Page page = pageService.getPageByKey(1, key);
        model.addAttribute("page", page);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "former/Page/page";

    }
    /**
     * 文章归档页面显示
     *
     * @return
     */
    @RequestMapping("/articleFile")
    public String articleFile(Model model) {
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList", articleList);
        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "former/Page/articleFile";
    }

    /**
     * 站点地图显示
     *
     * @return
     */
    @RequestMapping("/map")
    public String siteMap(Model model) {
        //文章显示
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList", articleList);
        //分类显示
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);
        //标签显示
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "former/Page/siteMap";
    }

    /**
     * 留言板
     *
     * @return
     */
    @RequestMapping( "/message")
    public String message(Model model) {
        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "former/Page/message";
    }

    /**
     * 友情链接
     * @param model
     * @return
     */
    @RequestMapping("/applyLink")
    public String applyLinkView(Model model)  {
        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "former/Page/applyLink";
    }

    /**
     * 友情链接提交
     * @param link
     */
    @RequestMapping(value = "/applyLinkSubmit",method = {RequestMethod.POST}, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public void applyLinkSubmit(Link link)  {
        //默认隐藏
        link.setLinkStatus(0);
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        linkService.addLink(link);
    }
}
