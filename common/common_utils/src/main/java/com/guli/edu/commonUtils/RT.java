package com.guli.edu.commonUtils;


import lombok.Data;


@Data
public class RT<T>{
    private RT(){}
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    public static RT ok(){
        RT r = new RT();
        r.setSuccess(ResultCode.SUCCESS.getSuccess());
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMsg());
        return r;
    }

    public static RT error(){
        RT r = new RT();
        r.setSuccess(ResultCode.FAIL.getSuccess());
        r.setCode(ResultCode.FAIL.getCode());
        r.setMessage(ResultCode.FAIL.getMsg());
        return r;
    }

    public RT success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public RT code(Integer code) {
        this.setCode(code);
        return this;
    }

    public RT msg(String msg) {
        this.setMessage(msg);
        return this;
    }
    public RT data(T data) {
        this.setData(data);
        return this;
    }


}
