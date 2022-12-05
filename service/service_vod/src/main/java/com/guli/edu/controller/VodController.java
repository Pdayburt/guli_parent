package com.guli.edu.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.constant.ConstantVodUtils;
import com.guli.edu.exception.GuliException;
import com.guli.edu.service.VodService;
import com.guli.edu.util.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Path;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadAliyun")
    public RMap uploadAliyun(MultipartFile file){
        String videoId = vodService.uploadAliyun(file);
        return RMap.ok().data("videoId",videoId);
    }

    @DeleteMapping("deleteVideo/{videoId}")
    public RMap deleteVideo(@PathVariable String videoId){
        vodService.removeAliyunvideoById(videoId);
        return RMap.ok();
    }

    @DeleteMapping("deleteBatch")
    public RMap deleteBatch(@RequestParam("videoList") List<String> videoList){
        vodService.removeMoreAliyunVideo(videoList);
        return RMap.ok();
    }

    @GetMapping("getPlayAuth/{videoId}")
    public RMap getPlayAuth(@PathVariable String videoId){
        DefaultAcsClient vodClient = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
        GetVideoPlayAuthResponse response ;
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        try {
            response = vodClient.getAcsResponse(request);
        } catch (ClientException e) {
            throw new GuliException(20001,"获取阿里云播放凭证失败!!!");
        }
        String playAuth = response.getPlayAuth();
        return RMap.ok().data("playAuth",playAuth);
    }

}
