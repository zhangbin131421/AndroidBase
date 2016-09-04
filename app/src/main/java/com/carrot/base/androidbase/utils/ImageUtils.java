package com.carrot.base.androidbase.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.TypedValue;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by victor on 9/3/16.
 */
public class ImageUtils {


    public static final String IMAGE_TYPE = "image/*";

    public static ImageView getImageViewFromURL(String url,Context context, Resources resources){

        if(url == null || url.trim().equals("") || context == null || resources == null){
            return null;
        }

        ImageView imageView = null;

        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
            imageView = ImageUtils.getImageViewForForm(context, resources, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return imageView;
    }

    public static ImageView getImageViewForForm(Context context, Resources resources, Bitmap bitmap){

        ImageView imageView = new ImageView(context);


        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, resources.getDisplayMetrics());

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, resources.getDisplayMetrics());

        imageView.setLayoutParams(new GridView.LayoutParams(width, width));
        imageView.setPadding(padding, padding, padding, padding);

        imageView.setImageBitmap(bitmap);

        return imageView;
    }
}
