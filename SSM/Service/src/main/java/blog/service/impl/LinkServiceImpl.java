package blog.service.impl;


import blog.entity.Link;
import blog.mapper.LinkMapper;
import blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//自动注入到spring容器中
@Service
public class LinkServiceImpl implements LinkService {

    /**
     * 链接sql实现接口
     */
    @Autowired
    private LinkMapper linkMapper;

    /**
     * 链接查询全部数据业务接口实现
     * @return
     */
    @Override
    public List<Link> linkList() {
        List<Link> list = linkMapper.selectLinks();
        return list;
    }

    /**
     * id删除链接接口实现
     * @param id
     * @return
     */
    @Override
    public Integer removeByIdLink(Integer id) {
        Integer row = linkMapper.deleteByIdLink(id);
        return row;
    }

    /**
     * id查询链接业务接口实现
     * @param id
     * @return
     */
    @Override
    public Link linkById(Integer id) {
        Link link = linkMapper.selectByIdLink(id);
        return link;
    }

    /**
     * 添加链接接口实现
     * @return
     */
    @Override
    public Integer addLink(Link link) {
        Integer row = linkMapper.insertLink(link);
        return row;
    }

    /**
     * id修改链接接口实现
     * @param link
     * @return
     */
    @Override
    public Integer reviseByIdLink(Link link) {
        Integer row = linkMapper.updateByIdLink(link);
        return row;
    }

    /**
     * 获得链接总数
     *
     * @param status 状态
     * @return 数量
     */
    @Override
    public Integer countLink(Integer status)  {
        return linkMapper.countLink(status);
    }

    /**
     * 获得链接列表
     *
     * @param status 状态
     * @return 链接列表
     */
    @Override
    public List<Link> listLink(Integer status)  {
        return linkMapper.listLink(status);
    }
}
