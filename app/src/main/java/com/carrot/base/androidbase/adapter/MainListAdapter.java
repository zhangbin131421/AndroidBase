package com.carrot.base.androidbase.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;

import com.carrot.base.androidbase.item.main.Main1ItemView;
import com.carrot.base.androidbase.item.main.Main1ItemView_;
import com.carrot.base.androidbase.item.main.Main2ItemView;
import com.carrot.base.androidbase.item.main.Main2ItemView_;
import com.carrot.base.androidbase.utils.TestUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.TypeVo;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
@EBean
public class MainListAdapter extends BaseExpandableListAdapter{

    List<TypeVo> types;


    @RootContext
    Context context;

    @AfterInject
    void initAdapter(){
        types = TypeUtils.getAllItems(context);
    }


    @Override
    public int getGroupCount() {
        return types.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if(types.get(i).getSubTypes() == null || types.get(i).getSubTypes().size() == 0){
            return 0;
        }
        return types.get(i).getSubTypes().size();
    }

    @Override
    public Object getGroup(int i) {
        return types.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        if(types.get(i).getSubTypes() == null || types.get(i).getSubTypes().size() == 0){
            return null;
        }

        return types.get(i).getSubTypes().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        Main1ItemView main1ItemView;
        if(view == null){
            main1ItemView = Main1ItemView_.build(context);
        }else{
            main1ItemView = (Main1ItemView) view;
        }

        main1ItemView.bind((TypeVo) getGroup(i));
        return main1ItemView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        Main2ItemView main2ItemView;

        if(view == null){
            main2ItemView = Main2ItemView_.build(context);
        }else{
            main2ItemView = (Main2ItemView) view;
        }

        main2ItemView.bind(types.get(i).getSubTypes().get(i1));
        return main2ItemView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
