/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatepluginlib.impl;

import com.jason.framework.updatepluginlib.base.UpdateStrategy;
import com.jason.framework.updatepluginlib.model.Update;

import com.jason.framework.updatepluginlib.base.UpdateStrategy;
import com.jason.framework.updatepluginlib.model.Update;

/**
 * 当为强制更新时，将会强制使用此更新策略。
 *
 * <p>此更新策略的表现为：<br>
 *     当下载完成后。强制显示下载完成后的界面通知，其他的通知策略默认不变。
 *
 * @author JasonLee on 2018/9/25.
 */
public class ForcedUpdateStrategy implements UpdateStrategy {

    private UpdateStrategy delegate;

    public ForcedUpdateStrategy(UpdateStrategy delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean isShowUpdateDialog(Update update) {
        return delegate.isShowUpdateDialog(update);
    }

    @Override
    public boolean isAutoInstall() {
        return false;
    }

    @Override
    public boolean isShowDownloadDialog() {
        return delegate.isShowDownloadDialog();
    }
}
