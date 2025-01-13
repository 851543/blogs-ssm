package blog.controller.after;

import blog.entity.Notice;
import blog.entity.Page;
import blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 后台公告模块
 */
@Controller
@RequestMapping("/announcement")
public class AnnouncementController implements HandlerInterceptor {

    /**
     *announcement公告业务接口
     */
    @Autowired
    private NoticeService noticeService;

    /**
     * 查询全部公告
     * @return
     */
    @RequestMapping("/all")
    public ModelAndView allAnnouncements(){
        ModelAndView modelAndView = new ModelAndView();
        List<Notice> list = noticeService.noticeAll();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("/after/announcement/allAnnouncements");
        return modelAndView;
    }
    /**
     * 增加公告(先跳去增加页面那里先)
     * @return
     */
    @RequestMapping(value = "/add")
    public String addAnnouncements(){
        return "/after/announcement/addAnnouncement";
    }
    /**
     * 增加公告(这里就是对其输入内容，然后重定向回到展示全部公告那里)
     * @param notice
     * @return
     */
    @RequestMapping(value = "/addannouncement")
    public String insertAnnouncements(Notice notice){
        notice.setNoticeCreateTime(new Date());
        notice.setNoticeUpdateTime(new Date());
        //直接默认状态,1为显示
        notice.setNoticeStatus(1);
        notice.setNoticeOrder(1);
        noticeService.insertNotice(notice);
        return "redirect:/announcement/all";
    }
    /**
     * 根据id删除公告
     * @param id
     * @return
     */
    @RequestMapping("/del/{id}")
    public String delAnnouncements(@PathVariable("id") Integer id){
      Integer rows = noticeService.delectNotice(id);
      return "redirect:/announcement/all";
    }
    /**
     * 编辑公告(修改)
     */
    @RequestMapping(value = "/editSubmit")
    public String deleteNotice(Notice notice){
        //  更新修改时间
        notice.setNoticeUpdateTime(new Date());
        Integer integer = noticeService.updateNotice(notice);
        return "redirect:/announcement/all";
    }

    /**
     * 编辑公告(查询)
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editNotice(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Notice notice = noticeService.noticeById(id);
        modelAndView.addObject("notice",notice);
        //去到编辑公告页面
        modelAndView.setViewName("/after/announcement/editAnnouncement");
        return modelAndView;
    }
}
