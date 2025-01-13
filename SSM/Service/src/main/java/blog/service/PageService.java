package blog.service;

import blog.entity.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PageService {
    /**
     * 查询全部数据的业务接口
     * @return
     */
    List<Page> pageList();

    /**
     * 添加实体数据业务接口
     * @param page
     * @return
     */
    Integer addPages(Page page);

    /**
     * 判断别名是否存在业务接口
     * @param pageKey
     * @return
     */
    String byKeyIsNull(String pageKey);
    /**
     * 查询一页页面业务接口
     * @param id
     * @return
     */
    Page pageById(Integer id);

    /**
     * id删除页面业务接口
     * @param id
     * @return
     */
    Integer removeByIdPage(Integer id);

    /**
     * id修改页面业务接口
     * @param page
     * @return
     */
    Integer reviseByIdPage(Page page);

    /**
     * 根据页面key获得页面
     *
     * @param status 状态
     * @param key 别名
     * @return 页面
     */
    Page getPageByKey(Integer status, String key);
}
