package org.ty.cloudCourse.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kangtaiyang on 2018/6/5.
 */
public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request, String key) {
        try {
            return Integer.parseInt(request.getParameter(key));
        } catch (Exception e) {
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key) {
        try {
            return Long.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1L;
        }
    }

    public static Double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.parseDouble(request.getParameter(key));
        } catch (Exception e) {
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key) {
        try {
            return Boolean.parseBoolean(request.getParameter(key));
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if (result != null) {
                result = result.trim();
            }
            if (result.equals("")) {
                result = null;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
