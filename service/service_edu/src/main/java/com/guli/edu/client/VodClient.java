package com.guli.edu.client;

import com.guli.edu.commonUtils.RMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "service-vod",fallback = VodDegradeFeignClient.class)
public interface VodClient {

    @DeleteMapping("/eduvod/video/deleteVideo/{videoId}")
    public RMap deleteVideo(@PathVariable String videoId);

    @DeleteMapping("/eduvod/video/deleteBatch")
    public RMap deleteBatch(@RequestParam("videoList") List<String> videoList);

}
