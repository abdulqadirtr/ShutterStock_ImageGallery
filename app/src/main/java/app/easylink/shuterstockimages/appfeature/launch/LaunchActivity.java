package app.easylink.shuterstockimages.appfeature.launch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AccelerateDecelerateInterpolator;

import app.easylink.shuterstockimages.R;
import app.easylink.shuterstockimages.appfeature.base.BaseActivity;
import app.easylink.shuterstockimages.databinding.LaunchActivityBinding;
import app.easylink.shuterstockimages.di.component.MyActivityComponent;

import static app.easylink.shuterstockimages.BR.viewModel;

public class LaunchActivity extends BaseActivity<LaunchActivityBinding, LaunchViewModel> {
    public static final int ANIMATION_DURATION = 500;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        animateLogo();
    }

    private void animateLogo() {
        binding.logoContainer.animate()
                .scaleX(2f).scaleY(2f).setDuration(ANIMATION_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .withEndAction(this::animateAppName)
                .start();
    }

    private void animateAppName() {
        binding.appName.animate().alpha(1).setDuration(ANIMATION_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(() -> {
            if (viewModel != null) {
                viewModel.onAnimationDone();
            }
        })
                .start();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void onComponentCreated(MyActivityComponent component) {
        component.inject(this);
    }
}
