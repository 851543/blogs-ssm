package blog.service;

import blog.entity.Page;
import blog.entity.Tag;

import java.util.List;

public interface TagService {
    //查询全部数据的业务接口
    List<Tag>  selectTagList();
    //增加标签
    Integer insertTagList(Tag tag);
    //删除
    Integer deleteTagList(Integer id);
    //全部标签的编辑(先查)
    Tag selTag(Integer id);
    //全部标签的编辑(修改)
    Integer updaTag(Tag tag);

    /**
     * 获得标签列表
     *
     * @return 标签列表
     */
    List<Tag> listTag() ;

    /**
     * 获得标签总数
     *
     * @return 数量
     */
    Integer countTag() ;

    /**
     * 根据id获得标签信息
     *
     * @param id 标签ID
     * @return 标签
     */
    Tag getTagById(Integer id) ;


}
