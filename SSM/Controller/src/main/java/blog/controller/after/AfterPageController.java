package blog.controller.after;

import blog.entity.Page;
import blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


/**
 * 后台页面模块
 */
//控制器
@Controller
//主路径
@RequestMapping(value = "/page")
public class AfterPageController implements HandlerInterceptor {

    /**
     * page页面业务接口
     */
    @Autowired
    private PageService pageService;

    /**
     * 后台全部页面
     * @return
     */
    @RequestMapping(value = "/all")
    public ModelAndView allPages(){
        ModelAndView modelAndView  = new ModelAndView();
        //page全部的数据
        List<Page> list = pageService.pageList();
        //封装打包
        modelAndView.addObject("pageList",list);
        modelAndView.setViewName("after/page/allPages");
        return modelAndView;
    }
    /**
     * 后台添加页面
     * @return
     */
    @RequestMapping(value = "/add")
    public String addPage(){
        return "after/page/addPage";
    }

    /**
     * 后台添加页面-添加数据成功-全部页面
     * @param page
     * @return
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String addSubmitPage(Page page){
        //查询这个别名
        String  key = pageService.byKeyIsNull(page.getPageKey());
        //定义重定向路径
        String action = null;
        // 如果别名不存在就新增数据并且到全部页面,存在就在当前页面刷新
        if (key == null ){
            page.setPageCreateTime(new Date());
            page.setPageUpdateTime(new Date());
            //默认显示状态
            page.setPageStatus(1);
            pageService.addPages(page);
            action = "redirect:/page/all";
        }else{
            action = "redirect:/page/add";
        }
        return action;
    }

    /**
     * 根据id编辑页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Page page = pageService.pageById(id);
        modelAndView.addObject("page",page);
        modelAndView.setViewName("/after/page/editPage");
        return modelAndView;
    }

    /**
     * 根据id删除页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public String deletePage(@PathVariable("id") Integer id){
        Integer row = pageService.removeByIdPage(id);
        return "redirect:/page/all";
    }

    /**
     * 根据id编辑页面-编辑页面提交
     * @param page
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String deletePage(Page page) {
        //  更新修改时间
        page.setPageUpdateTime(new Date());
        Integer integer = pageService.reviseByIdPage(page);
        return "redirect:/page/all";
    }
}
