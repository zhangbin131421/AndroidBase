package com.carrot.base.androidbase.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.adapter.Type2Adapter;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.client.ResolveRecordClient;
import com.carrot.base.androidbase.error.SSErrorWithoutDialogHandler;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.vo.TypeVo;
import com.carrot.base.androidbase.vo.result.CountResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by victor on 8/22/16.
 */

@EActivity(R.layout.activity_image_preview)
@OptionsMenu(R.menu.type_2)
public class ImagePreviewActivity extends AppCompatActivity {

    @ViewById(R.id.imgView)
    ImageView imageView;

    @ViewById(R.id.tb_tool_bar)
    Toolbar toolbar;


    @Extra
    String url;

    @AfterViews
    void bindAdapter(){

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("图片预览");

        if(url != null){

            BitmapFactory.Options opts=new BitmapFactory.Options();
            opts.inDither=false;                     //Disable Dithering mode
            opts.inPurgeable=true;                   //Tell to gc that whether it needs free memory, the Bitmap can be cleared
            opts.inInputShareable=true;              //Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future
            opts.inTempStorage=new byte[32 * 1024];


            File file = new File(url);

            if(file.exists()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


//                BitmapFactory.decodeStream(fis, null, opts);
                Bitmap bitmap = BitmapFactory.decodeStream(fis, null, opts);//BitmapFactory.decodeFile(file.getAbsolutePath());


                imageView.setImageBitmap(bitmap);
            }else{

                getImage(url);
            }
        }

    }

    @Background
    void getImage(String url){
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
            setImage(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @UiThread
    void setImage(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
