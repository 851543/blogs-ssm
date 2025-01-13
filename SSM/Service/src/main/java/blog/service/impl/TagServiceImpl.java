package blog.service.impl;


import blog.entity.Page;
import blog.entity.Tag;
import blog.mapper.TagMapper;
import blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//自动注入到spring容器中
@Service
@Slf4j
public class TagServiceImpl implements TagService {

    //对类成员变量、方法及构造函数进行标注,让 spring 完成 bean 自动装配的工作
    @Autowired
    private TagMapper tagMapper;

    //查询所有标签
    @Override
    public List<Tag> selectTagList() {
        List<Tag> list = tagMapper.selectTags();
        return list;
    }
    //增加标签
    @Override
    public Integer insertTagList(Tag tag) {
        Integer rows = tagMapper.addTagList(tag);
        return rows;
    }
    //删除标签
    @Override
    public Integer deleteTagList(Integer id) {
        Integer row = tagMapper.deleteTagList(id);
        return row;
    }
//全部标签的编辑(先查)
    @Override
    public Tag selTag(Integer id) {
        Tag tag = tagMapper.selTag(id);
        return tag;
    }
//全部标签的编辑(编辑)
    @Override
    public Integer updaTag(Tag tag) {
        Integer up = tagMapper.updaTag(tag);
        return up;
    }

    /**
     * 获得标签列表实现
     *
     * @return 标签列表
     */
    @Override
    public List<Tag> listTag() {
        List<Tag> list = tagMapper.selectTags();
        return list;
    }

    /**
     * 获得标签总数
     *
     * @return 数量
     */
    @Override
    public Integer countTag() {
        return tagMapper.countTag();
    }

    @Override
    public Tag getTagById(Integer id) {
        Tag tag = null;
        tag = tagMapper.getTagById(id);
        return tag;
    }

}
