package blog.service.impl;


import blog.entity.Category;
import blog.mapper.CategoryMapper;
import blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//铺获异常用
@Slf4j
//自动注入到spring容器中
@Service
public class CategoryServiceImpl implements CategoryService {

    //对类成员变量、方法及构造函数进行标注,让 spring 完成 bean 自动装配的工作
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 获得分类列表实现
     * @return
     */
    @Override
    public List<Category> listCategory() {
        List<Category> list = categoryMapper.selectCategoriesEssay();
        return list;
    }

    //增加分类
    @Override
    public Integer insertCategory(Category category) {
       Integer row =  categoryMapper.insertCategories(category);
        return row;
    }
//删除分类
    @Override
    public Integer delectCategory(Integer id) {
        Integer rows = categoryMapper.deleteCategory(id);
        return rows;
    }
//编辑分类(查询)
    @Override
    public Category selCategories(Integer id) {
        Category category = categoryMapper.selCategories(id);
        return category;
    }


    //编辑分类(修改)
    @Override
    public Integer updaCategories(Category category) {
        Integer up = categoryMapper.updaCategories(category);
        return up;
    }


    //查询导航烂以及 他的子目录这边实现的是CategoryService这里的方法，调用的是CategoryMapper的注解的这个查询的方法
    //查询导航烂以及 他的子目录这边实现的是CategoryService这里的方法，调用的是CategoryMapper的注解的这个查询的方法
    @Override
    public List<Category> selectAlls() {
        List<Category> categoryList=categoryMapper.selectAll();
        return categoryList;
    }
    //跳转到java
    @Override
    public Category selectCategoryId(Integer id) {
        Category categoryList=categoryMapper.categoryId(id);
        return categoryList;
    }

    /**
     * 获得分类总数
     *
     * @return
     */
    @Override
    public Integer countCategory() {
        Integer count = 0;
        count = categoryMapper.countCategory();
        return count;
    }

    @Override
    public Category getCategoryById(Integer id) {
        Category category = null;
        try {
            category = categoryMapper.getCategoryById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据分类ID获得分类, id:{}, cause:{}", id, e);
        }
        return category;
    }



}
