package com.spreawdwin.easyblur;

import android.app.Activity;
import android.content.SharedPreferences;

import java.math.BigDecimal;

/**
 * Created by lixiang on 2017/3/16.
 */

public class MyUtils {
    public static boolean isNightMode() {
        SharedPreferences preferences = MyApp.getAppContext().getSharedPreferences(
                Constants.SHARES_COLOURFUL_NEWS, Activity.MODE_PRIVATE
        );
        return preferences.getBoolean(Constants.NIGHT_THEME_MODE, false);
    }


    public static long getFileLength(java.io.File dir) {
        long length = 0;
        for (java.io.File file :
                dir.listFiles()) {
            if (file.isFile()) {
                length += file.length();
            } else
                length += getFileLength(file);
        }
        return length;
    }

    public static String getFileSize(java.io.File dir) {
        BigDecimal bd;
        if (getFileLength(dir) > 1024 * 1024) {
            bd = new BigDecimal(getFileLength(dir) / 1000000);
            return bd.setScale(2, BigDecimal.ROUND_HALF_EVEN) + " M";

        } else {
            bd = new BigDecimal(getFileLength(dir) / 1000);
            return bd.setScale(0, BigDecimal.ROUND_HALF_EVEN) + " k";

        }
    }
}
