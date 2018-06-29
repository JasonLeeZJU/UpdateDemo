/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatepluginlib.base;

import android.content.Context;

import com.jason.framework.updatepluginlib.UpdateBuilder;
import com.jason.framework.updatepluginlib.UpdateConfig;
import com.jason.framework.updatepluginlib.model.Update;

import com.jason.framework.updatepluginlib.UpdateBuilder;
import com.jason.framework.updatepluginlib.UpdateConfig;
import com.jason.framework.updatepluginlib.model.Update;
import com.jason.framework.updatepluginlib.impl.DefaultInstallStrategy;

/**
 * 提供一个安装策略。便于针对不同的应用场景。定制不同的安装策略实现。
 *
 * <p>配置方式：通过{@link UpdateConfig#setInstallStrategy(InstallStrategy)}或者{@link UpdateBuilder#setInstallStrategy(InstallStrategy)}
 *
 * <p>默认实现：{@link DefaultInstallStrategy}
 *
 * @author JasonLee
 */
public interface InstallStrategy {

    /**
     * 在此定制你自己的安装策略。如：
     * 1. 普通环境下，调起系统安装页面
     * 2. 插件化环境下，在此进行插件安装
     * 3. 热修复环境下，在此进行热修复dex合并及更多兼容操作
     * @param context application context
     * @param filename The apk filename
     * @param update 更新数据实体类
     */
    void install(Context context, String filename, Update update);
}
