package blog.mapper;

import blog.entity.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理
@Repository
public interface TagMapper {
    //查询所有全部数据
    @Select("select * from tag")
    List<Tag> selectTags();
    //增加
    @Insert("insert into tag value(#{tagId},#{tagName},#{tagDescription})")
    Integer addTagList(Tag tag);
    //删除
    @Delete("delete from tag where tag_id=#{tagId}")
    Integer deleteTagList(Integer id);
    //全部标签的编辑(先查)
    @Select("select * from tag where tag_id = #{tagId}")
    Tag selTag(Integer id);
    //全部标签的编辑(修改)
    @Update("update tag set tag_name=#{tagName},tag_description=#{tagDescription} where tag_id=#{tagId}")
    Integer updaTag(Tag tag);

    /**
     * 获得标签总数
     *
     * @return 数量
     */
    @Select("select count(*) from tag")
    Integer countTag() ;

    /**
     * 根据ID查询
     *
     * @param tagId 标签ID
     * @return 标签
     */
    @Select("select * from tag where tag_id=#{tagId}")
    Tag getTagById(Integer tagId);


}