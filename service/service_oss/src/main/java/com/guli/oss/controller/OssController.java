package com.guli.oss.controller;

import com.guli.oss.service.OssService;
import com.guli.edu.commonUtils.RMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping()
    public RMap uploadFile(MultipartFile file){

        String url = ossService.uploadAvatarFile(file);

        return RMap.ok().data("url",url);
    }

}
