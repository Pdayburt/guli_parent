package com.guli.oss.service.imp;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.guli.oss.service.OssService;
import com.guli.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImp implements OssService {

    @Override
    public String uploadAvatarFile(MultipartFile file) {


            // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
            String endpoint = ConstantPropertiesUtils.END_POINT;
            // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
            String accessKeyId = ConstantPropertiesUtils.KEY_ID;
            String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
            // 填写Bucket名称，例如examplebucket。
            String bucketName = ConstantPropertiesUtils.BUCK_NAME;
             String url = null;

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            try {
                InputStream inputStream = file.getInputStream();
                String originalFilename = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString().replace("-", "");
                originalFilename=uuid+originalFilename;

                //     2022/11/11
                String datePath = new DateTime().toString("yyyy/MM/dd");
                originalFilename = datePath +"/"+ originalFilename;


                // 创建PutObject请求。
                ossClient.putObject(bucketName, originalFilename, inputStream);
                url= "https://"+bucketName+"."+endpoint+"/"+originalFilename;
            } catch (OSSException oe) {
                System.out.println("Caught an OSSException, which means your request made it to OSS, "
                        + "but was rejected with an error response for some reason.");
                System.out.println("Error Message:" + oe.getErrorMessage());
                System.out.println("Error Code:" + oe.getErrorCode());
                System.out.println("Request ID:" + oe.getRequestId());
                System.out.println("Host ID:" + oe.getHostId());
            } catch (ClientException ce) {
                System.out.println("Caught an ClientException, which means the client encountered "
                        + "a serious internal problem while trying to communicate with OSS, "
                        + "such as not being able to access the network.");
                System.out.println("Error Message:" + ce.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (ossClient != null) {
                    ossClient.shutdown();
                }
                return url;
            }

    }
}
