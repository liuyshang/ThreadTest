package com.example.lance.threadtest.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.lance.threadtest.R;
import com.example.lance.threadtest.util.Injector;
import com.example.lance.threadtest.util.ViewInject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * author: admin
 * time: 2016/3/23 16:57
 * e-mail: lance.cao@anarry.com
 */
public class ThreadPoolTestFragment extends Fragment {

    @ViewInject(R.id.progress_bar_1)
    private ProgressBar progBar1;
    @ViewInject(R.id.progress_bar_2)
    private ProgressBar progBar2;
    @ViewInject(R.id.progress_bar_3)
    private ProgressBar progBar3;
    @ViewInject(R.id.progress_bar_4)
    private ProgressBar progBar4;
    @ViewInject(R.id.progress_bar_5)
    private ProgressBar progBar5;
    @ViewInject(R.id.progress_bar_6)
    private ProgressBar progBar6;

    private View view;
    private String type;
    private ThreadFactory mFactory = Executors.defaultThreadFactory();
    /*private WeakHandler mHandler = new WeakHandler(this);*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(">>>", "ThreadPoolTestFragment onCreateView");
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_thread_pool_test, null);
        }
        Injector.initInjectedView(this, view);
        init();
        return view;
    }

    private void init() {
        type = getArguments().getString("type");
        switch (type) {
            case "cached":
                Log.i(">>>", "cached");
                setCachedThreadPool();
                break;
            case "fixed":
                Log.i(">>>", "fixed");
                setFixedThreadPool();
                break;
            case "scheduled":
                Log.i(">>>", "scheduled");
                setScheduleThreadPool();
                break;
            case "single":
                Log.i(">>>", "single");
                setSingleThreadPool();
                break;
            default:
                break;
        }
    }

    /**
     * newSingleThreadExecutor()
     */
    private void setSingleThreadPool() {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor(mFactory);
        for (int i = 1; i <= 6; i++) {
            final int finalI = i;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    int progress = 0;
                    while (progress <= 100) {
                        progress++;
                        updateUi(finalI, progress);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        if (!singleThreadPool.isShutdown()) {
            singleThreadPool.shutdown();
        }
    }

    /**
     * newScheduledThreadPool()
     */
    private void setScheduleThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3, mFactory);
        for (int i = 1; i <= 6; i++) {
            final int finalI = i;
            scheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    int progress = 0;
                    while (progress <= 100) {
                        progress++;
                        updateUi(finalI, progress);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, 3, TimeUnit.SECONDS);
        }
        if (!scheduledThreadPool.isShutdown()) {
            scheduledThreadPool.shutdown();
        }
    }

    /**
     * newFixedThreadPool()
     */
    private void setFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2,mFactory);
        for (int i = 1; i <= 6; i++) {
            final int finalI = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    int progress = 0;
                    while (progress <= 100) {
                        progress++;
                        updateUi(finalI, progress);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        if (!fixedThreadPool.isShutdown()) {
            fixedThreadPool.shutdown();
        }
    }

    /**
     * newCachedThreadPool()
     */
    private void setCachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(mFactory);
        for (int i = 1; i <= 6; i++) {
            final int finalI = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    int progress = 0;
                    while (progress <= 100) {
                        progress++;
                        /**
                         * 可以直接在线程内更新progressBar
                         * progressBar源码有做处理
                         * */
                        updateUi(finalI, progress);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        if (!cachedThreadPool.isShutdown()) {
            cachedThreadPool.shutdown();
        }
    }

    private void updateUi(int i, int progress) {
        if (progress == 100) {
            progress = 0;
        }
        switch (i) {
            case 1:
                progBar1.setProgress(progress);
                break;
            case 2:
                progBar2.setProgress(progress);
                break;
            case 3:
                progBar3.setProgress(progress);
                break;
            case 4:
                progBar4.setProgress(progress);
                break;
            case 5:
                progBar5.setProgress(progress);
                break;
            case 6:
                progBar6.setProgress(progress);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(">>>", "ThreadPoolTestFragment onDestroyView");
        //不销毁View，则页面不会更新
        if (view != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

   /* public class WeakHandler extends Handler {
        WeakReference<ThreadPoolTestFragment> mFragment;

        WeakHandler(ThreadPoolTestFragment fragment) {
            this.mFragment = new WeakReference<ThreadPoolTestFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mFragment.get().progBar1.setProgress(msg.getData().getInt(String.valueOf(1)));
                case 2:
                    mFragment.get().progBar2.setProgress(msg.getData().getInt(String.valueOf(2)));
                case 3:
                    mFragment.get().progBar3.setProgress(msg.getData().getInt(String.valueOf(3)));
                case 4:
                    mFragment.get().progBar4.setProgress(msg.getData().getInt(String.valueOf(4)));
                case 5:
                    mFragment.get().progBar5.setProgress(msg.getData().getInt(String.valueOf(5)));
                case 6:
                    mFragment.get().progBar6.setProgress(msg.getData().getInt(String.valueOf(6)));
                default:
                    break;
            }
        }
    }*/
}
