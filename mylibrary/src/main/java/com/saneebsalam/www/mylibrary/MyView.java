package com.saneebsalam.www.mylibrary;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Saneeb Salam
 * on 10/31/2017.
 */

public class MyView extends LinearLayout {
    public  MyView(Context context) {
        super(context);
        initialize(context);
    }

    private  void initialize(final Context context) {
        inflate(context, R.layout.my_view, this);
        Button pay = findViewById(R.id.pay);
        pay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "PAY", Toast.LENGTH_SHORT).show();
//                getContext().startActivity(new Intent(getContext(), Activity_Payment.class));

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.item_paymentmethod);

                final RadioGroup radiogp = dialog.findViewById(R.id.radiogroup);
                final RadioButton radiopayviamobile = dialog.findViewById(R.id.payviamobile);
                final RadioButton radiopayviacash = dialog.findViewById(R.id.payviacash);
                TextView Pay_payment = dialog.findViewById(R.id.Pay_payment);


                Pay_payment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedId = radiogp.getCheckedRadioButtonId();

                        if (selectedId == radiopayviamobile.getId()) {
                            dialog.dismiss();
//                            alert_pay(getString(R.string.conformmobile) + Totalprice + " QR");
                        } else if (selectedId == radiopayviacash.getId()) {
                            dialog.dismiss();
//                            PayClassified_cash();
                        } else {
//                            alert_ok("Please choose one option");
                        }


                    }
                });

                dialog.show();
            }
        });




    }


}
