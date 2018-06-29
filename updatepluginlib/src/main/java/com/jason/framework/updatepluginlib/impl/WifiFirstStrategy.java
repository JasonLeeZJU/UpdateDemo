/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatepluginlib.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jason.framework.updatepluginlib.base.UpdateStrategy;
import com.jason.framework.updatepluginlib.model.Update;

import com.jason.framework.updatepluginlib.base.UpdateStrategy;
import com.jason.framework.updatepluginlib.model.Update;
import com.jason.framework.updatepluginlib.util.ActivityManager;

/**
 * 默认提供的更新策略：
 * 1. 当处于wifi环境时，只展示下载完成后的通知
 * 2. 当处于非wifi环境是：只展示有新版本更新及下载进度的通知。
 */
public class WifiFirstStrategy implements UpdateStrategy {

    private boolean isWifi;

    @Override
    public boolean isShowUpdateDialog(Update update) {
        isWifi = isConnectedByWifi();
        return !isWifi;
    }

    @Override
    public boolean isAutoInstall() {
        return !isWifi;
    }

    @Override
    public boolean isShowDownloadDialog() {
        return !isWifi;
    }

    private boolean isConnectedByWifi() {
        Context context = ActivityManager.get().getApplicationContext();
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        return info != null
                && info.isConnected()
                && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

}
