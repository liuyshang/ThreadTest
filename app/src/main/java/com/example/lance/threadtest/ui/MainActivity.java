package com.example.lance.threadtest.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lance.threadtest.R;
import com.example.lance.threadtest.util.Injector;
import com.example.lance.threadtest.util.ViewInject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.bt1)
    private Button bt1;
    @ViewInject(R.id.bt2)
    private Button bt2;
    @ViewInject(R.id.bt3)
    private Button bt3;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Injector.initInjectedView(this);
        mContext = MainActivity.this;
        setListener();
    }

    private void setListener() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                startActivity(new Intent(mContext, PostActivity.class));
                break;
            case R.id.bt2:
                startActivity(new Intent(mContext, MessageActivity.class));
                break;
            case R.id.bt3:
                startActivity(new Intent(mContext, AsyncTaskActivity.class));
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
