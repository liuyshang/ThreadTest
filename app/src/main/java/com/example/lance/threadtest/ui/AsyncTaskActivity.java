package com.example.lance.threadtest.ui;

import android.app.DialogFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lance.threadtest.R;
import com.example.lance.threadtest.util.AESUtil;
import com.example.lance.threadtest.util.Injector;
import com.example.lance.threadtest.util.OnClickBackListener;
import com.example.lance.threadtest.util.ViewInject;
import com.example.lance.threadtest.view.ActionbarView;
import com.example.lance.threadtest.view.DialogView;

import java.lang.ref.WeakReference;

/**
 * author: admin
 * time: 2016/3/22 14:33
 * e-mail: lance.cao@anarry.com
 */
public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.actionbar_view)
    private ActionbarView actionbarView;
    @ViewInject(R.id.tv_text)
    private TextView tvText;
    @ViewInject(R.id.bt_encrypt)
    private Button btEncrypt;
    @ViewInject(R.id.bt_decrypt)
    private Button btDecrypt;

    /**
     * 文字加解密标志
     */
    private boolean flag = false;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        Injector.initInjectedView(this);
        mContext = AsyncTaskActivity.this;
        init();
        setListener();
    }

    private void init() {
        tvText.setText(getResources().getString(R.string.async_task_text));
    }

    private void setListener() {
        btEncrypt.setOnClickListener(this);
        btDecrypt.setOnClickListener(this);
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
            case R.id.bt_encrypt:
                if (flag) {
                    Toast.makeText(mContext, "it is encrypted", Toast.LENGTH_SHORT).show();
                } else {
                    new AESAsyncTask(this).execute(tvText.getText().toString());
                }
                break;
            case R.id.bt_decrypt:
                if (!flag) {
                    Toast.makeText(mContext, "please encrypt it at first", Toast.LENGTH_SHORT).show();
                } else {
                    new AESAsyncTask(this).execute(tvText.getText().toString());
                }
                break;
            default:
                break;
        }
    }

    private static class AESAsyncTask extends AsyncTask<String, Integer, String> {
        WeakReference<AsyncTaskActivity> mActivity;
        String aes;
        DialogView dialogView;

        AESAsyncTask(AsyncTaskActivity activity) {
            this.mActivity = new WeakReference<AsyncTaskActivity>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(">>>", "onPreExecute");
            dialogView = new DialogView();
            dialogView.show(mActivity.get().getFragmentManager(), "aes");
        }

        @Override
        protected String doInBackground(String... params) {
            Log.i(">>>", "doInBackground");
            try {
                if (mActivity.get().flag) {
                    aes = AESUtil.decrypt("aes", params[0]);
                } else {
                    aes = AESUtil.encrypt("aes", params[0]);
                }
                //暂停时间,模拟线程任务
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return aes;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(">>>", "onPostExecute");
            dialogView.dismiss();
            mActivity.get().tvText.setText(s);
            mActivity.get().flag = !mActivity.get().flag;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i(">>>", "onProgressUpdate");
        }
    }
}
