package app.easylink.shuterstockimages.di;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import app.easylink.shuterstockimages.ShutterApplication;
import app.easylink.shuterstockimages.di.component.AppComponent;
import app.easylink.shuterstockimages.di.component.DaggerAppComponent;
import app.easylink.shuterstockimages.di.component.DaggerMyActivityComponent;
import app.easylink.shuterstockimages.di.component.MyActivityComponent;
import app.easylink.shuterstockimages.di.module.ApiModule;
import app.easylink.shuterstockimages.di.module.ApplicationModule;
import app.easylink.shuterstockimages.di.module.MyActivityModule;

public class DaggerApplicationManager {
    private static AppComponent component;

    public static void init(ShutterApplication app) {
        init(app, new ApiModule());
    }

    public static void init(ShutterApplication app, ApiModule apiModule) {
        if (component != null) return;

        component = DaggerAppComponent.builder().applicationModule(new ApplicationModule(app)).apiModule(apiModule).build();

        component.inject(app);
    }

    @NonNull
    public static AppComponent getComponent() {
        return component;
    }

    @NonNull
    public static MyActivityComponent getActivityComponent(AppCompatActivity activity) {
        return DaggerMyActivityComponent.builder()
                .appComponent(component)
                .myActivityModule(new MyActivityModule(activity))
                .build();
    }
}
