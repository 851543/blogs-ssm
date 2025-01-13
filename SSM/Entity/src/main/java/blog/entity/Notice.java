package blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
//需要导入lombok包
//自动生成get、set方法和toString方法
@Data
//生成全部参数的构造器
@AllArgsConstructor
//生成无参数的构造器
@NoArgsConstructor
public class Notice {
    private Integer noticeId;	//  站点id
    private String noticeTitle;	//  标题
    private String noticeContent;	//  站点内容
    //通过注解进行转化
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date noticeCreateTime;	//  创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date noticeUpdateTime;	//  修改时间
    private Integer noticeStatus;	//  站点状态
    private Integer noticeOrder;	//  排序

}
