
/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */

package com.jason.framework.updatedemo;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.jason.framework.updatedemo.update.NotificationDownloadCreator;
import com.jason.framework.updatedemo.update.NotificationInstallCreator;
import com.jason.framework.updatedemo.update.NotificationUpdateCreator;
import com.jason.framework.updatedemo.update.ToastCallback;
import com.jason.framework.updatedemolib.UpdateBuilder;
import com.jason.framework.updatedemolib.UpdateConfig;
import com.jason.framework.updatedemolib.base.UpdateParser;
import com.jason.framework.updatedemolib.model.Update;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class BaseActivity extends Activity {

    private ToastCallback callBack;
    private Boolean isPermissionGrant;
    private UpdateBuilder demoTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        requestStoragePermission();
        callBack = new ToastCallback(this);
    }


    /** 请求文件读写权限。*/
    private void requestStoragePermission() {
        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        isPermissionGrant = aBoolean;
                    }
                });
    }

    @OnClick(R.id.start_update)
    void onStartClick () {
        createBuilder().check();
    }

    @OnClick(R.id.start_background_update)
    void onDemoStartClick() {
        demoTask = createBuilder();
        demoTask.checkWithDaemon(5);// 后台更新时间间隔设置为5秒。
    }

    @OnClick(R.id.stop_background_update)
    void onStopDaemonClick() {
        if (demoTask != null) {
            demoTask.stopDaemon();
            demoTask = null;
        }
    }

    private UpdateBuilder createBuilder() {

        UpdateBuilder updateBuilder = UpdateBuilder.create();

        updateBuilder.setDownloadCallback(callBack);
        updateBuilder.setCheckCallback(callBack);

        //updateBuilder.setCheckNotifier(new NotificationUpdateCreator());
        //updateBuilder.setDownloadNotifier(new NotificationDownloadCreator());
        //updateBuilder.setInstallNotifier(new NotificationInstallCreator());

        return updateBuilder;
    }

    private UpdateConfig createNewConfig() {
        return UpdateConfig.createConfig()
                // .setUrl("https://raw.githubusercontent.com/JasonLeeZJU/UpdateDemo/master/update.json")
                //  .setUrl("https://rawgit.com/JasonLeeZJU/UpdateDemo/master/update.json")
                .setUrl("http://192.168.15.36/Update/update.json")
                .setUpdateParser(new UpdateParser() {
            @Override
            public Update parse(String httpResponse) throws Exception {
                JSONObject jsonObject = new JSONObject(httpResponse);
                Update update = new Update();

                update.setUpdateUrl(jsonObject.optString("update_url"));
                update.setVersionCode(jsonObject.optInt("update_ver_code"));
                update.setVersionName(jsonObject.optString("update_ver_name"));
                update.setUpdateContent(jsonObject.optString("update_content"));
                update.setForced(false);
                update.setIgnore(jsonObject.optBoolean("ignore_able",true));
                update.setMd5(jsonObject.optString("md5"));

                return update;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
