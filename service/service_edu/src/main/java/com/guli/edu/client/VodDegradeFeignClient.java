package com.guli.edu.client;

import com.guli.edu.commonUtils.RMap;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class VodDegradeFeignClient implements VodClient{
    @Override
    public RMap deleteVideo(String videoId) {
        return RMap.error().msg("hystrix已启用,deleteVideo调用错误已被捕获");
    }

    @Override
    public RMap deleteBatch(List<String> videoList) {
        return RMap.error().msg("hystrix已启用,deleteBatch调用错误已被捕获");
    }
}
