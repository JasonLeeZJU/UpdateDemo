/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatedemolib.base;

import com.jason.framework.updatedemolib.UpdateBuilder;
import com.jason.framework.updatedemolib.UpdateConfig;

import com.jason.framework.updatedemolib.UpdateBuilder;
import com.jason.framework.updatedemolib.UpdateConfig;

import java.io.File;

/**
 * 更新下载回调。
 *
 * <p>设置方式：{@link UpdateConfig#setDownloadCallback(DownloadCallback)} 或者 {@link UpdateBuilder#setDownloadCallback(DownloadCallback)}
 *
 * @author JasonLee
 */
public interface DownloadCallback {

    /**
     * 启动下载任务
     * <p>回调线程：UI
     */
    void onDownloadStart();

    /**
     * 下载完成
     *
     * <p>回调线程：UI
     * @param file 下载的文件
     */
    void onDownloadComplete(File file);

    /**
     * 下载进度通知
     *
     * <p>回调线程：UI
     * @param current 当前已下载长度
     * @param total 整个文件长度
     */
    void onDownloadProgress(long current, long total);

    /**
     * 当下载出现异常时通知到此回调
     * <p>回调线程：UI
     * @param t 下载时出现的异常
     */
    void onDownloadError(Throwable t);
}
