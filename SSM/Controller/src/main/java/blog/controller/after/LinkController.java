package blog.controller.after;

import blog.entity.Link;
import blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 后台链接模块
 */
@Controller
@RequestMapping("/link")
public class LinkController implements HandlerInterceptor {

    /**
     * link链接业务接口
     */
    @Autowired
    private LinkService linkService;

    /**
     * 后台链接全部页面
     * @return
     */
    @RequestMapping("/all")
    public ModelAndView allLinks(){
        ModelAndView modelAndView = new ModelAndView();
        List<Link> linkList = linkService.linkList();
        modelAndView.addObject("linkList",linkList);
        modelAndView.setViewName("/after/link/allLinks");
        return modelAndView;
    }

    /**
     * 后台链接添加页面
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView addLink(){
        ModelAndView modelAndView = new ModelAndView();
        List<Link> linkList = linkService.linkList();
        modelAndView.addObject("linkList",linkList);
        modelAndView.setViewName("/after/link/addLink");
        return modelAndView;
    }

    /**
     * 后台链接全部页面-id删除链接
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deleteLink(@PathVariable("id") Integer id){
        Integer row = linkService.removeByIdLink(id);
        return "redirect:/link/all";
    }

    /**
     * 后台链接添加页面-id删除链接
     * @param id
     * @return
     */
    @RequestMapping("/deletes/{id}")
    public String deletesLink(@PathVariable("id") Integer id){
        Integer row = linkService.removeByIdLink(id);
        return "redirect:/link/add";
    }
    /**
     * 后台链接添加页面-添加链接
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertSubmitLink(Link link){
        Integer linkOrder = link.getLinkOrder();
        //  默认排名为0
        if (linkOrder == null){
            link.setLinkOrder(0);
        }
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        //默认显示状态
        link.setLinkStatus(1);
        linkService.addLink(link);
        return "redirect:/link/add";
    }

    /**
     * 后台链接全部页面和添加页面-id编辑页面显示
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView editLink(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();

        //显示编辑链接数据
        Link linkCustom = linkService.linkById(id);
        modelAndView.addObject("linkCustom",linkCustom);

        //显示全部链接数据
        List<Link> linkList = linkService.linkList();
        modelAndView.addObject("linkList",linkList);

        modelAndView.setViewName("/after/link/editLink");
        return modelAndView;
    }

    /**
     * 后台链接全部页面和添加页面-id编辑页面提交
     * @param link
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editSubmitLink(Link link){
        Integer linkOrder = link.getLinkOrder();
        //  默认排名为0
        if (linkOrder == null){
            link.setLinkOrder(0);
        }
        //  更新修改时间
        link.setLinkUpdateTime(new Date());
        Integer integer = linkService.reviseByIdLink(link);
        return "redirect:/link/all";
    }
}
