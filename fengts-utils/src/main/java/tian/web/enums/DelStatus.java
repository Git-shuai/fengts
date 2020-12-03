package tian.web.enums;

/**
 * @author tian
 * @date 2020/9/11
 */
public enum DelStatus {
    DEL_1(1),
    DEL_0(0);
    private int del;

    DelStatus(int del) {
        this.del = del;
    }

    public int getDel() {
        return del;
    }
}
