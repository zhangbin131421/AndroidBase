package com.carrot.base.androidbase.activity.handle;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import com.carrot.base.androidbase.R;

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_line_broken_management)
@OptionsMenu(R.menu.task_item)
public class LineBrokenManagementActivity extends AppCompatActivity{


    @ViewById(R.id.tb_line_borken_management_tool_bar)
    Toolbar toolbar;

    @AfterViews
    void bindAdapter(){


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_item_save:
                Toast.makeText(LineBrokenManagementActivity.this, "SAVE!", Toast.LENGTH_SHORT).show();

                return true;
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
