package blog.mapper;

import blog.entity.ArticleTagRef;
import blog.entity.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理
@Repository
public interface ArticleTagRefMapper {
    //查询全部数据
    @Select("select * from articleTagRef")
    List<ArticleTagRef> selectArticleTagRefs();

    /**
     * 添加文章和标签关联记录
     * @param record 关联对象
     * @return 影响行数
     */
    @Insert("insert into article_tag_ref values(#{articleId},#{tagId})")
    Integer insertArticleTagRef(ArticleTagRef record);

    @Delete("delete from article_tag_ref where article_id = #{id}")
    Integer deleteByArticleId(@Param("id") Integer id);

    /**
     * 根据文章获得标签列表
     *
     * @param articleId 文章ID
     * @return 标签列表
     */
    @Select("select tag.* from tag,article_tag_ref where article_tag_ref.article_id = #{articleId} and article_tag_ref.tag_id = tag.tag_id")
    List<Tag> listTagByArticleId(Integer articleId);
}
