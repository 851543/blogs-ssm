package blog.service;

import blog.entity.Options;
import blog.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OptionsService {
    //保存设置(修改,(修改得先把需要修改的内容查出来))
    Options selOptions();
    //保存设置(修改)
    Integer updateOptions(Options options);
    //用户基本资料(右上角登录)
    User selectzhu(Integer id);
    //编辑基本资料(先查)
//    查出来直接调用user那边写的编辑页面(之前写的一整套可以直接用)
    User selzhu(Integer id);
}
