package tian.web;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tian
 * @date 2020/9/11
 */
public class TimeUtil {

    /**
     * 日期格式
     **/
    public interface DATE_PATTERN {
        String HHMMSS = "HHmmss";
        String HH_MM_SS = "HH:mm:ss";
        String HH_MM = "HH:mm";
        String YYYY = "yyyy";
        String YYYYMMDD = "yyyyMMdd";
        String YYYYMM = "yyyyMM";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    }

    /**
     * 时间戳转换成时间 YYYY_MM_DD_HH_MM_SS
     * @param timestamp
     * @return
     */
    public static String datestampToDataString(Long timestamp){
        Date d = new Date(timestamp);
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        return format.format(d);
    }

}
