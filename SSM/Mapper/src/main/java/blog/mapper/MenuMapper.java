package blog.mapper;

import blog.entity.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理
@Repository
public interface MenuMapper {
    //查询全部数据导航栏
    @Select("select * from menu order by menu_order desc,menu_id asc")
    List<Menu> selectMenus();
    //增加菜单项目
    @Insert("insert into menu value(#{menuId},#{menuName},#{menuUrl},#{menuLevel},#{menuIcon},#{menuOrder})")
    Integer insertsetup(Menu menu);
    //删除菜单
    @Delete("delete from menu where menu_id=#{menuId}")
    Integer delectsetup(Integer id);
    //修改菜单(查)
    @Select("select * from menu where menu_id=#{menuId}")
    Menu selMenu(Integer id);
    //修改菜单(改)
    @Update("update menu set menu_name=#{menuName},menu_url=#{menuUrl},menu_level=#{menuLevel},menu_icon=#{menuIcon},menu_order=#{menuOrder} where menu_id=#{menuId}")
    Integer updateSetup(Menu menu);

}
