package com.carrot.base.androidbase;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.carrot.base.androidbase.activity.SettingActivity_;
import com.carrot.base.androidbase.activity.TaskListActivity_;
import com.carrot.base.androidbase.adapter.MainCardAdapter;
import com.carrot.base.androidbase.adapter.MainListAdapter;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.TestUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "MainActivity";

    @Pref
    UserPrefs_ userPrefs;

//    @ViewById(R.id.elv_main_types)
//    ExpandableListView elvTypes;

    @ViewById(R.id.tb_main_tool_bar)
    Toolbar toolbar;

    @Bean
    MainListAdapter mainListAdapter;

    @AfterViews
    void bindAdapter(){

        Toast.makeText(this, "main bind adapter", Toast.LENGTH_SHORT).show();

        setSupportActionBar(toolbar);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MainCardAdapter(TestUtils.getAllItems());
        mRecyclerView.setAdapter(mAdapter);

//        elvTypes.setAdapter(mainListAdapter);
//
//        elvTypes.setOnChildClickListener(new OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//                TaskListActivity_.intent(MainActivity.this).start();
//                return false;
//
//            }
//
//        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        ((MainCardAdapter) mAdapter).setOnItemClickListener(new MainCardAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
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
