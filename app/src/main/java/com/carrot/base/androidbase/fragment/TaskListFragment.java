package com.carrot.base.androidbase.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.activity.TaskListActivity;
import com.carrot.base.androidbase.activity.TaskListActivity_;
import com.carrot.base.androidbase.adapter.TaskCardAdapter;
import com.carrot.base.androidbase.client.CoreMeterTestClient;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.constant.ResultCodeConstant;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.TypeVo;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
@EFragment(R.layout.fragment_task_list)
public class TaskListFragment extends Fragment {

    private static final int ACTIVITY_REQUEST_CODE = 1002;

//    @FragmentArg("status")
//    String status;
//
//

//
//    @FragmentArg
//    TypeVo typeVo;
//
//
//    @FragmentArg
//    TypeVo subTypeVo;



//    ProgressDialog progress;


    private static String LOG_TAG = "TaskListFragment";

    @AfterViews
    @Background
    void bindAdapter(){


    }








//    @OnActivityResult(ACTIVITY_REQUEST_CODE)
//    protected void onActivity3Result(int resultCode){
////        Toast.makeText(getActivity(), "asdfasdf", Toast.LENGTH_SHORT).show();
//        Log.i("ssLog", "save ");
//
//
////        if(resultCode == ResultCodeConstant.RESULT_CODE_REFRESH){
////            refreshItems();
////        }
//    }



//    @UiThread
//    void showLoading(){
//
////        if(progress == null){
////            progress = new ProgressDialog(getActivity());
////        }
////
////        progress.setTitle("Loading");
////        progress.show();
//
//    }
//    @UiThread
//    void alert(String message){
//        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
//    }

}
