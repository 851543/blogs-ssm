package blog.service.impl;

import blog.mapper.UserMapper;
import blog.service.UserService;
import blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//自动注入到spring容器中
@Service
public class UserServiceImpl implements UserService {

    /**
     * user用户的sql接口
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户的全部数据查询业务接口实现
     * @return
     */
    @Override
    public List<User> userList() {
        List<User> list = userMapper.selectUsers();
        return list;
    }

    /**
     * 添加用户全部信息业务接口实现
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        Integer row = userMapper.insertUser(user);
        return row;
    }

    /**
     * id删除用户业务接口实现
     * @param id
     * @return
     */
    @Override
    public Integer removeUser(Integer id) {
        Integer row = userMapper.deleteByIdUser(id);
        return row;
    }

//    @Override
//    public List<Category> menuSelect() {
//        List<Category> menulist = userMapper.selectAll();
//        return menulist;
//    }

//    /**
//     * id删除用户业务接口实现
//
//     * @return
//     */
//    @Override
//    public List<Category> menuSelect() {
//        List<Category> menulist = userMapper.selectAll();
//        return menulist;
//    }


    /**
     * id查询用户业务接口实现
     * @param id
     * @return
     */
    @Override
    public User userById(Integer id) {
        User user = userMapper.selectByIdUser(id);
        return user;
    }

    /**
     * id修改用户业务接口实现
     * @param user
     * @return
     */
    @Override
    public Integer reviseByIdUser(User user) {
        Integer row = userMapper.updateByIdUser(user);
        return row;
    }

    /**
     * 判断用户名是否存在业务接口实现
     * @return
     */
    @Override
    public User nameIsNullUser(String n) {
        User name = userMapper.userNameIsNull(n);
        return name;
    }

    /**
     * 判断邮箱是否存在业务接口实现
     * @return
     */
    @Override
    public User emailIsNullUser(String e) {
        User email = userMapper.userEmailIsNull(e);
        return email;
    }

    /**
     * 注册增加
     *
     * @param user
     */
    @Override
    public Integer inUser(User user) {
        Integer zhu = userMapper.inUser(user);
        return zhu;
    }
}
