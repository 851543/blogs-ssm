package blog.service;

import blog.entity.Category;
import blog.entity.Tag;

import java.util.List;

public interface CategoryService {
    /**
     * 获得分类列表
     *
     * @return 分类列表
     */
    List<Category> listCategory();
    //添加分类
    Integer insertCategory(Category category);
    //根据id删除
    Integer delectCategory(Integer id);
    //查询显示所有的导航烂
    //这边的方法被测试类那边去调用
    List<Category> selectAlls();

    Category selectCategoryId(Integer id);
    //全部分类的编辑(先查)
    Category selCategories(Integer id);

    //全部分类的编辑(修改)
    Integer updaCategories(Category category);


    /**
     * 获得分类总数
     *
     * @return
     */
    Integer countCategory();

    /**
     * 根据id查询分类信息
     *
     * @param id     ID
     * @return 分类
     */
    Category getCategoryById(Integer id);
}
