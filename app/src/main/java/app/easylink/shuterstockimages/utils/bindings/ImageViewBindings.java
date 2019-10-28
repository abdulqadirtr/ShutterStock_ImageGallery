package app.easylink.shuterstockimages.utils.bindings;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import app.easylink.shuterstockimages.di.DaggerApplicationManager;
import timber.log.Timber;

public class ImageViewBindings {
    @BindingAdapter("url")
    public static void loadUrl(SubsamplingScaleImageView imageView, String url) {
        DaggerApplicationManager.getComponent().providePicasso().load(url).into(new Target() {
            @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageView.setImage(ImageSource.bitmap(bitmap));
            }

            @Override public void onBitmapFailed(Drawable errorDrawable) {
                Timber.e("onBitmapFailed() called with: " + "errorDrawable = [" + errorDrawable + "]");
            }

            @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }
}
