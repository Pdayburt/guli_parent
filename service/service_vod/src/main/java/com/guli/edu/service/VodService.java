package com.guli.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadAliyun(MultipartFile file);

    void removeMoreAliyunVideo(List<String> videoList);

    void removeAliyunvideoById(String videoId);
}
