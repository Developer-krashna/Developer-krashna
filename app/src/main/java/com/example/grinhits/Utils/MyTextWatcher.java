package com.example.grinhits.Utils;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.example.grinhits.R;

public class MyTextWatcher implements TextWatcher {

    private final AppCompatEditText otp;
    private final AppCompatEditText otp_next;
    private Context context;

    public MyTextWatcher(Context context,AppCompatEditText otp1, AppCompatEditText otp2) {
        this.otp = otp1;
        this.otp_next = otp2;
        this.context=context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        otp.setBackgroundDrawable(context.getDrawable(R.drawable.otp_round_box_red));
    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void afterTextChanged(Editable s) {
        String txt = s.toString();
        switch (otp.getId()) {
            case R.id.otp1_et: {
                if (txt.length() == 1) {
                    otp_next.requestFocus();
                }
            }

            case R.id.otp2_et: {
                if (txt.length() == 1) {
                    otp_next.requestFocus();
                }
            }

            case R.id.otp3_et: {
                if (txt.length() == 1) {
                    otp_next.requestFocus();
                }
            }

            case R.id.otp4_et:{
                if(txt.length()!=1)
                {
                    otp.requestFocus();
                }
            }
        }
        otp.setBackgroundDrawable(context.getDrawable(R.drawable.otp_round_box));
    }


}
