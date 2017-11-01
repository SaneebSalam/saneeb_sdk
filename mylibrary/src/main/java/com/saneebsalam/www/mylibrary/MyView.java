package com.saneebsalam.www.mylibrary;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by Saneeb Salam
 * on 10/31/2017.
 */

public class MyView extends LinearLayout {
    public MyView(Context context) {
        super(context);
        initialize(context);
    }

    private void initialize(Context context){
        inflate(context, R.layout.my_view, this);
    }
}
