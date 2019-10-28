package app.easylink.shuterstockimages.di.component;

import javax.inject.Singleton;

import app.easylink.shuterstockimages.ShutterApplication;
import app.easylink.shuterstockimages.di.module.ApiModule;
import app.easylink.shuterstockimages.di.module.ApplicationModule;
import dagger.Component;

/**
 * This is the component interface which combines modueles
 **/
@Singleton @Component(modules = {ApplicationModule.class, ApiModule.class})
public interface AppComponent extends ShareableAppComponent {
    void inject(ShutterApplication app);

}
