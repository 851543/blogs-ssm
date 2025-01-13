package blog.service;

import blog.entity.Menu;

import java.util.List;

public interface MenuService {
    //查询全部数据的业务接口
    List<Menu> selectList();
    //增加菜单项目
    Integer insertsetup(Menu menu);
    //根据删除菜单
    Integer delectsetup(Integer id);
    //修改菜单(查)
    Menu selMenu(Integer id);
    //修改菜单(改)
    Integer updateSetup(Menu menu);
}
