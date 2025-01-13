package blog.service;

import blog.entity.Notice;

import java.util.List;

public interface NoticeService {
    //查询全部数据的业务接口
    List<Notice> selectnoticeList(Integer status);
    //删除公告
    Integer delectNotice(Integer id);
    //增加公告
    Integer insertNotice(Notice notice);
    //编辑公告(修改)
    Integer updateNotice(Notice notice);
    //编辑公告的查询
    Notice noticeById(Integer id);
    List<Notice> noticeAll();


}
