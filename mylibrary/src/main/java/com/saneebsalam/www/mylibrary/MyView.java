package com.saneebsalam.www.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
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

    private void initialize(Context context) {
        inflate(context, R.layout.my_view, this);
        Button pay = findViewById(R.id.pay);
        pay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "PAY", Toast.LENGTH_SHORT).show();
                getContext().startActivity(new Intent(getContext(), Activity_Payment.class));
            }
        });

    }
}
