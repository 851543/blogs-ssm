package blog.controller.after;

import blog.controller.dao.MyUtils;
import blog.controller.dao.UserRole;
import blog.entity.Article;
import blog.entity.Comment;
import blog.entity.User;
import blog.service.ArticleService;
import blog.service.CommentService;
import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.*;
//控制器
@Controller
//父路径
@RequestMapping("/comments")
public class CommentsController implements HandlerInterceptor {
    //注入接口
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;



    /**
     * 查询全部评论
     *  ModelAndView为视图解析器,可以指定返回的页面名称
     * 评论页面
     * 我发送的评论
     *
     *    @param pageIndex 页码
     *    @param pageSize  页大小
     *    @return modelAndView
     */
    @RequestMapping("/all")
    public String allComments(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                    HttpSession session,
                                    Model model){
        User user = (User) session.getAttribute("user");
        HashMap<String, Object> criteria = new HashMap<>();
        if (!UserRole.ADMIN.getValue().equals(user.getUserRole())) {
            // 用户查询自己的文章, 管理员查询所有的
            criteria.put("userId", user.getUserId());
        }
        PageInfo<Comment> commentPageInfo = commentService.listCommentByPage(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", commentPageInfo);
        model.addAttribute("pageUrlPrefix", "/comments/all?pageIndex");
        return "/after/comments/allComments";
    }

    /**
     * 评论页面
     * 我收到的评论
     *
     * @param pageIndex 页码
     * @param pageSize  页大小
     * @return modelAndView
     */
    @RequestMapping(value = "/receive")
    public String myReceiveComment(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                   HttpSession session,
                                   Model model) {
        User user = (User) session.getAttribute("user");
        PageInfo<Comment> commentPageInfo = commentService.listReceiveCommentByPage(pageIndex, pageSize, user.getUserId());
        model.addAttribute("pageInfo", commentPageInfo);
        model.addAttribute("pageUrlPrefix", "/comments/receive?pageIndex");
        return "after/comments/allComments";
    }

    /**
     * 编辑评论(查询)
     */
    @RequestMapping("/sel/{id}")
    public ModelAndView selComments(@PathVariable("id")Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Comment comment = commentService.selById(id);
        modelAndView.addObject("comment",comment);
        modelAndView.setViewName("/after/comments/editComments");
        return modelAndView;
    }
    /**
     * 编辑评论(修改)
     */
    @RequestMapping(value = "updacomments")
    public String updateComments(Comment comment){
        //更新评论时间
        comment.setCommentCreateTime(new Date());
        Integer integer = commentService.updateComments(comment);
        return "redirect:/comments/all";
    }
    /**
     * 回复评论(查)
     */
    @RequestMapping("/select/{id}")
    public ModelAndView selectComments(@PathVariable("id")Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Comment com = commentService.selComments(id);
        modelAndView.addObject("comment",com);
        modelAndView.setViewName("/after/comments/replycomments");
        return modelAndView;
    }
    /**
     * 回复评论(增加)
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insertComments(HttpServletRequest request, Comment comment, HttpSession session){
        //文章评论数+1
        Article article = articleService.getArticleByStatusAndId(1, comment.getCommentArticleId());
        User user = (User) session.getAttribute("user");
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        comment.setCommentAuthorAvatar(user.getUserAvatar());
        comment.setCommentAuthorName(user.getUserNickname());
        comment.setCommentAuthorEmail(user.getUserEmail());
        comment.setCommentAuthorUrl(user.getUserUrl());
        article.setArticleCommentCount(article.getArticleCommentCount() + 1);
        articleService.updateArticle(article);
        //添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        if (Objects.equals(user.getUserId(), article.getArticleUserId())) {
            //博主
            comment.setCommentRole(1);
        } else {
            //其他用户
            comment.setCommentRole(0);
        }
        commentService.insertComments(comment);
        return "redirect:/comments/all";
    }


}
