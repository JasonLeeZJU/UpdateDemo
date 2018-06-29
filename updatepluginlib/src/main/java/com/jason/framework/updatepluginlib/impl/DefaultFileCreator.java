/*
 * Copyright (C) 2017 Jason
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
