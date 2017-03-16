package com.spreawdwin.easyblur;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.orhanobut.logger.Logger;


/**
 * Created by lixiang on 2017/3/16.
 */

public class MyApp extends Application {
    private static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
        Logger.init().getLogLevel();
        initDayNightMode();

        boolean isNight = SharedPreferencesUtil.getBoolean(this, Constants.ISNIGHT, false);

        if (isNight) {
            //使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            //不使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void initDayNightMode() {
        if (MyUtils.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public static Context getAppContext() {
        return sAppContext;
    }
}
