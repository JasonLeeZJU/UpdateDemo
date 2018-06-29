/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */
package com.jason.framework.updatepluginlib.base;

import com.jason.framework.updatepluginlib.UpdateBuilder;
import com.jason.framework.updatepluginlib.UpdateConfig;
import com.jason.framework.updatepluginlib.model.Update;

import com.jason.framework.updatepluginlib.UpdateBuilder;
import com.jason.framework.updatepluginlib.UpdateConfig;
import com.jason.framework.updatepluginlib.model.Update;

/**
 * 此类用于解析通过{@link CheckWorker}访问的地址返回的数据。解析出框架所需的{@link Update}实体类数据并提供给框架内部各处使用
 *
 * <p>配置方式：通过{@link UpdateConfig#setUpdateParser(UpdateParser)}或者{@link UpdateBuilder#setUpdateParser(UpdateParser)}
 *
 * @author JasonLee
 */
public interface UpdateParser {

    /**
     * 当更新api网络任务请求成功时。将会触发到此，在此根据网络数据解析创建出对应的更新数据实体类并返回给框架层使用。
     *
     * @param response 更新api返回数据。
     * @return 被创建的更新数据实体类。不能为null
     * @throws Exception 捕获异常。防止crash并统一异常流程
     */
    Update parse(String response) throws Exception;
}
