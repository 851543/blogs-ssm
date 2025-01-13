package blog.controller.after;

import blog.controller.dao.UserRole;
import blog.entity.User;
import blog.service.OptionsService;
import blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户模块
 */
@Controller
@RequestMapping("/user")
public class AfterUserController implements HandlerInterceptor {

    /**
     * user用户业务接口
     */
    @Autowired
    private UserService userService;

    /**
     * 后台用户全部用户显示
     * @return
     */
    @RequestMapping("/all")
    public ModelAndView allUser(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> list = userService.userList();
        modelAndView.addObject("userList",list);
        modelAndView.setViewName("/after/user/allUsers");
        return modelAndView;
    }

    /**
     * 后台添加用户显示
     * @return
     */
    @RequestMapping("/add")
    public String addUser(){
        return "/after/user/addUser";
    }

    /**
     * 后台添加用户信息提交
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertSubmitUser(User user){
//        if (userService.nameIsNullUser(user.getUserName()) == null && userService.emailIsNullUser(user.getUserEmail()) == null) {
            //  注册时间
            user.setUserRegisterTime(new Date());
            //  默认正常状态
            user.setUserStatus(1);
            //  默认user
            user.setUserRole(UserRole.USER.getValue());
            userService.addUser(user);
//        }
        return "redirect:/user/all";
    }

    /**
     * id删除用户
     */
    @RequestMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.removeUser(id);
        return "redirect:/user/all";
    }

    /**
     * id编辑用户
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.userById(id);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/after/user/editUser");
        return modelAndView;
    }

    /**
     * id编辑用户-提交
     * @param user
     * @return
     */
    @RequestMapping(value = "editSubmit",method = RequestMethod.POST)
    public String editSubmitUser(User user, HttpSession session){
        Integer integer = userService.reviseByIdUser(user);
        User users = (User) session.getAttribute("user");
        if (!UserRole.ADMIN.getValue().equals(users.getUserRole())) {
            //编辑用户-去文章
            return "redirect:/essay/all";
        }
        //编辑管理员-去用户
        return "redirect:/user/all";
    }

    /**
     * 检查用户名是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String checkUserName(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        User user = userService.nameIsNullUser(username);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if (user != null) {
            if (user.getUserId() != id) {
                map.put("code", 1);
                map.put("msg", "用户名已存在！");
            }
        } else {
            map.put("code", 0);
            map.put("msg", "");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 检查Email是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkUserEmail", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String checkUserEmail(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String email = request.getParameter("email");
        User user = userService.emailIsNullUser(email);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if (user != null) {
            if (user.getUserId() != id) {
                map.put("code", 1);
                map.put("msg", "电子邮箱已存在！");
            }
        } else {
            map.put("code", 0);
            map.put("msg", "");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

}
