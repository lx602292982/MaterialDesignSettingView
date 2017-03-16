package com.spreawdwin.easyblur;

import android.app.Application;

import com.orhanobut.logger.Logger;


/**
 * Created by lixiang on 2017/3/16.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init().getLogLevel();
    }
}
