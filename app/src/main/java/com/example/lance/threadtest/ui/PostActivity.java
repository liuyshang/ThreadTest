package com.example.lance.threadtest.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
 * time: 2016/3/21 17:49
 * e-mail: lance.cao@anarry.com
 */
public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.actionbar_view)
    private ActionbarView actionbarView;
    @ViewInject(R.id.tv_text)
    private TextView tvText;
    @ViewInject(R.id.bt_post)
    private Button btPost;
    @ViewInject(R.id.bt_post_delayed)
    private Button btPostDelayed;
    @ViewInject(R.id.bt_run_on_ui_thread)
    private Button btRunOnUIThread;

    private Context mContext;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Injector.initInjectedView(this);
        mContext = PostActivity.this;
        tvText.setText("handler->post");
        setListener();
    }

    private void setListener() {
        btPost.setOnClickListener(this);
        btPostDelayed.setOnClickListener(this);
        btRunOnUIThread.setOnClickListener(this);
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
            case R.id.bt_post:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                tvText.setText(getResources().getString(R.string.post_text));
                            }
                        });
                    }
                }).start();
                break;
            case R.id.bt_post_delayed:
                tvText.setText("");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tvText.setText(getResources().getString(R.string.post_delayed_text));
                            }
                        }, 2000);

                    }
                }).start();
                break;
            case R.id.bt_run_on_ui_thread:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvText.setText(getResources().getString(R.string.runonuithread_text));
                            }
                        });
                    }
                }).start();
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
