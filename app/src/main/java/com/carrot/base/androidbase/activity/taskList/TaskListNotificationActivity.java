package com.carrot.base.androidbase.activity.taskList;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MenuItem;
import android.view.View;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.activity.handle.NotificationActivity_;
import com.carrot.base.androidbase.adapter.TaskCardNotificationAdapter;
import com.carrot.base.androidbase.client.NotificationClient;
import com.carrot.base.androidbase.vo.result.NotificationResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
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
public class TaskListNotificationActivity extends TaskListBaseActivity {

    @RestService
    NotificationClient notificationClient;

    private TaskCardNotificationAdapter mAdapter;

    String[] cars;

    @AfterViews
    void initTabs(){

        afterInitTabs();

        tlTabs.setVisibility(View.GONE);

        notificationClient.setRestErrorHandler(ssErrorHandler);

    }




    void clearAdapter(){
        mAdapter.clear();
    }

    void newAdapter(){
        mAdapter = new TaskCardNotificationAdapter(new ArrayList<NotificationResult>());
    }


    @UiThread
    void uiInit(){
        mRecyclerView.setAdapter(mAdapter);

        final Context context = this;

        ((TaskCardNotificationAdapter) mAdapter).setOnItemClickListener(new TaskCardNotificationAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                TaskBaseVo taskBaseVo = new TaskBaseVo();
                taskBaseVo.id = mAdapter.getItem(position).iD;
//                TypeUtils.openItem(subTypeVo.name, context, taskBaseVo, ACTIVITY_REQUEST_CODE, status.equals("已完成") ? 2 : 2);

                NotificationActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .isFinished(status.equals("已完成") ? 2 : 2)
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

    List<NotificationResult> getResultListFromServer(){
        return notificationClient.get();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_list_new:

                if(subTypeVo.addFlag == true){
                    NotificationActivity_.intent(this)
                            .taskBaseVo(null)
                            .isFinished(0)
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
