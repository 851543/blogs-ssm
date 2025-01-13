package blog.mapper;

import blog.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

//用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理
@Repository
public interface CommentMapper {
    //查询全部数据
    @Select("select * from comment")
    List<Comment> selectComments();
    //根据id删除评论
    @Delete("delete from comment where comment_id=#{id}")
    Integer deleteComments(@Param("id") Integer id);
    //编辑评论(查出本来写的)
    @Select("select * from comment where comment_id=#{comment_id}")
    Comment selById(Integer id);
    //编辑评论(修改)
    @Update("update comment set comment_pname=#{commentPname},comment_author_email=#{commentAuthorEmail}," +
            "comment_author_url=#{commentAuthorUrl},comment_content=#{commentContent} where comment_id=#{commentId}")
    Integer updateComments(Comment comment);
    //回复评论(查出来之前评论的)
    @Select("select * from comment where comment_id=#{comment_id}")
    Comment selComments(Integer id);
    /**
     * 添加
     *
     * @param comment 评论
     * @return 影响行数
     */
    @Insert("insert into comment value(#{commentId},#{commentPid},#{commentPname},#{commentArticleId},#{commentAuthorName}," +
            "#{commentAuthorEmail},#{commentAuthorUrl},#{commentAuthorAvatar},#{commentContent},#{commentAgent},#{commentIp},#{commentCreateTime},#{commentRole},#{commentUserId})")
    Integer insertComments(Comment comment);

//    近期评论
    @Select("select * from `comment`  ORDER BY  comment_create_time desc limit 9")
    List<Comment> Commect();

    /**
     * 获得最近评论
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Comment> listRecentComment(@Param(value = "userId") Integer userId,
                                    @Param(value = "limit") Integer limit);


    /**
     * 根据文章id获取评论列表
     *
     * @param id ID
     * @return 列表
     */
    @Select("select * from comment  WHERE comment_article_id = #{id} ORDER BY comment_id ASC")
    List<Comment> listCommentByArticleId(@Param(value = "id") Integer id);

    /**
     * 获得评论列表
     *
     * @return 列表
     */
    List<Comment> listComment(HashMap<String, Object> criteria);


    /**
     * 根据ID查询
     *
     * @param commentId 评论ID
     * @return 评论
     */
    @Select("select * from comment where comment_id=#{commentId}")
    Comment getCommentById(Integer commentId);


    /**
     * 获得评论的子评论
     *
     * @param id 评论ID
     * @return 列表
     */
    @Select("select * from comment where comment_pid=#{id}")
    List<Comment> listChildComment(@Param(value = "id") Integer id);

    /**
     * 添加
     *
     * @param comment 评论
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 获得某个用户收到的评论
     *
     * @return 列表
     */
    List<Comment> getReceiveComment(List<Integer> articleIds);

}



