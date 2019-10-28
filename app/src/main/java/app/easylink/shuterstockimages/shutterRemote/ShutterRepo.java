package app.easylink.shuterstockimages.shutterRemote;

import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.easylink.shuterstockimages.AppConstants;
import app.easylink.shuterstockimages.shutterRemote.models.MyShutterImage;
import io.reactivex.Single;
import timber.log.Timber;

@Singleton
public class ShutterRepo {
    private static final String AUTHORIZATION_HEADER =
    "Basic OWZmZWVjMmRhN2IzZDdmZTliYjc6YTYwZTljZThiYzJkM2I1YTIzYzFhYjVkMmY0MjVlOWZjMTAzZjU3NA==";
    private List<MyShutterImage> images = new ArrayList<>();

    @Inject
    ShutterApi api;

    private int currentPage = 0;

    @Inject public ShutterRepo() {
    }

    public Single<Boolean> fetchFirstImageSet() {
        return fetchPage(1).doOnSubscribe(disposable -> clear());
    }

    public Single<Boolean> fetchNextPage() {
        return fetchPage(currentPage + 1);
    }

    private Single<Boolean> fetchPage(int page) {
        /**
         * Get images method is in ShutterApi for authorization purpose
         */

        return api.getImages(AUTHORIZATION_HEADER, page, AppConstants.PER_PAGE_SIZE)
                // Try for 3 times, then give up
                .retry(2).map(shutterImages -> {
                    // To ignore multiple responses for a single page request
                    if (currentPage + 1 == page) {
                        currentPage = page;
                        images.addAll(shutterImages.getData());
                    }
                    return true;
                }).onErrorReturn(e -> {
                    Timber.e(e);
                    return false;
                });
    }

    public List<MyShutterImage> getImages() {
        return images;
    }

    @VisibleForTesting
    public int getCurrentPage() {
        return currentPage;
    }

    @VisibleForTesting public void clear() {
        currentPage = 0;
        images.clear();
    }
}
