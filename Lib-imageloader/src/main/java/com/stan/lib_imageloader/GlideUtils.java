package com.stan.lib_imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Stan on 2020-06-30.
 */
public class GlideUtils {
    private static final String TAG = "glidedebug";
    static Context mContext;
    static ImageView mImageView;

//    public static void init(Context context) {
//        ResourceDecoder decoder = new WebpResourceDecoder(context);
//        Glide.get(context).getRegistry().append(InputStream.class, Drawable.class, decoder);
//    }

    public static void setParams(Context context, ImageView view) {
        mContext = context;
        mImageView = view;
    }

    public static void loadStaticWebp(Drawable drawable) {
        Glide.with(mContext).load(drawable).into(mImageView);
    }

    public static void loadStaticWebp() {
        Glide.with(mContext)
                .load(Constants.staticWebpUrl2)
                .into(mImageView);
    }

    public static void loadDynamicWebp(Drawable drawable) {
        Glide.with(mContext).load(drawable).into(mImageView);
    }

    public static void loadDynamicWebp() {
        Glide.with(mContext)
                .load(Constants.dynamicWebpUrl)
                .apply(new RequestOptions()
                        .skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(mImageView);
    }

    public static void clearDiskcache() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(mContext).clearDiskCache();
            }
        }).start();

    }

    public static void clear() {
        Glide.with(mContext).clear(mImageView);
    }


    public static void loadGif(int id) {
        Glide.with(mContext).load(id).into(mImageView);
    }

    public static void loadPic() {
        Glide.with(mContext).load(Constants.ipc).into(mImageView);
    }

    public static void getCallers(final int depth) {
        final StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < depth; i++) {
            String stack = getCaller(callStack, i);
            if (TextUtils.isEmpty(stack)) {
                continue;
            }
            sb.append(stack).append("\n");
        }
        Log.i(TAG, sb.toString());
    }

    private static String getCaller(StackTraceElement callStack[], int depth) {
        if (4 + depth >= callStack.length) {
            return "";
        }
        StackTraceElement caller = callStack[4 + depth];
        return caller.getClassName() + "." + caller.getMethodName() + ":" + caller.getLineNumber();
    }

}

