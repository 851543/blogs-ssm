package blog.mapper;

import blog.entity.Category;
import blog.entity.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理
@Repository
public interface CategoryMapper {
   //查询全部数据
   @Select("select * from category order by category_order desc,category_id asc")
   List<Category> selectCategoriesEssay();
   //查
    @Select("select category_name,category_description,category_id,category_pid from category")
    List<Category> selectCategories();
    //删
    @Delete("delete from category where category_id=#{categoryId}")
    Integer deleteCategory(Integer id);
    //增
    @Insert("insert into category value(#{categoryId},#{categoryPid},#{categoryName},#{categoryDescription},#{categoryOrder},#{categoryIcon})")
    Integer insertCategories(Category category);
    //查询所有的导航栏以及他的子目录
    @Select("select * from category")
    List<Category> selectAll();
    //根据id进行查询
    @Select("select * from category where category_id=#{categoryId}")
    Category categoryId(Integer id);
//全部分类的编辑(先查)

    @Select("select * from category where category_id=#{categoryId}")
    Category selCategories(Integer id);
//全部分类的编辑(修改)
    @Update("update category set category_name=#{categoryName},category_description=#{categoryDescription},category_icon=#{categoryIcon} where category_id=#{categoryId}")
    Integer updaCategories(Category category);
    /**
     * 查询分类总数
     *
     * @return 数量
     */
    @Select("select count(*) from category")
    Integer countCategory();

 /**
  * 根据分类id获得分类信息
  *
  * @param id ID
  * @return 分类
  */
 @Select("select *  from category  WHERE category.category_id=#{value}")
 Category getCategoryById(Integer id);

}
