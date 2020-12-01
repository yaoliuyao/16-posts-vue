package com.nfit.yaoliusan.myblog.vo;


import com.google.gson.Gson;

public class ResultVO {
    private int code = 1;
    private String message;
    private Object data;

    private ResultVO() {}

    public String toJSON () {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static ResultVO ok(Object data) {
        ResultVO vo = new ResultVO();
        vo.setData(data);
        return vo;
    }

    public static ResultVO err(int code, String message) {
        ResultVO vo = new ResultVO();
        vo.setCode(code);
        vo.setMessage(message);
        return vo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
