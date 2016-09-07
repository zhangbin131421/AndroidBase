package com.carrot.base.androidbase.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.MultiValueMap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import id.zelory.compressor.Compressor;

/**
 * Created by victor on 9/7/16.
 */
public class FileUtils {

    public static void addImageToData(MultiValueMap<String, Object> data, String key, List<PhotoInfo> picList, Context context){
        try {
            for(int i = 0; i < picList.size(); i++){
                PhotoInfo pi = picList.get(i);
                File file = new File(pi.getPhotoPath());

                File compressedImageFile = Compressor.getDefault(context).compressToFile(file);


                final String filename = file.getName();


                byte[] bytes = new byte[(int) compressedImageFile.length()];


                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(compressedImageFile));


                buf.read(bytes, 0, bytes.length);
                buf.close();


                ByteArrayResource contentsAsResource = new ByteArrayResource(bytes){
                    @Override
                    public String getFilename(){
                        return filename;
                    }
                };;


                data.add(key, contentsAsResource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
