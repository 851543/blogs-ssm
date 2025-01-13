package blog.mapper;

import blog.entity.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理
@Repository
public interface NoticeMapper {
    /**
     * 获得公告列表
     *
     * @param status 状态
     * @return 列表
     */
    @Select("select * from notice where notice_status=#{status}")
    List<Notice> selectNotice(Integer status);
    //根据id删除公告
    @Delete("delete from notice where notice_id=#{noticeId}")
    Integer delectNotice(Integer id);
    //增加公告
    @Insert("insert into notice value(#{noticeId},#{noticeTitle},#{noticeContent},#{noticeCreateTime},#{noticeUpdateTime},#{noticeStatus},#{noticeOrder})")
    Integer insertNotice(Notice notice);
    //编辑公告(就是修改)
    @Update("update notice set notice_title=#{noticeTitle},notice_content=#{noticeContent},notice_create_time=#{noticeCreateTime}," +
            "notice_update_time=#{noticeUpdateTime},notice_status=#{noticeStatus},notice_order=#{noticeOrder} where notice_id=#{noticeId}")
    Integer updateNotice(Notice notice);
    //编辑公告查询
    @Select("select * from notice where notice_id=#{noticeId}")
    Notice noticeById(Integer id);

//    查询全部
    @Select("select * from notice")
    List<Notice> selectAllNotice();

}
