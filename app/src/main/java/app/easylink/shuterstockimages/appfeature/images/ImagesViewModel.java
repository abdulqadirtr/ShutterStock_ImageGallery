package app.easylink.shuterstockimages.appfeature.images;

import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;

import app.easylink.shuterstockimages.appfeature.base.BaseViewModel;
import app.easylink.shuterstockimages.appfeature.base.ViewModel;
import app.easylink.shuterstockimages.shutterRemote.ShutterRepo;
import app.easylink.shuterstockimages.shutterRemote.models.MyShutterImage;
import io.reactivex.Single;

public class ImagesViewModel extends BaseViewModel implements ViewModel {
    @Inject
    ShutterRepo repository;
    private AtomicBoolean fetchInProgress = new AtomicBoolean(false);

    @Inject
    ImagesViewModel() {
    }

    public List<MyShutterImage> getImages() {
        return repository.getImages();
    }

    @Nullable
    Single<Boolean> fetchNextPage() {
        if (fetchInProgress.getAndSet(true)) {
            return null;
        }

        return repository.fetchNextPage().doOnSuccess(success -> fetchInProgress.set(false));
    }
}
