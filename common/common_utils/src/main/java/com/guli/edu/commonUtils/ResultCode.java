package com.guli.edu.commonUtils;

import lombok.Data;


public enum ResultCode {
    SUCCESS(true,20000,"SUCCESS"),
    FAIL(false,20001,"FAIL");

    private Boolean success;
    private Integer code;
    private String msg;
     ResultCode(Boolean success,Integer code,String msg){
         this.success=success;
        this.code=code;
        this.msg=msg;
    }
    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

}

