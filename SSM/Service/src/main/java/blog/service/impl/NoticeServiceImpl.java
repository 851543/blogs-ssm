package blog.service.impl;


import blog.entity.Notice;
import blog.mapper.NoticeMapper;
import blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//自动注入到spring容器中
@Service
public class NoticeServiceImpl implements NoticeService {
    /**
     * 公告
     */
    //对类成员变量、方法及构造函数进行标注,让 spring 完成 bean 自动装配的工作
    @Autowired
    private NoticeMapper noticeMapper;
    //查询全部公告
    @Override
    public List<Notice> selectnoticeList(Integer status) {
        List<Notice> list = noticeMapper.selectNotice(status);
        return list;
    }
    //删除公告
    @Override
    public Integer delectNotice(Integer id) {
        Integer rows = noticeMapper.delectNotice(id);
        return rows;
    }
    //增加公告
    @Override
    public Integer insertNotice(Notice notice) {
       Integer ro = noticeMapper.insertNotice(notice);
        return ro;
    }
    //编辑公告(查询)
    @Override
    public Notice noticeById(Integer id) {
        Notice notice = noticeMapper.noticeById(id);
        return notice;
    }

    @Override
    public List<Notice> noticeAll() {
        List<Notice> noticeList=noticeMapper.selectAllNotice();
        return noticeList;
    }



    //编辑公告(修改)
    @Override
    public Integer updateNotice(Notice notice) {
        Integer row = noticeMapper.updateNotice(notice);
        return row;
    }
}
