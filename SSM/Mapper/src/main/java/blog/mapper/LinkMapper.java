package blog.mapper;

import blog.entity.Link;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理
@Repository
public interface LinkMapper {
    /**
     * 查询全部数据
     * @return
     */
    @Select("select * from link")
    List<Link> selectLinks();

    /**
     * id删除链接
     * @param id
     * @return
     */
    @Delete("delete from link where link_id=#{linkId}")
    Integer deleteByIdLink(Integer id);

    /**
     * id查询链接
     * @param id
     * @return
     */
    @Select("select * from link where link_id=#{linkId}")
    Link selectByIdLink(Integer id);

    /**
     * 添加链接
     * @return
     */
    @Insert("insert into link value(#{linkId},#{linkUrl},#{linkName},#{linkImage},#{linkDescription}," +
            "#{linkOwnerNickname},#{linkOwnerContact},#{linkUpdateTime},#{linkCreateTime},#{linkOrder},#{linkStatus})")
    Integer insertLink(Link link);

    /**
     * id修改链接
     * @param link
     * @return
     */
    @Update("update link set link_url=#{linkUrl},link_name=#{linkName},link_owner_contact=#{linkOwnerContact}," +
            "link_description=#{linkDescription},link_update_time=#{linkUpdateTime},link_order=#{linkOrder},link_status=#{linkStatus} where link_id = #{linkId}")
    Integer updateByIdLink(Link link);

    /**
     * 获得链接总数
     *
     * @param status 状态
     * @return 数量
     */
    @Select("select count(*) from link where link_status=#{status}")
    Integer countLink(@Param(value = "status") Integer status);

    /**
     * 获得链接列表
     *
     * @param status 状态
     * @return  列表
     */
    @Select("select * from  link where link_status=#{status}")
    List<Link> listLink(@Param(value = "status") Integer status);
}
