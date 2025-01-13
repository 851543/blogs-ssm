package blog.controller.after;

import blog.entity.Comment;
import blog.entity.Menu;
import blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

//控制器
@Controller
//父路径
@RequestMapping("/setup")

public class setupController implements HandlerInterceptor {
    //注入接口
    @Autowired
    private MenuService menuService;
    /**
     * 查询全部顶部菜单
     * @return
     */
    @RequestMapping("/all")
    public ModelAndView allsetup(){
        ModelAndView modelAndView = new ModelAndView();
        List<Menu> list = menuService.selectList();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("/after/setup/menu");
        return modelAndView;
    }
    /**
     * 增加菜单项目
     */
    @RequestMapping("insert")
    public String insertSetup(Menu menu){
        //因为增加那里没有Odrder,这里直接设置默认Oderor是1
        menu.setMenuOrder(1);
        Integer rows = menuService.insertsetup(menu);
        menu.setMenuOrder(1);
        return "redirect:/setup/all";
    }
    /**
     * 根据id删除菜单
     */
    @RequestMapping("/delete/{id}")
    public String delectsetup(@PathVariable("id")Integer id){
        Integer row = menuService.delectsetup(id);
        return "redirect:/setup/all";
    }
    /**
     * 修改菜单(查)
     */
    @RequestMapping("/sel/{id}")
    public ModelAndView selsetup(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Menu m = menuService.selMenu(id);
        modelAndView.addObject("m",m);
        modelAndView.setViewName("/after/setup/upMenu");
        return modelAndView;
    }
    /**
     * 修改菜单(改)
     */
    @RequestMapping(value = "updaSetup")
    public String updateSetup(Menu menu){
        Integer inte = menuService.updateSetup(menu);
        return "redirect:/setup/all";
    }
}
