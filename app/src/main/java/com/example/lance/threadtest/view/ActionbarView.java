package com.example.lance.threadtest.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lance.threadtest.R;
import com.example.lance.threadtest.util.OnClickBackListener;
import com.example.lance.threadtest.util.OnLongClickBackListener;

/**
 * author: admin
 * time: 2016/3/15 12:14
 * e-mail: lance.cao@anarry.com
 */
public class ActionbarView extends FrameLayout implements View.OnClickListener,View.OnLongClickListener{

    /**
     * 返回按钮
     */
    private ImageView ivBack;
    /**
     * 标题
     */
    private TextView tvTitle;

    private View view;
    private OnClickBackListener mListener;
    private OnLongClickBackListener lListener;

    public ActionbarView(Context context) {
        this(context, null);
    }

    public ActionbarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionbarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //root不能设置为null，否则布局不能显示
        view = LayoutInflater.from(context).inflate(R.layout.layout_actionbar, this, true);
        ivBack = (ImageView) view.findViewById(R.id.iv_back);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        ivBack.setOnClickListener(this);
        ivBack.setOnLongClickListener(this);
    }

    public void setTvTitle(String str) {
        if (!TextUtils.isEmpty(str)) {
            tvTitle.setText(str);
        }
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                mListener.onClickBack();
                break;
            default:
                break;
        }
    }

    public void setOnClickBackListener(OnClickBackListener listener) {
        this.mListener = listener;
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                lListener.onLongClickBack();
                break;
            default:
                break;
        }
        return false;
    }

    public void setOnLongClickBackListener(OnLongClickBackListener listener) {
        this.lListener = listener;
    }
}
