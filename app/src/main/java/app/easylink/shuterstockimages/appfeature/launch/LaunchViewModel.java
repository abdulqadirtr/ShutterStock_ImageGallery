package app.easylink.shuterstockimages.appfeature.launch;

import android.support.annotation.VisibleForTesting;
import android.view.View;


import javax.inject.Inject;

import app.easylink.shuterstockimages.R;
import app.easylink.shuterstockimages.appfeature.base.BaseViewModel;
import app.easylink.shuterstockimages.appfeature.base.UiModule;
import app.easylink.shuterstockimages.appfeature.base.ViewModel;
import app.easylink.shuterstockimages.appfeature.base.navigator.Navigator;
import app.easylink.shuterstockimages.appfeature.images.ImagesActivity;
import app.easylink.shuterstockimages.di.scope.MyActivityScope;
import app.easylink.shuterstockimages.shutterRemote.ShutterRepo;
import app.easylink.shuterstockimages.utils.rx.BgOperation;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

@MyActivityScope
public class LaunchViewModel extends BaseViewModel implements ViewModel {

    @VisibleForTesting
    boolean animationDone = false;
    @VisibleForTesting
    boolean workDone = false;
    @VisibleForTesting
    boolean workSuccess = false;

    @Inject
    ShutterRepo repository;
    @Inject
    UiModule ui;
    @Inject
    Navigator navigator;

    @Inject
    LaunchViewModel() {
    }

    @Override
    public void resume() {
        Disposable disposable =
                repository.fetchFirstImageSet().compose(new BgOperation<>()).subscribe(success -> {
                    workDone = true;
                    notifyChange();

                    workSuccess = success;

                    if (workSuccess) {
                        goToNextScreen();
                    } else {
                        ui.showSnackBar(R.string.failed_to_fetch_images);
                    }
                });

        disposables.add(disposable);
    }

    private void goToNextScreen() {
        if (animationDone && workDone && workSuccess) {
            Timber.d("goToNextScreen: Data fetched and animation finished. Moving to images screen");
            navigator.startActivity(ImagesActivity.class);
            navigator.finishActivity();
        } else {
            Timber.d(
                    "goToNextScreen: Wait till both animation and work are done. workDone=%s, animationDone=%s",
                    workDone, animationDone);
        }
    }

    void onAnimationDone() {
        animationDone = true;
        notifyChange();

        goToNextScreen();
    }

    public int getProgressBarVisibility() {
        return animationDone && !workDone ? View.VISIBLE : View.INVISIBLE;
    }
}
