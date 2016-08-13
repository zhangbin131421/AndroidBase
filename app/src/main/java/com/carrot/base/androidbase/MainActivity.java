package com.carrot.base.androidbase;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.carrot.base.androidbase.activity.TaskListActivity_;
import com.carrot.base.androidbase.adapter.MainListAdapter;
import com.carrot.base.androidbase.preferences.MyPrefs_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity {


    @Pref
    MyPrefs_ myPrefs;

    @ViewById(R.id.elv_main_types)
    ExpandableListView elvTypes;

    @Bean
    MainListAdapter mainListAdapter;

    @AfterViews
    void bindAdapter(){
        elvTypes.setAdapter(mainListAdapter);

        elvTypes.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                TaskListActivity_.intent(MainActivity.this).start();
                return false;

            }

        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        if(myPrefs.currentUsername().get().equals("")){
            LoginActivity_.intent(MainActivity.this).start();

        }
    }



}
