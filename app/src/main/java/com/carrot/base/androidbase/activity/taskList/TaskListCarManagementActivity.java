package com.carrot.base.androidbase.activity.taskList;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.activity.handle.CarManagementActivity_;
import com.carrot.base.androidbase.adapter.TaskCardCarManagementAdapter;
import com.carrot.base.androidbase.adapter.TaskListFragmentAdapter;
import com.carrot.base.androidbase.client.CarManagementClient;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.CarManagementResult;
import com.carrot.base.androidbase.vo.result.CarResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by victor on 8/12/16.
 */
@EActivity(R.layout.activity_task_list)
@OptionsMenu(R.menu.task_list_add)
public class TaskListCarManagementActivity extends TaskListBaseActivity {

    @RestService
    CarManagementClient carManagementClient;

    private TaskCardCarManagementAdapter mAdapter;

    String[] cars;

    @AfterViews
    void initTabs(){

        afterInitTabs();

        tlTabs.setVisibility(View.GONE);

        carManagementClient.setRestErrorHandler(ssErrorHandler);

        getCars();
    }


    @Background
    void getCars(){
        if(cars == null){

            List<CarResult> carList = carManagementClient.getAllCar();
            cars = new String[carList.size()];
            for(int i = 0; i < carList.size(); i++){
                CarResult car = carList.get(i);

                cars[i] = car.license;
            }
        }
    }


    void clearAdapter(){
        mAdapter.clear();
    }

    void newAdapter(){
        mAdapter = new TaskCardCarManagementAdapter(new ArrayList<CarManagementResult>());
    }


    @UiThread
    void uiInit(){
        mRecyclerView.setAdapter(mAdapter);

        final Context context = this;

        ((TaskCardCarManagementAdapter) mAdapter).setOnItemClickListener(new TaskCardCarManagementAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                TaskBaseVo taskBaseVo = new TaskBaseVo();
                taskBaseVo.id = mAdapter.getItem(position).iD;
//                TypeUtils.openItem(subTypeVo.name, context, taskBaseVo, ACTIVITY_REQUEST_CODE, status.equals("已完成") ? 2 : 2);

                CarManagementActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .isFinished(status.equals("已完成") ? 2 : 2).cars(cars)
                        .startForResult(ACTIVITY_REQUEST_CODE)
                ;
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

        refreshItems();
    }

    void addAllAdapter(List list){
        mAdapter.addAll(list);
    }

    List<CarManagementResult> getResultListFromServer(){
        return carManagementClient.getAll();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_list_new:

                if(subTypeVo.addFlag == true){
                    CarManagementActivity_.intent(this)
                            .taskBaseVo(null)
                            .isFinished(0).cars(cars)
                            .startForResult(ACTIVITY_REQUEST_CODE)
                    ;
                }else{
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("该任务不允许APP新增")
                            .setConfirmText("确定")
                            .show();
                }


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
