/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatedemolib.flow;

import android.app.Activity;
import android.app.Dialog;

import com.jason.framework.updatedemolib.UpdateBuilder;
import com.jason.framework.updatedemolib.base.DownloadCallback;
import com.jason.framework.updatedemolib.base.DownloadWorker;
import com.jason.framework.updatedemolib.base.InstallNotifier;
import com.jason.framework.updatedemolib.model.Update;

import com.jason.framework.updatedemolib.UpdateBuilder;
import com.jason.framework.updatedemolib.base.DownloadCallback;
import com.jason.framework.updatedemolib.base.DownloadWorker;
import com.jason.framework.updatedemolib.base.InstallNotifier;
import com.jason.framework.updatedemolib.model.Update;
import com.jason.framework.updatedemolib.util.ActivityManager;
import com.jason.framework.updatedemolib.util.SafeDialogHandle;
import com.jason.framework.updatedemolib.util.Utils;

import java.io.File;

/**
 * 默认的下载任务的回调监听。主要用于接收从{@link DownloadWorker}中传递过来的下载状态。通知用户并触发后续流程
 *
 * @author JasonLee
 */
public final class DefaultDownloadCallback implements DownloadCallback {

    private UpdateBuilder builder;
    // 通过UpdateConfig或者UpdateBuilder所设置的下载回调监听。通过此监听器进行通知用户下载状态
    private DownloadCallback callback;
    private Update update;
    // 通过DownloadCreator所创建的回调监听，通过此监听器进行下载通知的UI更新
    private DownloadCallback innerCB;

    public void setBuilder(UpdateBuilder builder) {
        this.builder = builder;
        callback = builder.getDownloadCallback();
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    @Override
    public void onDownloadStart() {
        try {
            if (callback != null) {
                callback.onDownloadStart();
            }
            innerCB = getInnerCB();
            if (innerCB != null) {
                innerCB.onDownloadStart();
            }
        } catch (Throwable t) {
            onDownloadError(t);
        }
    }

    private DownloadCallback getInnerCB() {
        if (innerCB != null || !builder.getUpdateStrategy().isShowDownloadDialog()) {
            return innerCB;
        }

        Activity current = ActivityManager.get().topActivity();
        if (Utils.isValid(current)) {
            innerCB = builder.getDownloadNotifier().create(update,current);
        }
        return innerCB;
    }

    @Override
    public void onDownloadComplete(File file) {
        try {
            if (callback != null) {
                callback.onDownloadComplete(file);
            }

            if (innerCB != null) {
                innerCB.onDownloadComplete(file);
            }
        } catch (Throwable t) {
            onDownloadError(t);
        }
    }

    public void postForInstall(final File file) {
        final UpdateBuilder updateBuilder = this.builder;
        Utils.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                InstallNotifier notifier = updateBuilder.getInstallNotifier();
                notifier.setBuilder(updateBuilder);
                notifier.setUpdate(update);
                notifier.setFile(file);
                Activity current = ActivityManager.get().topActivity();
                if (Utils.isValid(current)
                        && !builder.getUpdateStrategy().isAutoInstall()) {
                    Dialog dialog = notifier.create(current);
                    SafeDialogHandle.safeShowDialog(dialog);
                } else {
                    notifier.sendToInstall();
                }
            }
        });
    }

    @Override
    public void onDownloadProgress(long current, long total) {
        try {
            if (callback != null) {
                callback.onDownloadProgress(current,total);
            }

            if (innerCB != null) {
                innerCB.onDownloadProgress(current,total);
            }
        } catch (Throwable t) {
            onDownloadError(t);
        }

    }

    @Override
    public void onDownloadError(Throwable t) {
        try {
            if (callback != null) {
                callback.onDownloadError(t);
            }
            if (innerCB != null) {
                innerCB.onDownloadError(t);
            }
        } catch (Throwable ignore) {
            ignore.printStackTrace();
        }
    }
}