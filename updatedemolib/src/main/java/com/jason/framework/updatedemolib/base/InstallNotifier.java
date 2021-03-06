/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatedemolib.base;

import android.app.Activity;
import android.app.Dialog;

import com.jason.framework.updatedemolib.UpdateBuilder;
import com.jason.framework.updatedemolib.UpdateConfig;
import com.jason.framework.updatedemolib.model.Update;

import com.jason.framework.updatedemolib.UpdateBuilder;
import com.jason.framework.updatedemolib.UpdateConfig;
import com.jason.framework.updatedemolib.impl.DefaultInstallNotifier;
import com.jason.framework.updatedemolib.model.Update;
import com.jason.framework.updatedemolib.util.ActivityManager;
import com.jason.framework.updatedemolib.util.UpdatePreference;

import java.io.File;

/**
 * <p>此类为当检查到更新时的通知创建器基类。
 *
 * <p>配置方式：通过{@link UpdateConfig#setInstallNotifier(InstallNotifier)}或者{@link UpdateBuilder#setInstallNotifier(InstallNotifier)}
 *
 * <p>默认实现：{@link DefaultInstallNotifier}
 *
 * <p>触发逻辑：当检查到有新版本更新时且配置的更新策略{@link UpdateStrategy#isAutoInstall()}设定为false时。此通知创建器将被触发
 *
 * <p>定制说明：<br>
 *     1. 当需要进行后续更新操作时(请求调起安装任务)：调用{@link #sendToInstall()}}<br>
 *     2. 当需要取消此次更新操作时：调用{@link #sendUserCancel()}<br>
 *     3. 当需要忽略此版本更新时：调用{@link #sendCheckIgnore()}<br>
 *
 * @author JasonLee
 */
public abstract class InstallNotifier {

    protected UpdateBuilder builder;
    protected Update update;
    protected File file;

    public final void setBuilder(UpdateBuilder builder) {
        this.builder = builder;
    }

    public final void setUpdate(Update update) {
        this.update = update;
    }

    public final void setFile(File file) {
        this.file = file;
    }

    /**
     * 创建一个Dialog进行界面展示。提示用户当前有新版本可更新。且当前apk已下载完成。
     *
     * <p>数据来源：获取父类的{@link #update}属性
     *
     * <p>定制说明：<br>
     *     1. 当需要进行后续更新操作时(请求调起安装任务)：调用{@link #sendToInstall()}}<br>
     *     2. 当需要取消此次更新操作时：调用{@link #sendUserCancel()}<br>
     *     3. 当需要忽略此版本更新时：调用{@link #sendCheckIgnore()}<br>
     *
     * @param activity 当前最顶层的Activity实例。用于提供界面展示功能
     * @return 需要展示的Dialog。若为null则表示不进行Dialog展示。比如说此处需要以Notification样式进行展示时。
     */
    public abstract Dialog create(Activity activity);

    /**
     * 请求调起安装任务
     */
    public final void sendToInstall() {
        builder.getInstallStrategy().install(ActivityManager.get().getApplicationContext(), file.getAbsolutePath(), update);
    }

    /**
     * 请求取消更新任务
     */
    public final void sendUserCancel() {
        if (builder.getCheckCallback() != null) {
            builder.getCheckCallback().onUserCancel();
        }

    }

    /**
     * 请求将此版本加入版本忽略列表
     */
    public final void sendCheckIgnore() {
        if (builder.getCheckCallback() != null) {
            builder.getCheckCallback().onCheckIgnore(update);
        }
        UpdatePreference.saveIgnoreVersion(update.getVersionCode());
    }

}
