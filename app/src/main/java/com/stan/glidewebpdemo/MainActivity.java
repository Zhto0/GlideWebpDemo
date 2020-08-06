package com.stan.glidewebpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.stan.lib_imageloader.Constants;
import com.stan.lib_imageloader.GlideUtils;

public class MainActivity extends Activity implements View.OnClickListener {
    ImageView mImageView;
    //Drawable mGifDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.glide_static_webp).setOnClickListener(this);
        findViewById(R.id.glide_dynamic_webp).setOnClickListener(this);
        findViewById(R.id.glide_gif_webp).setOnClickListener(this);
        findViewById(R.id.glide_clear_disCache).setOnClickListener(this);
        findViewById(R.id.glide_clear).setOnClickListener(this);
        mImageView = findViewById(R.id.image_view);
        GlideUtils.setParams(this, mImageView);
        //mGifDrawable = getResources().getDrawable(R.drawable.normal);

        Glide.with(this)
                .load(Constants.dynamicWebpUrl)
                .apply(new RequestOptions()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop())
                .into(mImageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.glide_static_webp:
                //GlideUtils.loadStaticWebp(mStaicDrawable);
                GlideUtils.loadStaticWebp();
                break;
            case R.id.glide_dynamic_webp:
                //GlideUtils.loadDynamicWebp(mDynamicDrawable);
                GlideUtils.loadDynamicWebp();
                break;
            case R.id.glide_gif_webp:
                GlideUtils.loadGif(R.drawable.normal);
                break;
            case R.id.glide_clear_disCache:
                GlideUtils.clearDiskcache();
                break;
            case R.id.glide_clear:
                GlideUtils.clear();
                break;
        }
    }
}
