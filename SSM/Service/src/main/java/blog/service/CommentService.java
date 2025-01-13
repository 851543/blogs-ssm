package blog.service;

import blog.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

public interface CommentService {
    //查询全部评论数据的业务接口
    List<Comment> commentList();
    //根据id删除评论
    Integer deleteComments(Integer id);
    //编辑评论(查出来)
    Comment selById(Integer id);
    //编辑评论(修改)
    Integer updateComments(Comment comment);
    //回复评论(先查出来)
    Comment selComments(Integer id);
    //回复评论(增加)
    Integer insertComments(Comment comment);
//    近期评论
    List<Comment> selctComment();

    /**
     * 获得最近评论
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Comment> listRecentComment(Integer userId, Integer limit);


    /**
     * 根据文章id获取评论列表
     *
     * @param articleId 文章ID
     * @return 列表
     */
    List<Comment> listCommentByArticleId(Integer articleId);

    /**
     * 获取所有评论列表
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示数量
     * @return 列表
     */
    PageInfo<Comment> listCommentByPage(
            Integer pageIndex,
            Integer pageSize,
            HashMap<String, Object> criteria);

    /**
     * 根据id获取评论
     *
     * @param id
     * @return
     */
    Comment getCommentById(Integer id);

    /**
     * 获得评论的子评论
     *
     * @param id 评论ID
     * @return 列表
     */
    List<Comment> listChildComment(Integer id);

    /**
     * 添加评论
     *
     * @param comment 评论
     */
    void insertComment(Comment comment);

    /**
     * 获得某个用户收到的评论
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示数量
     * @return 列表
     */
    PageInfo<Comment> listReceiveCommentByPage(
            Integer pageIndex,
            Integer pageSize,
            Integer userId);
}
