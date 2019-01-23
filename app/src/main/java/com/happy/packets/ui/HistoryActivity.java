package com.happy.packets.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.happy.libs.base.BaseActivity;
import com.happy.libs.util.ActivityUtils;
import com.happy.packets.R;
import com.happy.packets.adapter.HistoryAdapter;
import com.happy.packets.entity.RedPackage;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.text.DecimalFormat;
import java.util.List;

public class HistoryActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private TextView tv_total_money;
    private ImageView iv_no_result;
    float total_money = 0.00f;

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
        tv_total_money = findViewById(R.id.tv_total_money);
        iv_no_result = findViewById(R.id.iv_no_result);
        mRecyclerView = findViewById(R.id.rv_Red_History);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void doBusiness() {
        showLoading();
        total_money =  LitePal.sum(RedPackage.class,"money",float.class);
        tv_total_money.setText("共抢得 " + new DecimalFormat("#0.00").format(total_money) + " 元");
        LitePal.findAllAsync(RedPackage.class).listen(new FindMultiCallback<RedPackage>() {
            @Override
            public void onFinish(List<RedPackage> list) {
                missLoading();
                if (list.isEmpty()){
                    mRecyclerView.setVisibility(View.GONE);
                    iv_no_result.setVisibility(View.VISIBLE);
                }else {
                    mRecyclerView.setVisibility(View.VISIBLE);
                    iv_no_result.setVisibility(View.GONE);
                    HistoryAdapter adapter = new HistoryAdapter(list);
                    mRecyclerView.setAdapter(adapter);
                }
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
