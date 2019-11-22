package com.tuny.demo.util;

import org.springframework.lang.Nullable;

/**
 * @author hucs
 * @date 2019/11/18 13:27
 * @since JDK 1.8
 */
public class StringUtil {

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param str 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int length(String str) {
        if (str == null) {
            return 0;
        }
        char[] c = str.toCharArray();
        int len = 0;
        int k = 0x80;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (c[i] / k != 0) {
                len++;
            }
        }
        return len;
    }

    /**
     * Spring.StringUtils的非空校验，此处拿来直接用
     */
    public static boolean isEmpty(@Nullable Object str) {
        return str == null || "".equals(str);
    }
}
