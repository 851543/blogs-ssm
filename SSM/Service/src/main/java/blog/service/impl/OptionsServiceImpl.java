package blog.service.impl;

import blog.entity.Options;
import blog.entity.User;
import blog.mapper.OptionsMapper;
import blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OptionsServiceImpl implements OptionsService {
    //注入接口
   @Autowired
   private OptionsMapper optionsMapper;
    //保存设置(修改,(修改得先把需要修改的内容查出来))
    @Override
    public Options selOptions() {
       Options options = optionsMapper.selOptions();
        return options;
}
    //保存设置(开始修改)
    @Override
    public Integer updateOptions(Options options) {
        Integer up = optionsMapper.updateOptions(options);
        return up;
    }
    //用户基本资料(根据id)
    @Override
    public User selectzhu(Integer id) {
     User user = optionsMapper.selectzhu(id);
        return user;
    }
    //编辑用户基本资料(查)(右上角登录)
//     查出来直接调用user那边写的编辑页面(之前写的一整套可以直接用)
    @Override
    public User selzhu(Integer id) {
        User user = optionsMapper.selzhu(id);
        return user;
    }
}
