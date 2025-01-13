package blog.controller.former;

import blog.entity.*;
import blog.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController implements HandlerInterceptor {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private  ArticleService articleService;

    @RequestMapping("/{id}")
    public ModelAndView noticeFTwo(@PathVariable("id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        Notice noticeList=noticeService.noticeById(id);
        modelAndView.addObject("notice",noticeList);


        //导航栏显示(注意这个别名是allCategoryList是你jsp中循环的那个名字
        List<Category> categories=categoryService.selectAlls();
        modelAndView.addObject("allCategoryList",categories);


        //顶部链接以及留言板
        List<Menu> menuList=menuService.selectList();
        modelAndView.addObject("menuList",menuList);


        //博客头部的内容
        Options options=optionsService.selOptions();
        modelAndView.addObject("options",options);


        List<Article> articleList=articleService.articleId();
        modelAndView.addObject("mostCommentArticleList",articleList);

        modelAndView.setViewName("former/Page/noticeDetail");
        return  modelAndView;

    }
}
