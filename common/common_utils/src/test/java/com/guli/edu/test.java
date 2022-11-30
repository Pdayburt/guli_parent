package com.guli.edu;

import com.guli.edu.commonUtils.ResultCode;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {

    @Test
    public void testResponseCode(){
        System.out.println(ResultCode.FAIL);
        System.out.println(ResultCode.FAIL.getCode());
        System.out.println(ResultCode.FAIL.getMsg());
        System.out.println(ResultCode.FAIL.getSuccess());
    }
}
