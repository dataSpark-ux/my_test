package com.wy.newblog.entity.enums;

public enum ResultCode {
    // Use standard HTTP status code
    OK("200", "OK"),
    CREATED("201", "Created"),

    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Unauthorized"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Not Found"),
    CONFLICT("409", "Conflict"),
    INTERNAL_SERVER_ERROR("500", " Internal Server Error"),
    ADD_FAILURE("410","新增失败"),
    UPDATE_FAILURE("410","修改失败"),

    ;

    private final String code;
    private final String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
