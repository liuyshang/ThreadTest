package com.example.lance.threadtest.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lance.threadtest.R;
import com.example.lance.threadtest.util.Injector;
import com.example.lance.threadtest.util.OnClickBackListener;
import com.example.lance.threadtest.util.OnLongClickBackListener;
import com.example.lance.threadtest.util.ViewInject;
import com.example.lance.threadtest.view.ActionbarView;

/**
 * author: admin
 * time: 2016/3/23 15:49
 * e-mail: lance.cao@anarry.com
 */
public class ThreadPoolActivity extends AppCompatActivity implements ThreadPoolCodeFragment.OnClickThreadPoolListener {

    @ViewInject(R.id.actionbar_view)
    private ActionbarView actionbarView;

    private Context mContext;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ThreadPoolCodeFragment threadPoolCodeFragment;
    private ThreadPoolTestFragment threadPoolTestFragment;

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
            threadPoolCodeFragment.setOnClickThreadPoolListener(this);
        }
        transaction.add(R.id.fragme_layout, threadPoolCodeFragment, "code");
        transaction.commitAllowingStateLoss();
    }

    private void setListener() {
        actionbarView.setOnClickBackListener(new OnClickBackListener() {
            @Override
            public void onClickBack() {
                transaction = manager.beginTransaction();
                if (threadPoolTestFragment != null){
                    transaction.remove(threadPoolTestFragment);
                }
                transaction.show(threadPoolCodeFragment);
                transaction.commitAllowingStateLoss();
            }
        });
        actionbarView.setOnLongClickBackListener(new OnLongClickBackListener() {
            @Override
            public void onLongClickBack() {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClickCached() {
        transaction = manager.beginTransaction();
        transaction.hide(threadPoolCodeFragment);
        if (threadPoolTestFragment == null) {
            threadPoolTestFragment = new ThreadPoolTestFragment();
        }
        threadPoolTestFragment.setArguments(setDatas("cached"));
        transaction.add(R.id.fragme_layout, threadPoolTestFragment);
        transaction.commit();
    }

    @Override
    public void onClickFixed() {
        transaction = manager.beginTransaction();
        transaction.hide(threadPoolCodeFragment);
        if (threadPoolTestFragment == null) {
            threadPoolTestFragment = new ThreadPoolTestFragment();
        }
        threadPoolTestFragment.setArguments(setDatas("fixed"));
        transaction.add(R.id.fragme_layout, threadPoolTestFragment);
        transaction.commit();
    }

    @Override
    public void onClickScheduled() {
        transaction = manager.beginTransaction();
        transaction.hide(threadPoolCodeFragment);
        if (threadPoolTestFragment == null) {
            threadPoolTestFragment = new ThreadPoolTestFragment();
        }
        threadPoolTestFragment.setArguments(setDatas("scheduled"));
        transaction.add(R.id.fragme_layout, threadPoolTestFragment);
        transaction.commit();
    }

    @Override
    public void onClickSingle() {
        transaction = manager.beginTransaction();
        transaction.hide(threadPoolCodeFragment);
        if (threadPoolTestFragment == null) {
            threadPoolTestFragment = new ThreadPoolTestFragment();
        }
        threadPoolTestFragment.setArguments(setDatas("single"));
        transaction.add(R.id.fragme_layout, threadPoolTestFragment);
        transaction.commit();
    }

    private Bundle setDatas(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("type", str);
        return bundle;
    }
}
