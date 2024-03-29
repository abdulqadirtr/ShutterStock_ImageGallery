package app.easylink.shuterstockimages.appfeature.images;

import android.app.ActivityOptions;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import app.easylink.shuterstockimages.R;
import app.easylink.shuterstockimages.appfeature.base.BaseActivity;
import app.easylink.shuterstockimages.databinding.ImagesActivityBinding;
import app.easylink.shuterstockimages.di.component.MyActivityComponent;
import app.easylink.shuterstockimages.shutterRemote.models.MyShutterImage;
import app.easylink.shuterstockimages.ui.GridSpacingItemDecoration;
import app.easylink.shuterstockimages.utils.rx.BgOperation;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

import static app.easylink.shuterstockimages.AppConstants.PER_PAGE_SIZE;

public class ImagesActivity extends BaseActivity<ImagesActivityBinding, ImagesViewModel>
        implements ImagesAdapter.OnImageClicked {

    private GridLayoutManager layoutManager;
    private ImagesAdapter adapter;

    @Inject
    Picasso picasso;
    private int gridSpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        gridSpan = getResources().getInteger(R.integer.grid_span);

        adapter = new ImagesAdapter(this, picasso, viewModel.getImages());

        layoutManager = new GridLayoutManager(this, gridSpan);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getSpanSize(gridSpan, position);
            }
        });
        binding.imagesList.setHasFixedSize(true);
        binding.imagesList.setLayoutManager(layoutManager);

        int cellPadding = getResources().getDimensionPixelSize(R.dimen.images_cell_spacing);
        binding.imagesList.addItemDecoration(
                new GridSpacingItemDecoration(gridSpan, cellPadding, true));

        binding.imagesList.setAdapter(adapter);

        setupInfiniteScrollListener(binding.imagesList);
    }

    private void setupInfiniteScrollListener(RecyclerView imagesList) {
        imagesList.addOnScrollListener(new InfiniteScrollListener(PER_PAGE_SIZE, layoutManager) {
            @Override
            public void onScrolledToEnd(final int firstVisibleItemPosition) {

                Single<Boolean> call = viewModel.fetchNextPage();

                if (call == null) {
                    Timber.d("onScrolledToEnd: Loading in progress. Do Nothing");
                    return;
                }

                adapter.showProgressBar(true);
                Disposable disposable = call.compose(new BgOperation<>())
                        .doAfterTerminate(() -> adapter.showProgressBar(false))
                        .subscribe(success -> onFetchDataCompleted(success, firstVisibleItemPosition));

                disposables.add(disposable);
            }
        });
    }

    private void onFetchDataCompleted(boolean success, int firstVisibleItemPosition) {
        if (success) {
            adapter.setImages(viewModel.getImages());
            binding.imagesList.scrollToPosition(firstVisibleItemPosition + gridSpan);
        } else {
            ui.showSnackBar(R.string.failed_to_fetch_images);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_images;
    }

    @Override
    protected void onComponentCreated(MyActivityComponent component) {
        component.inject(this);
    }

    /**
     * Code For imageClicked
     *
     * @param image
     * @param imageView
     * @param bitmap
     */
    @Override
    public void onImageClicked(MyShutterImage image, ImageView imageView, Bitmap bitmap) {
        Timber.d("onImageClicked");

        ActivityOptions options = ActivityOptions.makeThumbnailScaleUpAnimation(imageView, bitmap, 0, 0);

        // startActivity(ImageDetailActivity.getImageIntent(this, image), options.toBundle());
    }

    @Override
    public void onImageFailed() {
        Timber.e("onImageFailed");
        ui.showSnackBar(R.string.failed_to_fetch_image);
    }
}