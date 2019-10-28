package app.easylink.shuterstockimages.appfeature.images;

import android.app.Activity;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import app.easylink.shuterstockimages.R;
import timber.log.Timber;

public class ShowImage extends AppCompatActivity {
    /**
     * On Click show image
     */
    ImageView myImage;
    String url = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_image);
        url = getIntent().getStringExtra("image_url");
        myImage = findViewById(R.id.imageView);
        Timber.d("mylink" + url);
        //picasso.load(url).placeholder(R.drawable.ic_launcher_background).into(myImage);
        Picasso.with(this).load(url).into(myImage);


    }
}