package com.carrot.base.androidbase.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.activity.handle.CoreMeterTestActivity_;
import com.carrot.base.androidbase.adapter.TaskListFragmentAdapter;
import com.carrot.base.androidbase.constant.ResultCodeConstant;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.TypeVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

/**
 * Created by victor on 8/12/16.
 */
@EActivity(R.layout.activity_task_list)
@OptionsMenu(R.menu.task_list)
public class TaskListActivity extends AppCompatActivity {

    private static final int ACTIVITY_REQUEST_CODE = 1001;


    @ViewById(R.id.tl_task_list_tabs)
    TabLayout tlTabs;

    @ViewById(R.id.vp_task_list_pager)
    ViewPager vpPager;

    @ViewById(R.id.tb_task_list_tool_bar)
    Toolbar toolbar;

    @Extra
    TypeVo typeVo;

    @Extra
    TypeVo subTypeVo;


    TaskListFragmentAdapter taskListFragmentAdapter;

    @AfterViews
    void initTabs(){


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        taskListFragmentAdapter = new TaskListFragmentAdapter(getSupportFragmentManager(),
                this, typeVo, subTypeVo);
        vpPager.setAdapter(taskListFragmentAdapter);

        tlTabs.setupWithViewPager(vpPager);

        setTitle(subTypeVo.getName() + "任务列表");

    }

//    @OnActivityResult(ACTIVITY_REQUEST_CODE)
//    protected void onActivity3Result(int resultCode){
//        Log.i("ssLog", "save ");
//        super.onActivityResult(ACTIVITY_REQUEST_CODE, resultCode, null);
//        if(resultCode == ResultCodeConstant.RESULT_CODE_REFRESH){
//            taskListFragmentAdapter.refreshData();
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("ssLog", "save ");
        super.onActivityReenter(resultCode, data);
        if(resultCode == ResultCodeConstant.RESULT_CODE_REFRESH){
            taskListFragmentAdapter.refreshData();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_list_new:

                TypeUtils.openItem(subTypeVo.getName(), this, null, ACTIVITY_REQUEST_CODE);

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
