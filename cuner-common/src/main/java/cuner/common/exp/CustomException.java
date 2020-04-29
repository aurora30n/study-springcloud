package cuner.common.exp;

public class CustomException extends Exception {

    public CustomException() {
        super();
    }

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

}
