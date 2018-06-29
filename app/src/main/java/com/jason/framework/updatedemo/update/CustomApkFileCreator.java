/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */

package com.jason.framework.updatedemo.update;

import android.os.Environment;

import com.jason.framework.updatedemolib.base.FileCreator;
import com.jason.framework.updatedemolib.impl.DefaultFileCreator;
import com.jason.framework.updatedemolib.model.Update;

import java.io.File;

/**
 * 生成下载apk文件的文件地址
 * 默认使用参考 {@link DefaultFileCreator}
 */

public class CustomApkFileCreator extends FileCreator {
    @Override
    public File create(Update update) {
        // 根据传入的versionName创建一个文件。供下载时网络框架使用
        File path = new File(Environment.getExternalStorageDirectory().getPath() + "/updatePlugin");
        path.mkdirs();
        return new File(path,"UpdatePlugin_" + update.getVersionName());
    }

    @Override
    public File createForDaemon(Update update) {
        // 根据传入的versionName创建一个文件。供下载时网络框架使用
        File path = new File(Environment.getExternalStorageDirectory().getPath() + "/updatePlugin");
        path.mkdirs();
        return new File(path,"UpdatePlugin_daemon_" + update.getVersionName());
    }

}
