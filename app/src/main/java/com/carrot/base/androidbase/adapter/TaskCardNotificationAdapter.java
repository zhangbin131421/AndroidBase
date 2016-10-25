package com.carrot.base.androidbase.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.vo.result.NotificationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 8/22/16.
 */
public class TaskCardNotificationAdapter extends RecyclerView.Adapter<TaskCardNotificationAdapter
        .DataObjectHolder> {

    private static String LOG_TAG = "TaskCardResolveRecordAdapter";
    private List<NotificationResult> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {

        TextView name;


        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_task_name);

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

    public TaskCardNotificationAdapter(List<NotificationResult> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row_fragment_task_list_notification, parent, false);



        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        NotificationResult taskBaseVo = mDataset.get(position);

        holder.name.setText(taskBaseVo.notificationTitle);


    }



    public void addItem(NotificationResult dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    // Clean all elements of the recycler
    public void clear() {
        mDataset.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<NotificationResult> list) {
        if(list != null){
            mDataset.addAll(list);
        }else{
            mDataset.addAll(new ArrayList<NotificationResult>());
        }
        notifyDataSetChanged();
    }

    public NotificationResult getItem(int index){
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
