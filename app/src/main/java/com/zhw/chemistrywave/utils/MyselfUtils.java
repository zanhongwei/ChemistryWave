package com.zhw.chemistrywave.utils;

import java.util.regex.Pattern;

/**
 * Created by Axehome_Mr.z on 2018/12/17 18:02
 * $Describe
 */
public class MyselfUtils {

    /**
     * 判断string是否为数字
     * 正则表达式判断
     *
     * @param string
     * @return
     */
    public static boolean isDigit(String string) {
        Pattern pattern = Pattern.compile("[0-9]");
        return pattern.matcher(string).matches();

    }


    /**
     * 判断字符串整体全部为数字
     *
     * @param str
     * @return
     */
    public static boolean isDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }



}

