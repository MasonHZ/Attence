package com.example.a89370.view;

import android.content.Context;


/**
 * Created by 89370 on 2018/4/24.
 */

public class FocusTextView extends android.support.v7.widget.AppCompatTextView {


    public FocusTextView(Context context) {
        super(context);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
