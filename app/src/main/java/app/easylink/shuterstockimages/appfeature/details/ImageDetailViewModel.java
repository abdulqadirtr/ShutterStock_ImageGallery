package app.easylink.shuterstockimages.appfeature.details;

import android.support.annotation.VisibleForTesting;

import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import app.easylink.shuterstockimages.appfeature.base.BaseViewModel;
import app.easylink.shuterstockimages.appfeature.base.UiModule;
import app.easylink.shuterstockimages.di.scope.MyActivityScope;
import app.easylink.shuterstockimages.shutterRemote.models.MyShutterImage;
import timber.log.Timber;

@MyActivityScope public class ImageDetailViewModel extends BaseViewModel {

  @VisibleForTesting MyShutterImage image;

  @Inject RxPermissions rxPermissions;
  @Inject UiModule uiModule;

  @Inject public ImageDetailViewModel() {
  }

  public void setImage(MyShutterImage image) {
    this.image = image;
    Timber.d("onImageClickedNew"+image.getDescription());
    notifyChange();
  }

  public String getImageUrl() {
    return image.getAssets().getPreview().getUrl();
  }


}
