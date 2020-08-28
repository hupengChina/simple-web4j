package org.hupeng.framework.commons.util;

import java.util.regex.Pattern;

/**
 * @author : hupeng
 * @date : 2020/8/5
 */
public class StringUtil {

    public static boolean isInt(String str) {
        return Pattern.compile("0|([-]?[1-9][0-9]*)").matcher(str).matches();
    }
}
