package app.easylink.shuterstockimages;

import android.app.Application;

import app.easylink.shuterstockimages.di.DaggerApplicationManager;
import timber.log.Timber;

public class ShutterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    protected void initDagger() {
        DaggerApplicationManager.init(this);


    }
}
