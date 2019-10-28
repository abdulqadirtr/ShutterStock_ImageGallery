package app.easylink.shuterstockimages.di.module;

import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import app.easylink.shuterstockimages.ShutterApplication;
import app.easylink.shuterstockimages.di.qualifier.ApplicationContext;
import app.easylink.shuterstockimages.shutterRemote.OkHttp3Downloader;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class ApplicationModule {
    private final ShutterApplication app;

    public ApplicationModule(ShutterApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    Picasso providePicasso(OkHttpClient okHttpClient) {
        return new Picasso.Builder(app).downloader(new OkHttp3Downloader(okHttpClient)).build();
    }
}
