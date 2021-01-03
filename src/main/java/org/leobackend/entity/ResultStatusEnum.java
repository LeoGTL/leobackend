package org.leobackend.entity;

/**
 * Created on 2021-01-02.
 * TODO description
 *
 * @author Leo
 */
public enum ResultStatusEnum {

    OK (200, "成功"),
    FAILED (201, "失败"),
    PARAMETER_ERROR (400, "参数错误"),
    AUTH_ERROR (401, "认证错误"),
    SERVER_ERROR (500, "服务异常");

    private Integer value;
    private String message;

    private ResultStatusEnum (Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
