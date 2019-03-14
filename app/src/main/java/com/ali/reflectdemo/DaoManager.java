package com.ali.reflectdemo;

import android.content.Context;

import com.example.greendaotest.database.DaoMaster;
import com.example.greendaotest.database.DaoSession;

/**
 * Created by mumu on 2018/7/6.
 */

public class DaoManager {
    private static DaoManager daoManager;
    private final DaoSession daoSession;

    private DaoManager(Context context) {
        daoSession = DaoMaster.newDevSession(context, "my.db");
    }

    public static DaoManager getDefault(Context context) {
        if (daoManager == null) {
            synchronized (DaoManager.class) {
                if (daoManager == null) {
                    daoManager = new DaoManager(context);
                }
            }
        }
        return daoManager;
    }

    public DaoSession daoSession() {
        return daoSession;
    }
}
