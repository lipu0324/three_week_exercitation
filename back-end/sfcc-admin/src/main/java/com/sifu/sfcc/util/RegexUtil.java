package com.sifu.sfcc.util;

public class RegexUtil {

    /**
     * 获得电话号码的正则表达式：
     *  符合规则的号码：
     *  固定电话
     *  区号 + ‘-' + 固定电话 + ‘-' + 分机号
     *  区号 + ‘-' + 固定电话
     *  区号 + 固定电话
     * @return 电话号码的正则表达式
     */
    public static String isMobileRegexp() {
        return "0[0-9]{3}-*\\d{7,8}";
    }

    /**
     * 电话号 正则
     * @return
     */
    public static String isPhoneRegexp(){
        return "1[35789]\\d{9}";
    }
}
