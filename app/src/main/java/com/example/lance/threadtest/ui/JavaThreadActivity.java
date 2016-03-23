package com.example.lance.threadtest.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lance.threadtest.R;
import com.example.lance.threadtest.util.DialogUtil;
import com.example.lance.threadtest.util.Injector;
import com.example.lance.threadtest.util.OnClickBackListener;
import com.example.lance.threadtest.util.ViewInject;
import com.example.lance.threadtest.view.ActionbarView;

/**
 * author: admin
 * time: 2016/3/23 11:55
 * e-mail: lance.cao@anarry.com
 */
public class JavaThreadActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.actionbar_view)
    private ActionbarView actionbarView;
    @ViewInject(R.id.bt_volatile)
    private Button btVolatile;
    @ViewInject(R.id.bt_final)
    private Button btFinal;
    @ViewInject(R.id.bt_synchronized)
    private Button btSynchronized;
    @ViewInject(R.id.bt_lock)
    private Button btLock;
    @ViewInject(R.id.bt_condition)
    private Button btCondition;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_thread);
        Injector.initInjectedView(this);
        mContext = JavaThreadActivity.this;
        setListener();
    }

    private void setListener() {
        btVolatile.setOnClickListener(this);
        btFinal.setOnClickListener(this);
        btSynchronized.setOnClickListener(this);
        btLock.setOnClickListener(this);
        btCondition.setOnClickListener(this);
        actionbarView.setOnClickBackListener(new OnClickBackListener() {
            @Override
            public void onClickBack() {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_volatile:
                DialogUtil.showDialog(mContext,
                        getResources().getString(R.string.volatile_text), "Ok", "Cancel");
                break;
            case R.id.bt_final:
                DialogUtil.showDialog(mContext,
                        getResources().getString(R.string.final_text), "Ok", "Cancel");
                break;
            case R.id.bt_synchronized:
                DialogUtil.showDialog(mContext,
                        getResources().getString(R.string.synchronized_text), "Ok", "Cancel");
                break;
            case R.id.bt_lock:
                DialogUtil.showDialog(mContext,
                        getResources().getString(R.string.lock_text), "Ok", "Cancel");
                break;
            case R.id.bt_condition:
                DialogUtil.showDialog(mContext,
                        getResources().getString(R.string.condition_text), "Ok", "Cancel");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
