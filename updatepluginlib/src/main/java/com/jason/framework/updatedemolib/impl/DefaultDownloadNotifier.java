/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatedemolib.impl;

import android.app.Activity;
import android.app.ProgressDialog;

import com.jason.framework.updatedemolib.base.DownloadCallback;
import com.jason.framework.updatedemolib.base.DownloadNotifier;
import com.jason.framework.updatedemolib.model.Update;

import com.jason.framework.updatedemolib.base.DownloadCallback;
import com.jason.framework.updatedemolib.base.DownloadNotifier;
import com.jason.framework.updatedemolib.model.Update;
import com.jason.framework.updatedemolib.util.SafeDialogHandle;

import java.io.File;

/**
 * 默认使用的下载进度通知创建器: 在此创建Dialog弹窗显示并根据下载回调通知进行进度条更新
 * @author JasonLee
 */
public class DefaultDownloadNotifier implements DownloadNotifier {
    @Override
    public DownloadCallback create(Update update, Activity activity) {
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);
        dialog.setProgress(0);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        SafeDialogHandle.safeShowDialog(dialog);
        return new DownloadCallback() {
            @Override
            public void onDownloadStart() {
            }

            @Override
            public void onDownloadComplete(File file) {
                SafeDialogHandle.safeDismissDialog(dialog);
            }

            @Override
            public void onDownloadProgress(long current, long total) {
                int percent = (int) (current * 1.0f / total * 100);
                dialog.setProgress(percent);
            }

            @Override
            public void onDownloadError(Throwable t) {
                SafeDialogHandle.safeDismissDialog(dialog);
            }
        };
    }
}
