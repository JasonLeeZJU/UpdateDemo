/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatedemolib.flow;

import android.app.Activity;
import android.app.Dialog;

import com.jason.framework.updatedemolib.UpdateBuilder;
import com.jason.framework.updatedemolib.base.CheckCallback;
import com.jason.framework.updatedemolib.base.CheckNotifier;
import com.jason.framework.updatedemolib.base.CheckWorker;
import com.jason.framework.updatedemolib.model.Update;

import com.jason.framework.updatedemolib.UpdateBuilder;
import com.jason.framework.updatedemolib.base.CheckCallback;
import com.jason.framework.updatedemolib.base.CheckNotifier;
import com.jason.framework.updatedemolib.base.CheckWorker;
import com.jason.framework.updatedemolib.model.Update;
import com.jason.framework.updatedemolib.util.ActivityManager;
import com.jason.framework.updatedemolib.util.SafeDialogHandle;
import com.jason.framework.updatedemolib.util.Utils;

/**
 * <p><b>核心操作类</b>
 *
 * <p>此类为默认的检查更新api网络任务的通知回调。用于接收从{@link CheckWorker}中所传递过来的各种状态。并进行后续流程触发</p>
 *
 * @author JasonLee
 */
public final class DefaultCheckCallback implements CheckCallback {
    private UpdateBuilder builder;
    private CheckCallback callback;

    public void setBuilder (UpdateBuilder builder) {
        this.builder = builder;
        this.callback = builder.getCheckCallback();
    }

    @Override
    public void onCheckStart() {
        try {
            if (callback != null) {
                callback.onCheckStart();
            }
        } catch (Throwable t) {
            onCheckError(t);
        }
    }

    @Override
    public void hasUpdate(Update update) {
        try {
            if (callback != null) {
                callback.hasUpdate(update);
            }

            CheckNotifier notifier = builder.getCheckNotifier();
            notifier.setBuilder(builder);
            notifier.setUpdate(update);
            Activity current = ActivityManager.get().topActivity();

            if (Utils.isValid(current)
                    && builder.getUpdateStrategy().isShowUpdateDialog(update)) {
                Dialog dialog = notifier.create(current);
                SafeDialogHandle.safeShowDialog(dialog);
            } else {
                notifier.sendDownloadRequest();
            }
        } catch (Throwable t) {
            onCheckError(t);
        }
    }

    @Override
    public void noUpdate() {
        try {
            if (callback != null) {
                callback.noUpdate();
            }
        } catch (Throwable t) {
            onCheckError(t);
        }

    }

    @Override
    public void onCheckError(Throwable t) {
        try {
            if (callback != null) {
                callback.onCheckError(t);
            }
        } catch (Throwable ignore) {
            ignore.printStackTrace();
        }
    }

    @Override
    public void onUserCancel() {
        try {
            if (callback != null) {
                callback.onUserCancel();
            }
        } catch (Throwable t) {
            onCheckError(t);
        }

    }

    @Override
    public void onCheckIgnore(Update update) {
        try {
            if (callback != null) {
                callback.onCheckIgnore(update);
            }
        } catch (Throwable t) {
            onCheckError(t);
        }
    }

}
