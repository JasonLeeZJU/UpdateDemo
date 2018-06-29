
/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */

package com.jason.framework.updatedemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class ToastTool {
    static Handler mainHandler = new Handler(Looper.getMainLooper());
    static Toast toast;
    @SuppressLint("ShowToast")
    public static void init(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }

    public static void show (final String msg) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                toast.setText(msg);
                toast.show();
            }
        });
    }
}
