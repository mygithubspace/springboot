package peng.cheng.springboot.common;

import lombok.Data;

/**
 * @author by chengpeng
 * @description
 * @time 2019/9/11 19:27
 */
@Data
public class Response {
    /**
     * 请求是否处理成功
     */
    private boolean isok;
    /**
     * 请求响应状态码（200、400、500）
     */
    private int code;
    /**
     * 请求结果描述信息
     */
    private String message;
    /**
     * 请求结果数据
     */
    private Object data;

    private Response() {}

    /**
     * @return 返回无结果对象数据
     */
    public static Response success() {
        Response resultBean = new Response();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    /**
     * @param data 结果对象
     * @return 返回有结果对象数据
     */
    public static Response success(Object data) {
        Response resultBean = new Response();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }
}
