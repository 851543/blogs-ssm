import blog.entity.*;
import blog.entity.Category;
import blog.entity.Page;
import blog.entity.User;
import blog.entity.Category;
import blog.service.*;
import blog.serviceModel.Model;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//注解引用SpringJUnit这个
@RunWith(SpringJUnit4ClassRunner.class)
//注解Contest寻找配置文件
@ContextConfiguration("classpath:application-context.xml")
public class TestMain {


    //注入用户接口
    @Autowired
    private UserService userService;

    //注入页面接口
    @Autowired
    private PageService pageService;
    //顶部导航烂
    @Autowired
    private  MenuService menuService;

    @Autowired
    private TagService tagServices;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;


//    注入博客头部的接口
    @Autowired
    private  OptionsService optionsService;

    @Autowired
    private  NoticeService noticeService;


    @Autowired
    private  CommentService commentService;

    @Autowired
    private  LinkService linkService;




    //测试方法
    @Test
    public void userTest(){
        // user数据测试
        List<User> users = userService.userList();
        System.out.println(users);

        Model model = new Model();
        model.Model();
    }
    @Test
    public void ceTest(){
//        List<Article> list = articleService.selectArticleListCe();
//        list.forEach(System.out::println);
        Category category = new Category();
        category.setCategoryName("aaa");
        Integer s = categoryService.insertCategory(category);
        System.out.println(s);
        List<Article> list = articleService.selectArticleListCe();
        list.forEach(System.out::println);
        Model model = new Model();
        model.Model();

    }
    //insert页面4个模板数据
    @Test
    public void insertPage(){
        // 4个站点别名
//        String[] keys = {"map","archiving","boards","friend"};
//        // 4个站点名
//        String[] titles = {"站点地图","文章归档","留言板","申请友链"};
//        //时间格式
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //page_status级别 1为显示 0为隐藏 2为模板
//        for (int i=0; i<4; i++){
//            Page page = new Page();
//            page.setPageKey(keys[i]);
//            page.setPageTitle(titles[i]);
//            page.setPageCreateTime(sdf.format(new Date()));
//            page.setPageUpdateTime(sdf.format(new Date()));
//            page.setPageStatus(2);
//            Integer integer = pageService.addFourPages(page);
//            System.out.println(integer);
//        }
    }
//    @Test
//    public void userIdTest(){
//        // user数据测试
//        List<User> lists=userService.userIdList();
//        System.out.println(lists);
//    }
    @Test
    public void addPages(){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Page page = new Page();
//        String key = pageService.byKeyIsNull("AA");
//        if (key == null ){
//            page.setPageCreateTime(sdf.format(new Date()));
//            page.setPageUpdateTime(sdf.format(new Date()));
//            pageService.addPages(page);
//        }

//        List<Article> list = articleService.selectArticleListCe();
//        list.forEach(System.out::println);
//        Category category = new Category();
//        category.setCategoryName("aaa");
//        Integer s = categoryService.insertCategory(category);
//        System.out.println(s);

//        List<Article> list = articleService.selectArticleListCe();
//        list.forEach(System.out::println);
        Category category = new Category();
        category.setCategoryName("aaa");
        Integer s = categoryService.insertCategory(category);
        System.out.println(s);
    }
    @Test
    public void  tagTest(){
        List<Tag> tagList=tagServices.selectTagList();
    System.out.println(tagList);
}
//测试导航栏以及他的子目录数据（调用的是mapper的方法）
    @Test
    public  void  categoryTest(){
        List<Category> categoryList=categoryService.selectAlls();
        System.out.println(categoryList);
    }
//测试顶部链接以及留言板
    @Test
    public  void  menuTest(){
        List<Menu> menuList=menuService.selectList();
        System.out.println(menuList);
    }
//    测试博客头部
    @Test
    public  void optionsTest(){
        Options optionsList=optionsService.selOptions();
        System.out.println(optionsList);
    }

    @Test
    public  void  commentTest(){
        List<Comment> commentList=commentService.commentList();
        System.out.println(commentList);
    }


    @Test
    public  void  noticeTest(){
        List<Notice> noticeList=noticeService.selectnoticeList(1);
        System.out.println(noticeList);
    }

    @Test
    public  void  lintTest(){
        List<Link> linkList=linkService.linkList();
        System.out.println(linkList);
    }

    @Test
    public  void  categoryIdTest(){
        Category categoryList=categoryService.selectCategoryId(1);
        System.out.println(categoryList);
    }


}
