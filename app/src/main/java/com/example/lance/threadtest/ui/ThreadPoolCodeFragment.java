package com.example.lance.threadtest.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lance.threadtest.R;
import com.example.lance.threadtest.util.Injector;
import com.example.lance.threadtest.util.ViewInject;

/**
 * author: admin
 * time: 2016/3/23 16:43
 * e-mail: lance.cao@anarry.com
 */
public class ThreadPoolCodeFragment extends Fragment implements View.OnClickListener {

    @ViewInject(R.id.tv_info)
    private TextView tvInfo;
    @ViewInject(R.id.bt_newCachedThreadPool)
    private Button btNewCacheThreadPool;
    @ViewInject(R.id.bt_newFixedThreadPool)
    private Button btNewFixedThreadPool;
    @ViewInject(R.id.bt_newScheduledThreadPool)
    private Button btNewScheduledThreadPool;
    @ViewInject(R.id.bt_newSingleThreadExecutor)
    private Button btNewSingleThreadExecutor;

    private View view;
    private Context mContext;
    private OnClickThreadPoolListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(">>>","ThreadPoolCodeFragment onCreateView");
        view = inflater.inflate(R.layout.fragment_thread_pool_code, container, false);
        Injector.initInjectedView(this, view);
        mContext = getActivity();
        init();
        setListener();
        return view;
    }

    private void init() {
        tvInfo.setText(getResources().getString(R.string.thread_pool_text));
    }

    private void setListener() {
        btNewCacheThreadPool.setOnClickListener(this);
        btNewFixedThreadPool.setOnClickListener(this);
        btNewScheduledThreadPool.setOnClickListener(this);
        btNewSingleThreadExecutor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_newCachedThreadPool:
                if (mListener != null) {
                    mListener.onClickCached();
                }
                break;
            case R.id.bt_newFixedThreadPool:
                if (mListener != null) {
                    mListener.onClickFixed();
                }
                break;
            case R.id.bt_newScheduledThreadPool:
                if (mListener != null) {
                    mListener.onClickScheduled();
                }
                break;
            case R.id.bt_newSingleThreadExecutor:
                if (mListener != null) {
                    mListener.onClickSingle();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(">>>", "ThreadPoolCodeFragment onDestroyView");
    }

    /**
     * 定义接口
     */
    public interface OnClickThreadPoolListener {
        void onClickCached();

        void onClickFixed();

        void onClickScheduled();

        void onClickSingle();
    }

    public void setOnClickThreadPoolListener(OnClickThreadPoolListener listener) {
        this.mListener = listener;
    }
}
