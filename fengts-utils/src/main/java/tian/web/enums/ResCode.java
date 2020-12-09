package tian.web.enums;

/**
 * @author tian
 * @date 2020/9/11
 */
public enum  ResCode {

    /**
     * 返回结果
     */
    ERROR_CODE(-999,"操作失败"),
    SUCCESS_CODE(0,"操作成功");


    private Integer code;
    private String message;

    ResCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
