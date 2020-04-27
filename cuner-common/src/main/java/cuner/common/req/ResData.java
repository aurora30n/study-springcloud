package cuner.common.req;

public class ResData<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResData() {
    }

    public ResData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static<T> ResData<T> success() {
        return new ResData<T>(ResCode.success.getCode(), ResCode.success.getName(), null);
    }

    public static<T> ResData<T> success(String msg) {
        return new ResData<T>(ResCode.success.getCode(), msg, null);
    }

    public static<T> ResData<T> success(T data) {
        return new ResData<T>(ResCode.success.getCode(),  ResCode.success.getName(), data);
    }

    public static<T> ResData<T> success(String msg, T data) {
        return new ResData<T>(ResCode.success.getCode(), msg, data);
    }

    public static<T> ResData<T> success(Integer status, String msg, T data) {
        return new ResData<T>(status, msg, data);
    }

    public static<T> ResData<T> error() {
        return new ResData<T>(ResCode.fail.getCode(), ResCode.fail.getName(), null);
    }

    public static<T> ResData<T> error(String msg) {
        return new ResData<T>(ResCode.fail.getCode(), msg, null);
    }

    public static<T> ResData<T> error(Integer status, String msg) {
        return new ResData<T>(status, msg, null);
    }

    public static<T> ResData<T> error(ResCode resCode) {
        return new ResData<T>(resCode.getCode(), resCode.getName(), null);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
