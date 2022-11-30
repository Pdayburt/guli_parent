package com.guli.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadAvatarFile(MultipartFile file);
}
