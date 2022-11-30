package com.guli.edu.commonUtils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RMap {

    private Boolean success;
    private Integer code;
    private String msg;
    private Map<String ,Object> data = new HashMap<>();
    private RMap(){}

    public static RMap ok(){
        RMap rMap = new RMap();
        rMap.setSuccess(ResultCode.SUCCESS.getSuccess());
        rMap.setCode(ResultCode.SUCCESS.getCode());
        rMap.setMsg(ResultCode.SUCCESS.getMsg());
        return rMap;
    }

    public static RMap error(){
        RMap rMap = new RMap();
        rMap.setSuccess(ResultCode.FAIL.getSuccess());
        rMap.setCode(ResultCode.FAIL.getCode());
        rMap.setMsg(ResultCode.FAIL.getMsg());
        return rMap;
    }

    public RMap success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public RMap code(Integer code) {
        this.setCode(code);
        return this;
    }

    public RMap msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public RMap data(String key,Object value) {
        this.data.put(key, value);
        return this;
    }
    public RMap data(Map<String,Object> map) {
        this.setData(map);
        return this;
    }
}
