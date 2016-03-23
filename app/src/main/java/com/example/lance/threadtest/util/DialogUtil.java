package com.example.lance.threadtest.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.lance.threadtest.R;

/**
 * author: admin
 * time: 2016/3/23 14:09
 * e-mail: lance.cao@anarry.com
 */
public class DialogUtil {

    public static void showDialog(Context context, String str, String positive, String negative) {
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null);
        TextView tvInfo = (TextView) layout.findViewById(R.id.tv_info);
        tvInfo.setText(str);
        new AlertDialog.Builder(context)
                .setView(layout)
                .setCancelable(false)
                .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}
