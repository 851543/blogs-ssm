package blog.mapper;

import blog.entity.Options;
import blog.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface OptionsMapper {
    //保存设置(修改,(修改得先把需要修改的内容查出来))
//    @Select("select option_site_title,option_site_descrption,option_meta_descrption,option_meta_keyword from options")
    @Select("select * from options")
    Options selOptions();

    //保存设置(开始修改)
    @Update("update options set option_site_title=#{optionSiteTitle},option_site_descrption=#{optionSiteDescrption}," +
            "option_meta_descrption=#{optionMetaDescrption},option_meta_keyword=#{optionMetaKeyword},option_aboutsite_avatar=#{optionAboutsiteAvatar}," +
            "option_aboutsite_title=#{optionAboutsiteTitle},option_aboutsite_content=#{optionAboutsiteContent}," +
            "option_aboutsite_wechat=#{optionAboutsiteWechat},option_aboutsite_qq=#{optionAboutsiteQq}," +
            "option_aboutsite_weibo=#{optionAboutsiteWeibo},option_aboutsite_github=#{optionAboutsiteGithub} ")
    Integer updateOptions(Options options);

    //用户基本资料(右上角登录)
    @Select("select * from user where user_id=#{userId}")
    User selectzhu(Integer id);

    //编辑基本资料(先查)
    //    查出来直接调用user那边写的编辑页面(之前写的一整套可以直接用)
    @Select("select * from user where user_id=#{userId}")
    User selzhu(Integer id);
}
