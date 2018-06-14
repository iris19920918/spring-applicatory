package util;

/**
 * Created by WANGDD on 2017-08-23.
 * 参数相关工具类
 */
public class ParamsUtil {

    /**
     * 获取sql时间范围参数
     * n<0:获取n天前日期
     * n>0:获取n天后日期
     * n=0:获取当前日期
     *
     * @return
     */
    public static String getStartParams(int n) {
        return getDateStr(n) + " 00:00:00"; //开始日期 yyyy-MM-dd HH:mm:ss
    }

    /**
     * 获取sql时间范围参数
     * n<0:获取n天前日期
     * n>0:获取n天后日期
     * n=0:获取当前日期
     *
     * @return
     */
    public static String getEndParams(int n) {
        return getDateStr(n) + " 23:59:59"; //结束日期 yyyy-MM-dd HH:mm:ss
    }

    /**
     * n<0:获取n天前日期
     * n>0:获取n天后日期
     * n=0:获取当前日期
     *
     * @param n
     * @return (yyyy-MM-dd)
     */
    public static String getDateStr(int n) {
        return DateUtils.format(DateUtils.getBeforOrFutureDate(n), "yyyy-MM-dd");
    }

}
