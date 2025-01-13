package blog.controller.other;

import blog.entity.*;
import blog.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;


@Controller
public class IndexController implements HandlerInterceptor{
//    注入标签业务层接口
    @Autowired
    private TagService tagService;
//注入导航栏以及他的子目录接口
    @Autowired
    private CategoryService categoryService;
//    注入顶部链接以及留言板的业务层接口
    @Autowired
    private MenuService menuService;

//    注入业务层的博客头部的接口
    @Autowired
    private  OptionsService optionsService;

//    公告声明
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private  CommentService commentService;

    @Autowired
    private  LinkService linkService;

    @Autowired
    private  ArticleService articleService;



    /**
     * 运行显示页面
     * @return
     */
//携带数据转发
    @RequestMapping(value = {"/","/article"})
    public String InderOneFirst(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model){
        //文章分页
        HashMap<String, Object> criteria = new HashMap<>(1);
        //已经发布的文章
        criteria.put("status", 1);
        //文章列表
        PageInfo<Article> articleList = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articleList);

        //公告
        List<Notice> noticeList = noticeService.selectnoticeList(1);
        model.addAttribute("noticeList", noticeList);
        //友情链接
        List<Link> linkList = linkService.listLink(1);
        model.addAttribute("linkList", linkList);

        //侧边栏显示
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(null, 10);
        model.addAttribute("recentCommentList", recentCommentList);

        //分页操作
        model.addAttribute("pageUrlPrefix", "/article?pageIndex");

        return "/former/index";
    }

//    跳转到模糊查询的jspmessage.jsp
    @RequestMapping(value = "/search")
    public String search(
            @RequestParam("keywords") String keywords,
            @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model) {
        //文章列表
        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("status", 1);
        criteria.put("keywords", keywords);
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);

        //侧边栏显示
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(null, 10);
        model.addAttribute("recentCommentList", recentCommentList);
        model.addAttribute("pageUrlPrefix", "/search?keywords=" + keywords + "&pageIndex");
        return "/former/Page/search";
    }

}
