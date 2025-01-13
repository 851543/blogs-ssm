package blog.service.impl;


import blog.entity.Article;
import blog.entity.Comment;
import blog.mapper.ArticleMapper;
import blog.mapper.CommentMapper;
import blog.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//自动注入到spring容器中
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    //对类成员变量、方法及构造函数进行标注,让 spring 完成 bean 自动装配的工作
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 文章接口
     * @return
     */
    @Autowired
    private ArticleMapper articleMapper;

    //查询全部评论
    @Override
    public List<Comment> commentList() {
        List<Comment> list = commentMapper.selectComments();
        return list;
    }

//删除评论
    @Override
    public Integer deleteComments(Integer id) {
        Integer rows = commentMapper.deleteComments(id);
        return rows;
    }
//编辑评论(查)
    @Override
    public Comment selById(Integer id) {
    Comment comment = commentMapper.selById(id);
        return comment;
    }
//编辑评论(修改)
    @Override
    public Integer updateComments(Comment comment) {
        Integer row = commentMapper.updateComments(comment);
        return row;
    }
//回复评论(查)
    @Override
    public Comment selComments(Integer id) {
        Comment com = commentMapper.selComments(id);
        return com;
    }
//回复评论(增加)
    @Override
    public Integer insertComments(Comment comment) {
        Integer ro = commentMapper.insertComments(comment);
        return ro;
    }

    @Override
    public List<Comment> selctComment() {
        List<Comment> commentList=commentMapper.Commect();
        return commentList;
    }

    /**
     * 获得最近评论
     *
     * @param limit 查询数量
     * @return 列表
     */
    @Override
    public List<Comment> listRecentComment(Integer userId, Integer limit) {
        List<Comment> commentList = null;
        commentList = commentMapper.listRecentComment(userId, limit);
            for (int i = 0; i < commentList.size(); i++) {
                //此处为1为分布 0为草稿状态
                Article article = articleMapper.getArticleByStatusAndId(1, commentList.get(i).getCommentArticleId());
                commentList.get(i).setArticle(article);
            }
        return commentList;
    }

    @Override
    public List<Comment> listCommentByArticleId(Integer articleId) {
        List<Comment> commentList = null;
        commentList = commentMapper.listCommentByArticleId(articleId);
        return commentList;
    }

    @Override
    public PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Comment> commentList = null;
        commentList = commentMapper.listComment(criteria);
            for (int i = 0; i < commentList.size(); i++) {
                Article article = articleMapper.getArticleByStatusAndId(1, commentList.get(i).getCommentArticleId());
                commentList.get(i).setArticle(article);
            }
        return new PageInfo<>(commentList);
    }

    @Override
    public Comment getCommentById(Integer id) {
        Comment comment = null;
        comment = commentMapper.getCommentById(id);
        return comment;
    }

    @Override
    public List<Comment> listChildComment(Integer id) {
        List<Comment> childCommentList = null;
        childCommentList = commentMapper.listChildComment(id);
        return childCommentList;
    }

    @Override
    public void insertComment(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public PageInfo<Comment> listReceiveCommentByPage(Integer pageIndex, Integer pageSize, Integer userId) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Comment> commentList = new ArrayList<>();
            List<Integer> articleIds = articleMapper.listArticleIdsByUserId(userId);
            if (articleIds != null && articleIds.size() > 0) {
                commentList = commentMapper.getReceiveComment(articleIds);
                for (int i = 0; i < commentList.size(); i++) {
                    Article article = articleMapper.getArticleByStatusAndId(1, commentList.get(i).getCommentArticleId());
                    commentList.get(i).setArticle(article);
                }
            }
        return new PageInfo<>(commentList);
    }
}
