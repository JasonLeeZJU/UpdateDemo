/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatepluginlib.base;

import android.app.Activity;

import com.jason.framework.updatepluginlib.UpdateBuilder;
import com.jason.framework.updatepluginlib.UpdateConfig;
import com.jason.framework.updatepluginlib.model.Update;

import com.jason.framework.updatepluginlib.UpdateBuilder;
import com.jason.framework.updatepluginlib.UpdateConfig;
import com.jason.framework.updatepluginlib.impl.DefaultDownloadNotifier;
import com.jason.framework.updatepluginlib.model.Update;
import com.jason.framework.updatepluginlib.util.ActivityManager;

/**
 * apk下载任务的通知创建器
 *
 * <p>配置方式：通过{@link UpdateConfig#setDownloadNotifier(DownloadNotifier)}或者{@link UpdateBuilder#setDownloadNotifier(DownloadNotifier)}
 *
 * <p>默认实现：{@link DefaultDownloadNotifier}
 *
 * <p>触发逻辑：当配置的更新策略{@link UpdateStrategy#isShowDownloadDialog()}设定为true时。此通知创建器将被触发
 *
 * @author JasonLee
 */
public interface DownloadNotifier {

    /**
     * 创建一个下载任务的下载进度回调。此回调将用于接收下载任务的状态并更新UI。
     *
     * @param update 更新数据实体类
     * @param activity 顶部的Activity实例。通过{@link ActivityManager#topActivity()}进行获取
     * @return 被创建的回调器。允许为null。
     */
    DownloadCallback create(Update update, Activity activity);
}
