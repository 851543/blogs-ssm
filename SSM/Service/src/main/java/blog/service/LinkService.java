package blog.service;

import blog.entity.Link;

import java.util.List;

public interface LinkService {
    /**
     * 查询全部数据的业务接口
     * @return
     */
    List<Link> linkList();

    /**
     * id删除链接业务接口
     * @param id
     * @return
     */
    Integer removeByIdLink(Integer id);

    /**
     * id查询链接业务接口
     * @param id
     * @return
     */
    Link linkById(Integer id);

    /**
     * 添加链接业务接口
     * @return
     */
    Integer addLink(Link link);

    /**
     * id修改链接接口
     * @param link
     * @return
     */
    Integer reviseByIdLink(Link link);

    /**
     * 获得链接总数
     *
     * @param status 状态
     * @return 数量
     */
    Integer countLink(Integer status);

    /**
     * 获得链接列表
     *
     * @param status 状态
     * @return 链接列表
     */
    List<Link> listLink(Integer status);
}
