package com.example.lance.threadtest.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.lance.threadtest.R;

/**
 * author: admin
 * time: 2016/3/22 15:42
 * e-mail: lance.cao@anarry.com
 */
public class DialogView extends DialogFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.layout_loading,null);
    }
}
