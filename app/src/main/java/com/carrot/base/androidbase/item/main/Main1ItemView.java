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
@EViewGroup(R.layout.item_main)
public class Main1ItemView extends LinearLayout{

    @ViewById(R.id.et_item_main_name)
    TextView tvName;

    @ViewById(R.id.et_item_main_newFlag)
    TextView tvNewFlag;

    public Main1ItemView(Context context){
        super(context);
    }

    public void bind(TypeVo type){
        tvName.setText(type.getName());
//        tvNewFlag.isShown() = t
    }
}
