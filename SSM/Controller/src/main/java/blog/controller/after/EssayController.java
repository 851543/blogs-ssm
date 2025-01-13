package blog.controller.after;

import blog.controller.dao.ArticleParam;
import blog.controller.dao.UserRole;
import blog.entity.Article;
import blog.entity.Category;
import blog.entity.Tag;
import blog.entity.User;
import blog.service.ArticleService;
import blog.service.CategoryService;
import blog.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import cn.hutool.http.HtmlUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 后台文章模块
 */
//控制器
@Controller
//父路径
@RequestMapping("/essay")
public class EssayController implements HandlerInterceptor {

    /**
     * 文章
     */
    @Autowired
    private ArticleService articleService;

    /**
     * 分类
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 标签
     */
    @Autowired
    private TagService tagService;


    /**
     * 后台全部文章显示
     *
     * @return modelAndView
     */
    @RequestMapping(value = "all")
    public String allEssay(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) String status, Model model,
                           HttpSession session) {

        HashMap<String, Object> criteria = new HashMap<>(1);

        if (status == null) {
            model.addAttribute("pageUrlPrefix", "/essay/all?pageIndex");
        } else {
            criteria.put("status", status);
            model.addAttribute("pageUrlPrefix", "/essay/all?status=" + status + "&pageIndex");
        }

        //获取用户角色：admin/user
        User user = (User) session.getAttribute("user");

        //用户查询自己的文章, 管理员查询所有的
        if (!UserRole.ADMIN.getValue().equals(user.getUserRole())) {
            // 用户查询自己的文章
            criteria.put("userId", user.getUserId());
        }

        //分页文章
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);
        return "/after/essay/allArticles";
    }

    /**
     * 后台添加文章页面显示
     *
     * @return
     */
    @RequestMapping(value = "/add")
    public String insertArticleView(Model model) {
        List<Category> categoryList = categoryService.listCategory();
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("tagList", tagList);
        return "/after/essay/writeArticles";
    }

    /**
     * 删除文章
     *
     * @param id 文章ID
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
        return "redirect:/essay/all";
    }

    /**
     * 后台首页添加文章提交操作
     *
     * @param articleParam
     * @return
     */
    @RequestMapping(value = "/insertDraftSubmit", method = RequestMethod.POST)
    public String insertDraftSubmit(HttpSession session, ArticleParam articleParam) {
        Article article = new Article();
        //用户ID
        User user = (User) session.getAttribute("user");
        if (user != null) {
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(articleParam.getArticleTitle());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        article.setArticleThumbnail(articleParam.getArticleThumbnail());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.insertArticle(article);
        return "redirect:/after/manage";
    }

    /**
     * 后台添加文章提交操作
     *
     * @param articleParam
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertArticleSubmit(HttpSession session, ArticleParam articleParam) {
        //文章
        Article article = new Article();
        //用户ID 文章与登陆用户id进行绑定
        User user = (User) session.getAttribute("user");
        if (user != null) {
            //绑定
            article.setArticleUserId(user.getUserId());
        }
        //文章标题
        article.setArticleTitle(articleParam.getArticleTitle());
        //文章摘要
        int summaryLength = 150;
        //文章内容-编码器处理
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        article.setArticleThumbnail(articleParam.getArticleThumbnail());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());

        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.insertArticle(article);
        return "redirect:/essay/all";
    }




    /**
     * 编辑文章页面显示
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String editArticleView(@PathVariable("id") Integer id, Model model) {

        Article article = articleService.getArticleByStatusAndId(null,id);

        model.addAttribute("article", article);

        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);

        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        return "/after/essay/editArticles";
    }

    /**
     * 编辑文章提交
     *
     * @param articleParam
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editArticleSubmit(ArticleParam articleParam) {
        //文章
        Article article = new Article();
        article.setArticleThumbnail(articleParam.getArticleThumbnail());
        article.setArticleId(articleParam.getArticleId());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(article.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);
        articleService.updateArticleDetail(article);
        return "redirect:/essay/all";
    }

    /**
     * 全部分类(查询全部)
     * @return
     */
    @RequestMapping("/categorized")
    public ModelAndView afterClassIfy(){
        ModelAndView modelAndView  = new ModelAndView();
        List<Category> list = categoryService.listCategory();
        modelAndView.addObject( "list",list);
        modelAndView.setViewName("after/essay/categorized");
        return modelAndView;
    }

    /**
     * /全部分类(增加的)
     * @param category
     * @return
     */
    @RequestMapping(value = "/classify/insertSubmit",method = RequestMethod.POST)
    public String insertClassIfy(Category category){
        categoryService.insertCategory(category);
        return "redirect:/essay/categorized";
    }

    /**
     * 根据id删除(全部分类的)
     * @param id
     * @return
     */
    @RequestMapping(value = "/dele/{id}")
    public String deleteCategory(@PathVariable("id") Integer id)  {
        //禁止删除有文章的分类
       Integer rows = categoryService.delectCategory(id);
        return "redirect:/essay/categorized";
    }


    /**
     * 编辑分类(查询)
      */
    @RequestMapping("/sel/{id}")
    public ModelAndView selCategories(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Category category = categoryService.selCategories(id);
        modelAndView.addObject("category",category);
        modelAndView.setViewName("/after/essay/classification");
        return modelAndView;
    }
    /**
     * 编辑分类(修改)
     */
    @RequestMapping("/up")
    public String updaCategories(Category category){
        Integer up = categoryService.updaCategories(category);
        return "redirect:/essay/categorized";
    }
    /**
     * 全部标签(查询全部)
     * @return
     */
    @RequestMapping("/label")
    public ModelAndView tagService(){
        ModelAndView modelAndView = new ModelAndView();
        List<Tag> list = tagService.selectTagList();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("after/essay/label");
        return modelAndView;
    }

    /**
     * 全部标签(增加)
     * @param tag
     * @return
     */
    @RequestMapping(value = "/addSubmit",method = RequestMethod.POST)
    public String insertTag(Tag tag){
        tagService.insertTagList(tag);
        return "redirect:/essay/label";
    }

    /**
     * 全部标签(删除)
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}")
    public String deleteTag(@PathVariable("id") Integer id)  {
        //禁止删除有文章的分类
        Integer row = tagService.deleteTagList(id);
        return "redirect:/essay/label";
    }



    /**
     * 全部标签编辑（查询）
     */
    @RequestMapping("/tag/{id}")
    public ModelAndView selTag(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Tag tag = tagService.selTag(id);
        modelAndView.addObject("tag",tag);
        modelAndView.setViewName("/after/essay/editLable");
        return modelAndView;
    }
    /**
     * 全部标签编辑（修改）
     */
    @RequestMapping("updaTag")
    public String updaTag(Tag tag){
        Integer up = tagService.updaTag(tag);
        return "redirect:/essay/label";
    }
}
