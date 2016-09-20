package com.carrot.base.androidbase.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.carrot.base.androidbase.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by victor on 9/3/16.
 */
public class ImageUtils {


    public static final String IMAGE_TYPE = "image/*";

    /**
     * 获取图片的View，包括图片和删除按钮
     * @param url
     * @param context
     * @param resources
     * @return
     */
    public static View getViewFromURL(String url,Context context, Resources resources){

        if(url == null || url.trim().equals("") || context == null || resources == null){
            return null;
        }

        View view = null;

        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
            view = ImageUtils.getImageViewForForm(context, resources, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return view;
    }

    public static View getImageViewForForm(Context context, Resources resources, Bitmap bitmap){

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.view_image, null);


        ImageView imageView = (ImageView) promptView.findViewById(R.id.image);

        ImageView deleteButton = (ImageView) promptView.findViewById(R.id.btnDelete);

        imageView.setImageBitmap(bitmap);


//        ImageView imageView = new ImageView(context);
//
//
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//
//        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, resources.getDisplayMetrics());
//
//        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, resources.getDisplayMetrics());
//
//        imageView.setLayoutParams(new GridView.LayoutParams(width, width));
//        imageView.setPadding(padding, padding, padding, padding);
//
//        imageView.setImageBitmap(bitmap);

        return promptView;
    }
}
