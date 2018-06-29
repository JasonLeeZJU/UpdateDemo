/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatepluginlib.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

public class Utils {

    private static Handler handler;
    /**
     * 提供一个Main Handler。用于将更新状态派发传递到主线程中进行通知
     * @return Main Handler
     */
    public static Handler getMainHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

    public static boolean isValid(Activity activity) {
        return activity != null && !activity.isFinishing();
    }
}
