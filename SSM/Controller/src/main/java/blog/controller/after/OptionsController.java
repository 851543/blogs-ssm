package blog.controller.after;

import blog.entity.Comment;
import blog.entity.Options;
import blog.entity.User;
import blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 设置下的主要选项下的基本信息和小工具都写一起，jsp页面用的是一个form表单
 */
//控制器
@Controller
//父路径
@RequestMapping("/options")
public class OptionsController implements HandlerInterceptor {
    //注入接口
    @Autowired
    private OptionsService optionsService;
    /**
     * 主要选项的查询
     *  ModelAndView为视图解析器,可以指定返回的页面名称
     */
    @RequestMapping("all")
    public ModelAndView alloptions(){
        ModelAndView modelAndView = new ModelAndView();
        Options option = optionsService.selOptions();
        modelAndView.addObject("option",option);
        modelAndView.setViewName("/after/options/index");
        return modelAndView;
    }
    /**
     * 主要选项的修改
     */
    @RequestMapping(value = "upoptions")
    public String updatecomments(Options options){
        optionsService.updateOptions(options);
        return "redirect:/options/all";
    }
    /**
     * 基本资料（查）(右上角登录)
     */
    @RequestMapping(value = "/after/profile")
    public ModelAndView userProfileView(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User sessionUser = (User) session.getAttribute("user");
        //接口那边写了根据id去查User,getUserId()获取相对应的id之后,把User表的数据查出来(用户的基本资料)
        User user = optionsService.selectzhu(sessionUser.getUserId());
        //把时间加入
        user.setUserRegisterTime(new Date());
        user.setUserLastLoginTime(new Date());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/after/options/Basicinformation");
        return modelAndView;
    }
    /**
     * 编辑用户基本资料(查)
     * 查出来直接调用user那边写的编辑页面(之前写的一整套可以直接用)
     */
    @RequestMapping("/sel/{id}")
    public ModelAndView selcomments(@PathVariable("id")Integer id){
        ModelAndView modelAndView = new ModelAndView();
        //根据id查出来,直接调用user那边的修改
        User user = optionsService.selzhu(id);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/after/user/editUser");
        return modelAndView;
    }
}
