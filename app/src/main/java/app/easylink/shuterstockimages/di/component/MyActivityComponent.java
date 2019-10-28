package app.easylink.shuterstockimages.di.component;

import app.easylink.shuterstockimages.appfeature.images.ImagesActivity;
import app.easylink.shuterstockimages.appfeature.launch.LaunchActivity;
import app.easylink.shuterstockimages.di.module.MyActivityModule;
import app.easylink.shuterstockimages.di.scope.MyActivityScope;
import dagger.Component;

@MyActivityScope
@Component(dependencies = AppComponent.class, modules = { MyActivityModule.class })
public interface MyActivityComponent {
    void inject(LaunchActivity launchActivity);
    void inject(ImagesActivity imagesActivity);
}
