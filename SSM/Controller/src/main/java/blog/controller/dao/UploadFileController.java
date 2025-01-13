package blog.controller.dao;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 图片上传和回显处理
 * @author liuyanzhao
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadFileController {

    /**
     * 文件保存目录，物理路径
     */
    public final String rootPath = "http://121.196.239.99:1314/imgs";

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public JsonResult uploadFile(@RequestParam("file") MultipartFile file) {

        //为上传到服务器的文件取名，使用UUID防止文件名重复
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename= UUID.randomUUID().toString()+type;

        //3.存储文件
        //将内存中的数据写入磁盘
        try {
            //使用Jersey客户端上传文件
            Client client = Client.create();
            WebResource webResource = client.resource(rootPath +"/" + URLEncoder.encode(filename,"utf-8"));
            webResource.put(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传失败，cause:{}", e);
        }
        //完整的url
        String fileUrl = rootPath + "/" + filename;

        //4.返回URL
        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setTitle(filename);
        uploadFileVO.setSrc(fileUrl);
        return new JsonResult().ok(uploadFileVO);
    }
}