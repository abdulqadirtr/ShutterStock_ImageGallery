package app.easylink.shuterstockimages.appfeature.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import app.easylink.shuterstockimages.R;
import app.easylink.shuterstockimages.databinding.ImageThumbBinding;
import app.easylink.shuterstockimages.shutterRemote.models.MyShutterImage;
import timber.log.Timber;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.BaseViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    Context context;

    private final OnImageClicked listener;
    private final Picasso picasso;
    private LayoutInflater inflater;

    private List<MyShutterImage> images = new ArrayList<>();
    private final MyShutterImage loader = new MyShutterImage();

    private AtomicBoolean isPreviewLoadInProgress = new AtomicBoolean(false);

    void showProgressBar(boolean show) {
        if (show) {
            images.add(loader);
            notifyItemInserted(images.size() - 1);
        } else {
            images.remove(loader);
            notifyItemRemoved(images.size());
        }
    }

    int getSpanSize(int gridSpan, int position) {
        return getItemViewType(position) == TYPE_FOOTER ? gridSpan : 1;
    }

    interface OnImageClicked {
        void onImageClicked(MyShutterImage image, ImageView imageView, Bitmap bitmap);

        void onImageFailed();
    }

    ImagesAdapter(OnImageClicked listener, Picasso picasso, @NonNull List<MyShutterImage> images) {
        this.listener = listener;
        this.picasso = picasso;
        this.images.addAll(images);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    public void setImages(List<MyShutterImage> images) {
        int prevSize = this.images.size();

        this.images.clear();
        this.images.addAll(images);

        notifyItemRangeInserted(prevSize, images.size() - 1);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new BaseViewHolder(inflater.inflate(R.layout.progress_item, parent, false));
        } else {
            return new ItemViewHolder(ImageThumbBinding.inflate(inflater, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (images.get(position) == loader) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        MyShutterImage image = images.get(position);

        if (getItemViewType(position) == TYPE_ITEM) {
            ImageThumbBinding binding = ((ItemViewHolder) holder).binding;
            picasso.load(image.getAssets().getLargeThumb().getUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(binding.imageThumb);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * Here passing context through
                     */
                    context = holder.itemView.getContext();
                    Intent intent = new Intent(context, ShowImage.class);
                    intent.putExtra("image_url", image.getAssets().getLargeThumb().getUrl());
                    context.startActivity(intent);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return images.size();
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder {

        BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class ItemViewHolder extends BaseViewHolder {

        private final ImageThumbBinding binding;

        ItemViewHolder(ImageThumbBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}