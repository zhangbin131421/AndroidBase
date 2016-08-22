package com.carrot.base.androidbase.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.adapter.MainCardAdapter;
import com.carrot.base.androidbase.utils.TestUtils;
import com.carrot.base.androidbase.vo.TypeVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

/**
 * Created by victor on 8/22/16.
 */

@EActivity(R.layout.active_type_2)
@OptionsMenu(R.menu.type_2)
public class Type2Activity extends AppCompatActivity {



    @ViewById(R.id.rv_type_2)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "Type2Activity";

    @Extra
    TypeVo typeVo;

    @ViewById(R.id.tb_type_2_tool_bar)
    Toolbar toolbar;

    @AfterViews
    void bindAdapter(){


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        mRecyclerView = (RecyclerView) findViewById(R.id.rv_type_2);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MainCardAdapter(typeVo.getSubTypes());
        mRecyclerView.setAdapter(mAdapter);


    }
    @Override
    protected void onResume() {
        super.onResume();

        setTitle(typeVo.getName());
        ((MainCardAdapter) mAdapter).setOnItemClickListener(new MainCardAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

}
