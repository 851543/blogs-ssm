package blog.service;

import blog.entity.Category;
import blog.entity.User;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface UserService {

    /**
     * 查询全部数据的业务接口
     * @return
     */
    List<User> userList();

    /**
     * 添加用户全部信息业务接口
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     * id删除用户业务接口
     * @param id
     * @return
     */
    Integer removeUser(Integer id);

    /**
     * id查询用户业务接口
     * @param id
     * @return
     */
    User userById(Integer id);

    /**
     * id修改用户业务接口
     * @param user
     * @return
     */
    Integer reviseByIdUser(User user);

    User nameIsNullUser(String n);

    User emailIsNullUser(String e);

    /**
     *  注册增加
     */
    Integer inUser(User user);
    /**
     * 查询目录的业务接口
     * @return
     */


}
