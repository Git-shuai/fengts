package tian.web;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    public static Map<String, Object> mapStringToList(Map<String, Object> source,String... targetName) {
        if (StringUtils.isEmpty(source)){
            return source;
        }
        for (String s : targetName) {
            String target= StringUtils.getString(source.get(s));
            if (isEmpty(target)){
                return source;
            }
            String[] targets = target.split(",");
            List<String> targetList = Arrays.asList(targets);
            source.put(s,targetList);
        }
        return source;
    }

}
