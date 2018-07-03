/*
 * Copyright (c) 2018.
 * JasonLee
 * OpenAccess All rights reserved.
 */

package com.jason.framework.updatedemo;

public class updateAddress {

    public static String getUpdate_json_json_address(){
        return "http://192.168.15.36/Update/update.json";
    }


    public static String getUpdate_json_field_name(){
        return "stable_update";
    }
    public static int getUpdate_json_field_index(){
        return 0;
    }

    public static String getUpdate_json_apk_address(){
        return "update_url";
    }

    public static String getUpdate_json_version_code(){
        return "update_ver_code";
    }

    public static String getUpdate_json_version_name(){
        return "update_ver_name";
    }

    public static String getUpdate_json_update_content(){
        return "update_content";
    }

    public static String getUpdate_json_ignorable_flag(){
        return "ignore_able";
    }

    public static String getUpdate_json_md5(){
        return "md5";
    }
}
