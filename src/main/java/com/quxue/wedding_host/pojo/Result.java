package com.quxue.wedding_host.pojo;

/**
 * 返回给前端的通用对象
 */
public class Result {
    private Integer code;
    //逻辑状态码（开发者自定义）不是http响应状态码，
    // 一般200认为成功，500任务逻辑有问题

    private String msg;//message
    //返回给前端的消息，一般都是逻辑错误以后给前端返回提示信息

    private Object data;
    //返回给前端的数据对象，比如单行查询，多行查询

    /**
     * 下面是常用快捷方法
     */


    public static Result success() {
        Result result = new Result();
        result.code = 200;
        result.msg = "success";
        return result;
    }

    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.code = 200;
        result.data=data;
        result.msg = msg;
        return result;
    }

    public static Result success(String msg) {
        Result result = new Result();
        result.code = 200;
        result.msg = msg;
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.code = 200;
        result.data = data;
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.code = 500;
        result.msg = "error";
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.code = 500;
        result.msg = msg;
        return result;
    }

    public static Result error(Integer customCode, String msg) {
        Result result = new Result();
        result.code = customCode;
        result.msg = msg;
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
