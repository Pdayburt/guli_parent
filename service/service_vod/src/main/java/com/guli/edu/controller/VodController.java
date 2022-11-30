package com.guli.edu.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.constant.ConstantVodUtils;
import com.guli.edu.exception.GuliException;
import com.guli.edu.service.VodService;
import com.guli.edu.util.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        try {
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001, "阿里云视频删除失败");
        }
        return RMap.ok();
    }

    @DeleteMapping("deleteBatch")
    public RMap deleteBatch(@RequestParam("videoList") List<String> videoList){

        vodService.removeMoreAliyunVideo(videoList);
        return RMap.ok();
    }
}
