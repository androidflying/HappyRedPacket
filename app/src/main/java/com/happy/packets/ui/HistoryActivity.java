package com.happy.packets.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.happy.libs.base.BaseActivity;
import com.happy.libs.util.ActivityUtils;
import com.happy.packets.R;
import com.happy.packets.adapter.HistoryAdapter;
import com.happy.packets.entity.RedPackage;
import com.happy.packets.helper.DialogHelper;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.List;

public class HistoryActivity extends BaseActivity {
    private RecyclerView mRecyclerView;

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_history;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        findViewById(R.id.btn_close).setOnClickListener(this);

        mRecyclerView = findViewById(R.id.rv_Red_History);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void doBusiness() {
        DialogHelper.showLoading();
        LitePal.findAllAsync(RedPackage.class).listen(new FindMultiCallback<RedPackage>() {
            @Override
            public void onFinish(List<RedPackage> list) {
                DialogHelper.missLoading();
                HistoryAdapter adapter = new HistoryAdapter(list);
                mRecyclerView.setAdapter(adapter);
            }
        });

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                ActivityUtils.finishActivity(HistoryActivity.class, 0, R.anim.activity_close);
                break;
            default:
        }
    }
}
