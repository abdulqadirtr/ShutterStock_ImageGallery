package app.easylink.shuterstockimages.di.module;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import app.easylink.shuterstockimages.BuildConfig;
import app.easylink.shuterstockimages.di.qualifier.ApplicationContext;
import app.easylink.shuterstockimages.shutterRemote.ShutterApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    private static final int NETWORK_REQUEST_TIMEOUT_SECONDS = 5;
    private static final String RESPONSE_CACHE_DIRECTORY = "response_cache";
    private static final long CACHE_SIZE = 10 * 1024 * 1024;

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@ApplicationContext Context context) {
        File responseCacheDirectory = new File(context.getCacheDir(), RESPONSE_CACHE_DIRECTORY);
        Cache cache = new Cache(responseCacheDirectory, CACHE_SIZE);

        OkHttpClient.Builder builder =
                new OkHttpClient.Builder().connectTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .readTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .writeTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .cache(cache);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(loggingInterceptor);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BuildConfig.SHUTTERSTOCK_BASE_URL)
                .build();
    }

    /**
     * shutterstock.remote
     * Shutter Api Class Location
     *
     * @param retrofit
     * @return
     */
    @Provides
    @Singleton
    ShutterApi provideShutterApi(Retrofit retrofit) {
        return retrofit.create(ShutterApi.class);
    }


}
