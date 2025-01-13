package blog.mapper;

import blog.entity.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理
@Repository
public interface PageMapper {

    /**
     * 查询全部数据
     * @return
     */
    @Select("select * from page")
    List<Page> selectPages();

    /***
     * insert delete update操作问题
     * 这里有一个坑 我不断的尝试才晓得啥问题
     * 首先我们在配置文件哪里开始驼峰命名模式,用select查询的时候,数据库是page_id,那么我查询出现的自动变为pageId,这里是没有问题的
     *其他的insert delete update操作呢？刚开始我的sql语句用的字段名是和数据库一样的名字,但是怎么也insert不了,我真的服了,也就是说什么也插入不了数据
     * 他显示说数据类型不对,我也加了一些注解什么的,各种操作,服了还是不行,最终我发现！！！用实体类属性的名字就可以了。。。。。。。。。
     * 原来是驼峰模式 他把传入数据库的数据变化了 pageId变为page_id 心累 所以你们注意了。。。。。。。。。
     * @param page
     * @return
     */

    /**
     * 添加实体数据
     * @param page
     * @return
     */
    @Insert("insert into page value(#{pageId},#{pageKey},#{pageTitle},#{pageContent}," +
            "#{pageCreateTime},#{pageUpdateTime},#{pageViewCount},#{pageCommentCount},#{pageStatus})")
    Integer insertPages(Page page);

    /**
     * 判断别名是否存在
     * @param pageKey
     * @return
     */
    @Select("select page_key from page where page_key = #{pageKey}")
    String selectByKeyIsNull(String pageKey);

    /**
     * 查询一页页面
     * @param id
     * @return
     */
    @Select("select * from page where page_id = #{pageId}")
    Page selectByIdPage(Integer id);

    /**
     * id删除页面
     * @param id
     * @return
     */
    @Delete("delete from page where page_id = #{pageId}")
    Integer deleteByIdPage(Integer id);

    /**
     * id修改页面
     * @param page
     * @return
     */
    @Update("update page set page_key=#{pageKey},page_title=#{pageTitle}," +
            "page_content=#{pageContent},page_update_time=#{pageUpdateTime},page_status=#{pageStatus} where page_id=#{pageId} ")
    Integer updateByIdPage(Page page);

    /**
     * 根据key获得页面
     *
     * @param status 状态
     * @param key 别名
     * @return 页面
     */
    Page getPageByKey(@Param(value = "status") Integer status,@Param(value = "key") String key);
}
