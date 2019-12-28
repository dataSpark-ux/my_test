package com.wy.newblog.entity.enums;

/**
 * @author wy
 */
public enum IsPay {
    NO_PAY(0, "未付款"),
    YES_PAY(1, "已付款"),
    REFUNDED(2, "已退款");
    private final int code;
    private final String text;

    private IsPay(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int code() {
        return code;
    }

    public String text() {
        return text;
    }

    public static IsPay nameOf(String name) {
        try {
            return IsPay.valueOf(name);
        } catch (Exception e) {
        }

        return null;
    }

    public static IsPay codeOf(int code) {
        for (IsPay value : values()) {
            if (value.code == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
