package com.carrot.base.androidbase.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.vo.result.DistributionNetworkEngineeringResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 8/22/16.
 */
public class TaskCardDistributionNetworkEngineeringAdapter extends RecyclerView.Adapter<TaskCardDistributionNetworkEngineeringAdapter
        .DataObjectHolder> {

    private static String LOG_TAG = "TaskCardResolveRecordAdapter";
    private List<DistributionNetworkEngineeringResult> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {

        TextView name;
        TextView creationTime;
        TextView endTime;

        TextView tv_unfinished;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_task_name);

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

    public TaskCardDistributionNetworkEngineeringAdapter(List<DistributionNetworkEngineeringResult> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row_fragment_task_list_distribution_network_engineering, parent, false);



        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        DistributionNetworkEngineeringResult taskBaseVo = mDataset.get(position);

        holder.name.setText(taskBaseVo.taskNum);

        if(taskBaseVo.assignmentTime != null && !taskBaseVo.assignmentTime.equals("")){
            holder.creationTime.setText("指派日期：" + taskBaseVo.assignmentTime.substring(0,10));
        }else{
            holder.creationTime.setText("");
        }

        if(taskBaseVo.endHandleTime != null && !taskBaseVo.endHandleTime.equals("")){
            holder.endTime.setText("结束日期：" + taskBaseVo.endHandleTime.substring(0,10));
        }else{
            holder.endTime.setText("");
        }

        if(taskBaseVo.isHandled == 2){
            holder.tv_unfinished.setText("未完成");
            holder.tv_unfinished.setVisibility(View.VISIBLE);
        }else{
            holder.tv_unfinished.setVisibility(View.GONE);
        }

    }



    public void addItem(DistributionNetworkEngineeringResult dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    // Clean all elements of the recycler
    public void clear() {
        mDataset.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<DistributionNetworkEngineeringResult> list) {
        if(list != null){
            mDataset.addAll(list);
        }else{
            mDataset.addAll(new ArrayList<DistributionNetworkEngineeringResult>());
        }
        notifyDataSetChanged();
    }

    public DistributionNetworkEngineeringResult getItem(int index){
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
