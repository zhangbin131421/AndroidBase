package com.carrot.base.androidbase;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.carrot.base.androidbase.adapter.MainListAdapter;
import com.carrot.base.androidbase.preferences.MyPrefs_;
import com.carrot.base.androidbase.vo.TypeVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import static android.widget.Toast.makeText;

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


    @OptionsItem(R.id.menu_main_setting)
    void myMethod() {
        System.out.println("----setting-----");
    }

//    @ItemClick
//    void personListItemClicked(TypeVo type) {
//        makeText(this, type.getName(), Toast.LENGTH_SHORT).show();
//    }
}
