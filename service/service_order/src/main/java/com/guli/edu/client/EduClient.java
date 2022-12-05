package com.guli.edu.client;

import com.guli.edu.commonUtils.orderVo.CourseWebVoOrder;
import com.guli.edu.commonUtils.orderVo.UCenterMemberForOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "service-edu")
public interface EduClient {
    @PostMapping("eduservice/courseFront/getCourseInfoForOrder/{id}")
    public CourseWebVoOrder getCourseInfoForOrder(@PathVariable String id);
}
