package com.happy.packets.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.happy.packets.R;

public class FilterView extends FrameLayout {
    private EditText editText;

    public FilterView(@NonNull Context context) {
        this(context, null);
    }

    public FilterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_filter, this);
        editText = findViewById(R.id.et_filter);
    }

    public String getContents() {
        return editText.getText().toString();
    }
}
