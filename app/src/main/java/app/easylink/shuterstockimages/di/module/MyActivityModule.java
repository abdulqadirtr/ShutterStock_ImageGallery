package app.easylink.shuterstockimages.di.module;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Scope;

import app.easylink.shuterstockimages.appfeature.base.navigator.ActivityNavigator;
import app.easylink.shuterstockimages.appfeature.base.navigator.Navigator;
import app.easylink.shuterstockimages.di.scope.MyActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class MyActivityModule {

    private final AppCompatActivity activity;

    public MyActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {

        return activity;
    }

    @Provides
    @MyActivityScope
    Navigator provideNavigator() {
        return new ActivityNavigator(activity);
    }

    @Provides
    @MyActivityScope
    RxPermissions provideRxPermissions() {
        return new RxPermissions(activity);
    }


}
