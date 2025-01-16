package blog.controller.after;

import blog.controller.dao.UserRole;
import blog.entity.Article;
import blog.entity.Comment;
import blog.entity.Options;
import blog.entity.User;
import blog.mapper.ArticleMapper;
import blog.mapper.CommentMapper;
import blog.mapper.OptionsMapper;
import blog.service.ArticleService;
import blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 后台公共模块
 */
//控制器
@Controller
//主路径
@RequestMapping(value = "/after")
public class AfterController implements HandlerInterceptor {

    /**
     * 文章接口
     */
    @Autowired
    private ArticleService articleService;

    /**
     * 评论接口
     */
    @Autowired
    private CommentService commentService;

    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping("/manage")
    public String index(Model model, HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        Integer userId = null;
//        if (!UserRole.ADMIN.getValue().equals(user.getUserRole())) {
//            // 用户查询自己的文章, 管理员查询所有的
//            userId = user.getUserId();
//        }
//        //文章列表
//        List<Article> articleList = articleService.listRecentArticle(userId, 5);
//        model.addAttribute("articleList", articleList);
//
//        //评论列表
//        List<Comment> commentList = commentService.listRecentComment(userId, 5);
//        model.addAttribute("commentList", commentList);
        return "/after/manage";
    }
}
