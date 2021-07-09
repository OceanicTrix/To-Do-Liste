package com.example.to_doliste;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;

import androidx.test.runner.AndroidJUnitRunner;

import com.example.to_doliste.room.AufgabeRoomDatabase;

import junit.extensions.ActiveTestSuite;

public class CustomRunner extends AndroidJUnitRunner {
    AufgabeRoomDatabase db = null;
    /*@Override
    public Activity newActivity(Class<?> clazz, Context context, IBinder token, Application application, Intent intent, ActivityInfo info, CharSequence title, Activity parent, String id, Object lastNonConfigurationInstance) throws InstantiationException, IllegalAccessException {
        Activity var = super.newActivity(clazz, context, token, application, intent, info, title, parent, id, lastNonConfigurationInstance);
        db = AufgabeRoomDatabase.getInstance(var.getApplicationContext());
        db.clearAllTables();
        return var;
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Activity var = super.newActivity(cl, className, intent);
        db = AufgabeRoomDatabase.getInstance(var.getApplicationContext());
        db.clearAllTables();
        return var;
    }*/

    @Override
    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        super.callActivityOnCreate(activity, bundle);
    }

    @Override
    public void callApplicationOnCreate(Application app) {
        super.callApplicationOnCreate(app);
        db = AufgabeRoomDatabase.getInstance(app);
        db.clearAllTables();

    }

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Application var = super.newApplication(cl, className, context);
        //db = AufgabeRoomDatabase.getInstance(context);
        //db.clearAllTables();
        return var;
    }
}
