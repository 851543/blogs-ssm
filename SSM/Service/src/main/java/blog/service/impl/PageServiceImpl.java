package blog.service.impl;


import blog.entity.Page;
import blog.mapper.PageMapper;
import blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//自动注入到spring容器中
@Service
public class PageServiceImpl implements PageService {

    /**
     * 注入mapper页面接口
     */
    @Autowired
    private PageMapper pageMapper;

    /**
     * 查询全部数据的业务接口实现
     * @return
     */
    @Override
    public List<Page> pageList() {
        List<Page> list = pageMapper.selectPages();
        return list;
    }

    /**
     * 添加实体数据业务接口实现
     * @param page
     * @return
     */
    @Override
    public Integer addPages(Page page) {
        Integer rows = pageMapper.insertPages(page);
        return rows;
    }

    /**
     * 判断别名是否存在业务接口实现
     * @param pageKey
     * @return
     */
    @Override
    public String byKeyIsNull(String pageKey) {
        String key = pageMapper.selectByKeyIsNull(pageKey);
        return key;
    }

    /**
     * 查询一页页面业务接口实现
     * @param id
     * @return
     */
    @Override
    public Page pageById(Integer id) {
        Page page = pageMapper.selectByIdPage(id);
        return page;
    }

    /**
     * id删除页面业务接口实现
     * @param id
     * @return
     */
    @Override
    public Integer removeByIdPage(Integer id) {
        Integer row = pageMapper.deleteByIdPage(id);
        return row;
    }

    /**
     * id修改页面业务接口实现
     * @param page
     * @return
     */
    @Override
    public Integer reviseByIdPage(Page page) {
        Integer row = pageMapper.updateByIdPage(page);
        return row;
    }
    @Override
    public Page getPageByKey(Integer status, String key) {
        return pageMapper.getPageByKey(status, key);
    }
}
