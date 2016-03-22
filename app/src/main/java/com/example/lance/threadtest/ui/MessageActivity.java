package com.example.lance.threadtest.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.lang.ref.WeakReference;

/**
 * author: admin
 * time: 2016/3/22 11:14
 * e-mail: lance.cao@anarry.com
 */
public class MessageActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.actionbar_view)
    private ActionbarView actionbarView;
    @ViewInject(R.id.tv_text)
    private TextView tvText;
    @ViewInject(R.id.bt_message)
    private Button btMessage;
    @ViewInject(R.id.bt_message_delayed)
    private Button btMessageDelay;
    @ViewInject(R.id.bt_empty_message)
    private Button btEmptyMessage;
    @ViewInject(R.id.bt_empty_message_delay)
    private Button btEmptyMessageDelay;
    @ViewInject(R.id.bt_send_to_target)
    private Button btSendToTarget;

    private Context mContext;
    private WeakRefHandler mHandler = new WeakRefHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Injector.initInjectedView(this);
        mContext = MessageActivity.this;
        setListener();
    }

    private void setListener() {
        btMessage.setOnClickListener(this);
        btMessageDelay.setOnClickListener(this);
        btEmptyMessage.setOnClickListener(this);
        btEmptyMessageDelay.setOnClickListener(this);
        btSendToTarget.setOnClickListener(this);
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
            case R.id.bt_message:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        msg.what = 1;
                        msg.obj = "使用Message.Obtain + Hander.sendMessage()发送消息";
                        mHandler.sendMessage(msg);
                    }
                }).start();
                break;
            case R.id.bt_message_delayed:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        msg.what = 2;
                        msg.obj = "使用Message.Obtain + Hander.sendMessageDelayed()发送消息";
                        mHandler.sendMessageDelayed(msg, 2000);
                    }
                }).start();
                break;
            case R.id.bt_empty_message:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessage(3);
                    }
                }).start();
                break;
            case R.id.bt_empty_message_delay:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessageDelayed(4, 2000);
                    }
                }).start();
                break;
            case R.id.bt_send_to_target:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = Message.obtain(mHandler);
                        msg.what = 5;
                        msg.obj = "使用Message.sendToTarget发送消息";
                        msg.sendToTarget();
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


    /**
     * 当我们的activity里有handler对象时，有时候会因为handler对activity的持有而导致activity无法被收回，引发oom;
     */
    private static class WeakRefHandler extends Handler {
        WeakReference<MessageActivity> mActivity;

        WeakRefHandler(MessageActivity activity) {
            this.mActivity = new WeakReference<MessageActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mActivity.get().tvText.setText(msg.obj.toString());
                    break;
                case 2:
                    mActivity.get().tvText.setText(msg.obj.toString());
                    break;
                case 3:
                    mActivity.get().tvText.setText("使用Hander.sendEmptyMessage()发送消息");
                    break;
                case 4:
                    mActivity.get().tvText.setText("使用Hander.sendEmptyMessageDelayed()发送消息");
                    break;
                case 5:
                    mActivity.get().tvText.setText(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }
}
