package com.happy.libs.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

public interface IBaseView extends View.OnClickListener{

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    void initData(@NonNull final Bundle bundle);

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    int bindLayout();


    /**
     * 初始化 view
     */
    void initView(final Bundle savedInstanceState, final View contentView);

    /**
     * 业务操作
     */
    void doBusiness();

    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    void onWidgetClick(final View view);
}
