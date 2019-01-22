package com.happy.packets.helper;

import android.app.ProgressDialog;

import com.happy.libs.util.ActivityUtils;

public class DialogHelper {

    private static ProgressDialog progressDialog;

    public static void showLoading() {
        showLoading("加载中...");
    }

    public static void showLoading(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(ActivityUtils.getTopActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void missLoading() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

}
