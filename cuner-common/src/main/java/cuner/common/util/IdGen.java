package cuner.common.util;

public class IdGen {

    public static long nextId() {
        return SnowFlake.nextId();
    }
}