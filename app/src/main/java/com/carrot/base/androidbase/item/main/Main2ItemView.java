package com.carrot.base.androidbase.item.main;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.vo.TypeVo;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by victor on 8/12/16.
 */
@EViewGroup(R.layout.item_main_2)
public class Main2ItemView extends LinearLayout{


    @ViewById(R.id.et_item_main2_name)
    TextView tvName;

    @ViewById(R.id.et_item_main2_newFlag)
    TextView tvNewFlag;


    public Main2ItemView(Context context){
        super(context);
    }

    public void bind(TypeVo type){
        tvName.setText(type.getName());
//        tvNewFlag.isShown() = t
    }
}
