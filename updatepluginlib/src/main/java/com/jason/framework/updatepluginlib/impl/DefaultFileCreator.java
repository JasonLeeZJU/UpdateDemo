/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatepluginlib.impl;

import android.content.Context;

import com.jason.framework.updatepluginlib.base.FileCreator;
import com.jason.framework.updatepluginlib.model.Update;

import com.jason.framework.updatepluginlib.base.FileCreator;
import com.jason.framework.updatepluginlib.model.Update;
import com.jason.framework.updatepluginlib.util.ActivityManager;

import java.io.File;

/**
 * 默认使用的APK下载文件创建器。
 *
 * @author JasonLee
 */
public class DefaultFileCreator extends FileCreator {
    @Override
    public File create(Update update) {
        File cacheDir = getCacheDir();
        cacheDir.mkdirs();
        return new File(cacheDir,"update_normal_" + update.getVersionName());
    }

    @Override
    public File createForDaemon(Update update) {
        File cacheDir = getCacheDir();
        cacheDir.mkdirs();
        return new File(cacheDir,"update_daemon_" + update.getVersionName());
    }

    private File getCacheDir() {
        Context context = ActivityManager.get().getApplicationContext();
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        cacheDir = new File(cacheDir,"update");
        return cacheDir;
    }
}
