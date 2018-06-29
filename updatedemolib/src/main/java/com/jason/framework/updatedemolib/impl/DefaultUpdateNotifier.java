/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatedemolib.impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import com.jason.framework.updatedemolib.base.CheckNotifier;

import com.jason.framework.updatedemolib.base.CheckNotifier;
import com.jason.framework.updatedemolib.util.SafeDialogHandle;

/**
 * 默认使用的在检查到有更新时的通知创建器：创建一个弹窗提示用户当前有新版本需要更新。
 *
 * @author JasonLee
 * @see CheckNotifier
 */
public class DefaultUpdateNotifier extends CheckNotifier {
    @Override
    public Dialog create(Activity activity) {
        String updateContent = "版本号: " + update.getVersionName() + "\n\n\n"
                + update.getUpdateContent();
        AlertDialog.Builder builder =  new AlertDialog.Builder(activity)
                .setMessage(updateContent)
                .setTitle("你有新版本需要更新")
                .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendDownloadRequest();
                        SafeDialogHandle.safeDismissDialog((Dialog) dialog);
                    }
                });
        if (update.isIgnore() && !update.isForced()) {
            builder.setNeutralButton("忽略此版本", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendUserIgnore();
                    SafeDialogHandle.safeDismissDialog((Dialog) dialog);
                }
            });
        }

        if (!update.isForced()) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendUserCancel();
                    SafeDialogHandle.safeDismissDialog((Dialog) dialog);
                }
            });
        }
        builder.setCancelable(false);
        return builder.create();
    }
}
