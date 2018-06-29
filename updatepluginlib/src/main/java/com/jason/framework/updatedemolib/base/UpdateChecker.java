/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatedemolib.base;

import com.jason.framework.updatedemolib.UpdateBuilder;
import com.jason.framework.updatedemolib.UpdateConfig;
import com.jason.framework.updatedemolib.model.Update;

import com.jason.framework.updatedemolib.UpdateBuilder;
import com.jason.framework.updatedemolib.UpdateConfig;
import com.jason.framework.updatedemolib.impl.DefaultUpdateChecker;
import com.jason.framework.updatedemolib.model.Update;

/**
 * 此类用于对通过{@link UpdateParser}所解析返回的更新数据进行检查。判断是否此新版本数据需要被更新
 *
 * <p>配置方式：通过{@link UpdateConfig#setUpdateChecker(UpdateChecker)}或者{@link UpdateBuilder#setUpdateChecker(UpdateChecker)}
 *
 * <p>默认实现：{@link DefaultUpdateChecker}
 *
 * @author JasonLee
 */
public interface UpdateChecker {

    /**
     * 对提供的更新实体类进行检查。判断是否需要进行更新。
     * @param update 更新数据实体类
     * @return True代表检查通过。此版本需要被更新
     * @throws Exception error occurs.
     */
    boolean check(Update update) throws Exception;
}
