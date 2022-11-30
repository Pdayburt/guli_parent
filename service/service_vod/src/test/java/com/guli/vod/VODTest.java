package com.guli.vod;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.guli.edu.util.InitVodClient;

import java.util.List;

public class VODTest {
    public static void main(String[] args){

        String accessKeyId = "LTAI5tBVBomyrq98HjFDuxnm";
        String accessKeySecret = "8ZrfvjQrl9mmDcDH8H8XdbIkXVNkHE";
        String title = "Believe In God";//上传后显示的文件名
        String fileName = "/Users/anatkh/Downloads/The Bible/Believe In God.mp4";//本地文件路径和名称

        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，（注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }


    }


    //根据视频id获取视频播放凭证
    public static void getPlayAuth() throws ClientException {

        DefaultAcsClient vodClient = InitVodClient.initVodClient("LTAI5tBVBomyrq98HjFDuxnm", "8ZrfvjQrl9mmDcDH8H8XdbIkXVNkHE");
        GetVideoPlayAuthResponse response ;
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("7cec1c731a6f41f0b05e269fd948756f");
        response = vodClient.getAcsResponse(request);
        System.out.println("playauth: "+response.getPlayAuth());
    }

    //根据视频id获取视频的播放地址
    public static void getPlayUrl() throws ClientException {
        DefaultAcsClient vodClient = InitVodClient.initVodClient("LTAI5tBVBomyrq98HjFDuxnm", "8ZrfvjQrl9mmDcDH8H8XdbIkXVNkHE");
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response ;
        request.setVideoId("7cec1c731a6f41f0b05e269fd948756f");
        response = vodClient.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
    }
}
