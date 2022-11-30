package com.guli.edu.controller;

import com.guli.edu.client.VodClient;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.EduVideo;
import com.guli.edu.exception.GuliException;
import com.guli.edu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;
    //添加小结
    @PostMapping("addVideo")
    public RMap addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return RMap.ok();
    }

    //删除小结

    @DeleteMapping("deleteVideo/{id}")
    public RMap deleteVideo(@PathVariable String id) {
        EduVideo videoById = eduVideoService.getById(id);
        String videoSourceId = videoById.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            RMap rMap = vodClient.deleteVideo(videoSourceId);
            if (rMap.getCode() == 20001) {
                throw new GuliException(20001, "删除视频失败，熔断器已作出响应");
            }
        }
        eduVideoService.removeById(id);
        return RMap.ok();
    }

    //修改小结
    @PostMapping("updateVideo")
    public RMap updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return RMap.ok();
    }

    @GetMapping("getVideo/{id}")
    public RMap getVideo(@PathVariable String id){
        EduVideo video = eduVideoService.getById(id);
        return RMap.ok().data("video",video);
    }

}
