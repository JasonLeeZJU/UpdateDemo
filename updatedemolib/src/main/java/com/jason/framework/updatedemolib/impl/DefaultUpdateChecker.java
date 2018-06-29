/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatedemolib.impl;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.jason.framework.updatedemolib.base.UpdateChecker;
import com.jason.framework.updatedemolib.model.Update;

import com.jason.framework.updatedemolib.base.UpdateChecker;
import com.jason.framework.updatedemolib.model.Update;
import com.jason.framework.updatedemolib.util.ActivityManager;
import com.jason.framework.updatedemolib.util.UpdatePreference;

/**
 * 默认的更新数据检查器。
 *
 * <p>实现逻辑说明：<br>
 *     1. 当为强制更新时：直接判断当前的{@link Update#versionCode}是否大于当前应用的versionCode。若大于。则代表需要更新<br>
 *     2. 当为普通更新，判断是否当前更新的版本在忽略版本列表中。如果不是。再进行versionCode比对。
 *
 * @author JasonLee
 */
public class DefaultUpdateChecker implements UpdateChecker {
    @Override
    public boolean check(Update update) throws Exception{
        int curVersion = getApkVersion(ActivityManager.get().getApplicationContext());
        return update.getVersionCode() > curVersion &&
                (update.isForced() ||
                        !UpdatePreference.getIgnoreVersions().contains(String.valueOf(update.getVersionCode())));
    }

    public int getApkVersion (Context context) throws PackageManager.NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
        return packageInfo.versionCode;
    }
}
