package com.carrot.base.androidbase;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.carrot.base.androidbase.activity.SettingActivity_;
import com.carrot.base.androidbase.activity.TaskListActivity_;
import com.carrot.base.androidbase.activity.Type2Activity;
import com.carrot.base.androidbase.activity.Type2Activity_;
import com.carrot.base.androidbase.adapter.MainCardAdapter;
import com.carrot.base.androidbase.image.UILImageLoader;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.TestUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.TypeVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "MainActivity";

    @Pref
    UserPrefs_ userPrefs;


    @ViewById(R.id.tb_main_tool_bar)
    Toolbar toolbar;



    @AfterViews
    void bindAdapter(){


        setSupportActionBar(toolbar);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MainCardAdapter(TypeUtils.getAllItems(getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);

        initGrally();


    }



    private void initGrally() {
        //设置主题
//ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .build();
//配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

//配置imageloader
        ImageLoader imageLoader = new UILImageLoader();


        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageLoader, theme)
//                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MainCardAdapter) mAdapter).setOnItemClickListener(new MainCardAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                TypeVo typeVo = ((MainCardAdapter) mAdapter).getItem(position);

                if(typeVo.getSubTypes() == null || typeVo.getSubTypes().size() == 0){
                    TaskListActivity_.intent(getApplicationContext())
                            .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .typeVo(null)
                            .subTypeVo(typeVo)
                            .start();

                }else{
                    Type2Activity_.intent(getApplicationContext())
                            .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .typeVo(typeVo).start();
                }


            }
        });
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();

        if(userPrefs.name().get().equals("")){
            LoginActivity_.intent(MainActivity.this).start();

        }
    }

    @OptionsItem(R.id.menu_main_setting)
    void openSetting(){
        SettingActivity_.intent(MainActivity.this).start();
    }

}
