package com.example.lance.threadtest.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lance.threadtest.R;
import com.example.lance.threadtest.util.Injector;
import com.example.lance.threadtest.util.OnClickBackListener;
import com.example.lance.threadtest.util.ViewInject;
import com.example.lance.threadtest.view.ActionbarView;

/**
 * author: admin
 * time: 2016/3/23 15:49
 * e-mail: lance.cao@anarry.com
 */
public class ThreadPoolActivity extends AppCompatActivity {

    @ViewInject(R.id.actionbar_view)
    private ActionbarView actionbarView;

    private Context mContext;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ThreadPoolCodeFragment threadPoolCodeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);
        Injector.initInjectedView(this);
        mContext = ThreadPoolActivity.this;
        initFragment();
        setListener();
    }

    private void initFragment() {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        if (threadPoolCodeFragment == null) {
            threadPoolCodeFragment = new ThreadPoolCodeFragment();
        }
        transaction.add(R.id.fragme_layout, threadPoolCodeFragment, "code");
        transaction.commitAllowingStateLoss();
    }

    private void setListener() {
        actionbarView.setOnClickBackListener(new OnClickBackListener() {
            @Override
            public void onClickBack() {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
