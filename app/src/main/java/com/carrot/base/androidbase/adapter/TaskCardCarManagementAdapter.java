package com.carrot.base.androidbase.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.vo.result.CarManagementResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 8/22/16.
 */
public class TaskCardCarManagementAdapter extends RecyclerView.Adapter<TaskCardCarManagementAdapter
        .DataObjectHolder> {

    private static String LOG_TAG = "TaskCardResolveRecordAdapter";
    private List<CarManagementResult> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {

        TextView name;
        TextView status;
        TextView creationTime;
        TextView endTime;

        TextView tv_unfinished;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_task_name);
            status = (TextView) itemView.findViewById(R.id.tv_area_name);

            creationTime = (TextView) itemView.findViewById(R.id.tv_creation_time);
            endTime = (TextView) itemView.findViewById(R.id.tv_end_time);
            tv_unfinished = (TextView) itemView.findViewById(R.id.tv_unfinished);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }


    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public TaskCardCarManagementAdapter(List<CarManagementResult> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row_fragment_task_list_car_management, parent, false);



        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        CarManagementResult taskBaseVo = mDataset.get(position);

        holder.name.setText(taskBaseVo.applyNum);
        holder.status.setText(taskBaseVo.applyStatus);

        if(taskBaseVo.createdTime != null && !taskBaseVo.createdTime.equals("")){
            holder.creationTime.setText("申请日期：" + taskBaseVo.createdTime.substring(0,10));
        }else{
            holder.creationTime.setText("");
        }
//
//        if(taskBaseVo.endTime != null && !taskBaseVo.endTime.equals("")){
//            holder.endTime.setText("结束日期：" + taskBaseVo.endTime.substring(0,10));
//        }else{
//            holder.endTime.setText("");
//        }
//
//        if(taskBaseVo.isHandled == 2){
//            holder.tv_unfinished.setText("未完成");
//            holder.tv_unfinished.setVisibility(View.VISIBLE);
//        }else{
//            holder.tv_unfinished.setVisibility(View.GONE);
//        }

    }



    public void addItem(CarManagementResult dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    // Clean all elements of the recycler
    public void clear() {
        mDataset.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<CarManagementResult> list) {
        if(list != null){
            mDataset.addAll(list);
        }else{
            mDataset.addAll(new ArrayList<CarManagementResult>());
        }
        notifyDataSetChanged();
    }

    public CarManagementResult getItem(int index){
        return mDataset.get(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
