package blog.mapper;

import blog.entity.Category;
import blog.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理
@Repository
public interface UserMapper {
    /**
     * 查询全部数据
     * @return
     */
    @Select("select * from user")
    List<User> selectUsers();

    /**
     * 添加用户全部信息
     * @param user
     * @return
     */
    @Insert("insert into user value(#{userId},#{userName},#{userPass},#{userNickname},#{userEmail},#{userUrl}," +
            "#{userAvatar},#{userLastLoginIp},#{userRegisterTime},#{userRegisterTime},#{userStatus},#{userRole})")
    Integer insertUser(User user);

    /**
     * id删除用户
     *  @param id
     */
    @Delete("delete from user where user_id=#{userId}")
    Integer deleteByIdUser(Integer id);

    /**
     * id查询用户信息
     * @param id
     * @return
     */
    @Select("select * from user where user_id=#{userId}")
    User selectByIdUser(Integer id);

    /**
     * id修改用户信息
     * @param user
     * @return
     */
    @Update("update user set user_name=#{userName},user_pass=#{userPass},user_nickname=#{userNickname},user_email=#{userEmail},user_url=#{userUrl},user_avatar=#{userAvatar},user_status=${userStatus} where user_id=#{userId}")
    Integer updateByIdUser(User user);

    /**
     * 判断用户名是否存在
     * @return
     */
    @Select("select * from user where user_name=#{userName}")
    User userNameIsNull(String name);

    /**
     * 判断邮箱是否存在
     * @return
     */
    @Select("select * from user where user_email=#{userEmail}")
    User userEmailIsNull(String email);

    /**
     * 注册
     * @param user
     * @return
     */
    @Insert("insert into user value(#{userId},#{userName},#{userPass},#{userNickname},#{userEmail},#{userUrl},#{userAvatar}," +
            "#{userLastLoginIp},#{userRegisterTime},#{userLastLoginTime},#{userStatus})")
    Integer inUser(User user);

}
