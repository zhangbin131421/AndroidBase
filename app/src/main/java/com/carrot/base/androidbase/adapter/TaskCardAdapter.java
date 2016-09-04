package com.carrot.base.androidbase.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import java.util.List;

/**
 * Created by victor on 8/22/16.
 */
public class TaskCardAdapter extends RecyclerView.Adapter<TaskCardAdapter
        .DataObjectHolder> {

    private static String LOG_TAG = "TaskCardAdapter";
    private List<TaskBaseVo> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {

        TextView name;
        TextView creationTime;
        TextView status;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_cvr_fragment_task_list_task_name);
            creationTime = (TextView) itemView.findViewById(R.id.tv_cvr_fragment_task_list_task_time);
            status = (TextView) itemView.findViewById(R.id.tv_cvr_fragment_task_list_status);

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

    public TaskCardAdapter(List<TaskBaseVo> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row_fragment_task_list, parent, false);


        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.name.setText(mDataset.get(position).taskNum);
        holder.creationTime.setText(mDataset.get(position).assignmentTime);
//        holder.status.setText(mDataset.get(position).taskStatus);
    }

    public void addItem(TaskBaseVo dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    // Clean all elements of the recycler
    public void clear() {
        mDataset.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<TaskBaseVo> list) {
        mDataset.addAll(list);
        notifyDataSetChanged();
    }

    public TaskBaseVo getItem(int index){
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
