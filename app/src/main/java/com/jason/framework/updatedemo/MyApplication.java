
/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */

package com.jason.framework.updatedemo;

import android.app.Application;
import android.util.Log;

import org.json.JSONObject;
import com.jason.framework.updatedemo.widget.ToastTool;
import com.jason.framework.updatedemolib.UpdateConfig;
import com.jason.framework.updatedemolib.model.Update;
import com.jason.framework.updatedemolib.base.UpdateParser;

import static android.content.ContentValues.TAG;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // UpdateConfig为全局配置。当在其他页面中。使用UpdateBuilder进行检查更新时。
        // 对于没传的参数，会默认使用UpdateConfig中的全局配置
        ToastTool.init(this);
        UpdateConfig.getConfig()
           // .setUrl("https://raw.githubusercontent.com/JasonLeeZJU/UpdateDemo/master/update.json")
           // .setUrl("https://rawgit.com/JasonLeeZJU/UpdateDemo/master/update.json")
                .setUrl("http://192.168.15.36/Update/update.json")
    // .setCheckEntity(new CheckEntity().setMethod(HttpMethod.GET).setUrl("http://192.168.15.36"))

                .setUpdateParser(new UpdateParser() {
                    @Override
                    public Update parse(String httpResponse) throws Exception{

                        JSONObject jsonObject = new JSONObject(httpResponse);
                        Update update = new Update();


                        // 此apk包的下载地址
                        update.setUpdateUrl(jsonObject.optString("update_url"));
                        // 此apk包的版本号
                        update.setVersionCode(jsonObject.optInt("update_ver_code"));
                        // 此apk包的版本名称
                        update.setVersionName(jsonObject.optString("update_ver_name"));
                        // 此apk包的更新内容
                        update.setUpdateContent(jsonObject.optString("update_content"));
                        // 此apk包是否为强制更新
                        update.setForced(false);
                        // 是否显示忽略此次版本更新按钮
                        update.setIgnore(jsonObject.optBoolean("ignore_able",true));
                        //md5 用于文件校验
                        update.setMd5(jsonObject.optString("md5"));


                        /*
                        JSONObject jsonObject1 = jsonObject.getJSONArray(updateAddress
                                .getUpdate_json_field_name()).getJSONObject(updateAddress
                                .getUpdate_json_field_index());

                        update.setUpdateUrl(jsonObject1.optString("update_url"));
                        update.setVersionCode(jsonObject1.optInt("update_ver_code"));
                        update.setVersionName(jsonObject1.optString("update_ver_name"));
                        update.setUpdateContent(jsonObject1.optString("update_content"));
                        update.setForced(false);
                        update.setIgnore(jsonObject1.optBoolean("ignore_able",true));
                        update.setMd5(jsonObject1.optString("md5"));
                        */

                        return update;
                    }
                })
                // TODO: 2018/6/11 除了以上两个参数为必填。以下的参数均为非必填项。
                // 检查更新接口是否有新版本更新的回调。
//                .setCheckCallback(callback)
                // apk下载的回调
//                .setDownloadCallback(callback)
                // 自定义更新检查器。
                /*.setUpdateChecker(new UpdateChecker() {
                    @Override
                    public boolean check(Update update) {
//                           此处根据上面jsonParser解析出的update对象来判断是否此update代表的
//                           版本应该被更新。返回true为需要更新。返回false代表不需要更新
                        return false;
                    }
                })
                 // 自定义更新接口的访问任务
                .setCheckWorker(new CheckWorker() {
                    @Override
                    protected String check(CheckEntity setUrl) throws Exception {
                        // TODO: 2018/6/11 此处运行于子线程。在此进行更新接口访问
                        return null;
                    }
                })
                // 自定义apk下载任务
                .setDownloadWorker(new DownloadWorker() {
                    @Override
                    protected void download(String setUrl, File file) throws Exception {
                        // TODO: 2018/6/11 此处运行于子线程，在此进行文件下载任务
                    }
                })
                // 自定义下载文件缓存,默认下载至系统自带的缓存目录下
                .setFileCreator(new FileCreator() {
                    @Override
                    public File create(String versionName) {
                        // TODO: 2018/6/11 解析的Update实例中的update_url数据。在此可自定义下载文件缓存路径及文件名。放置于File中
                        return null;
                    }
                })
                // 自定义更新策略，默认WIFI下自动下载更新
                .setUpdateStrategy(new UpdateStrategy() {
                    @Override
                    public boolean isShowUpdateDialog(Update update) {
                        // 是否在检查到有新版本更新时展示Dialog。
                        return false;
                    }

                    @Override
                    public boolean isAutoInstall() {
                        // 是否自动更新.当为自动更新时。代表下载成功后不通知用户。直接调起安装。
                        return false;
                    }

                    @Override
                    public boolean isShowDownloadDialog() {
                        // 在APK下载时。是否显示下载进度的Dialog
                        return false;
                    }
                })
                // 自定义检查出更新后显示的Dialog，
                .setCheckNotifier(new CheckNotifier() {
                    @Override
                    public Dialog create(Update update, Activity context) {
                        // TODO: 2018/6/11 此处为检查出有新版本需要更新时的回调。运行于主线程，在此进行更新Dialog的创建
                        return null;
                    }
                })
                // 自定义下载时的进度条Dialog
                .setDownloadNotifier(new DownloadNotifier() {
                    @Override
                    public DownloadCallback create(Update update, Activity activity) {
                        // TODO: 2018/6/11 此处为正在下载APK时的回调。运行于主线程。在此进行Dialog自定义与显示操作。
                        // TODO: 2018/6/11 需要在此创建并返回一个UpdateDownloadCB回调。用于对Dialog进行更新。
                        return null;
                    }
                })
                // 自定义下载完成后。显示的Dialog
                .setInstallNotifier(new InstallNotifier() {
                    @Override
                    public Dialog create(Update update, String s, Activity activity) {
                        // TODO: 2018/6/11 此处为下载APK完成后的回调。运行于主线程。在此创建Dialog
                        return null;
                    }
                })*/;

    }
}
