package tian.web;

import org.springframework.lang.Nullable;

/**
 * @author FTS
 * @date 2021/1/4
 */
public class StringUtils {

    /**
     * 空也会返回“”
     *
     * @param o
     * @return String
     */
    public static String getString(Object o) {
        if (o == null || o == Constant.ISNULL) {
            return "";
        }
        return o.toString();
    }

    public static boolean isEmpty(@Nullable Object str) {
        return (str == null || "".equals(str)||"null".equals(str));
    }

}
