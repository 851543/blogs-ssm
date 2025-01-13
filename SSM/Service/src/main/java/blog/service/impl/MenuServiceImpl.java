package blog.service.impl;

import blog.entity.Menu;
import blog.mapper.MenuMapper;
import blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//自动注入到spring容器中
@Service
public class MenuServiceImpl implements MenuService {

    //对类成员变量、方法及构造函数进行标注,让 spring 完成 bean 自动装配的工作
    @Autowired
    private MenuMapper menuMapper;

//查询全部顶部菜单
    @Override
    public List<Menu> selectList() {
        List<Menu> list = menuMapper.selectMenus();
        return list;
    }
//增加菜单项目
    @Override
    public Integer insertsetup(Menu menu) {
        Integer rows = menuMapper.insertsetup(menu);
        return rows;
    }
//根据id删除菜单
    @Override
    public Integer delectsetup(Integer id) {
        Integer row = menuMapper.delectsetup(id);
        return row;
    }
    //修改菜单(查)
    @Override
    public Menu selMenu(Integer id) {
        Menu me = menuMapper.selMenu(id);
        return me;
    }
    //修改菜单(改)
    @Override
    public Integer updateSetup(Menu menu) {
        Integer up = menuMapper.updateSetup(menu);
        return up;
    }



}
