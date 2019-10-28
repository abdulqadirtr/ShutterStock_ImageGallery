package app.easylink.shuterstockimages.di.component;


import com.squareup.picasso.Picasso;

import app.easylink.shuterstockimages.shutterRemote.ShutterRepo;
import okhttp3.OkHttpClient;

public interface ShareableAppComponent {

    ShutterRepo provideShutterRepository();

    Picasso providePicasso();

    OkHttpClient provideOkHttpClient();
}
