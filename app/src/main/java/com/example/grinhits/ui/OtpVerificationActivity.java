package com.example.grinhits.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.grinhits.R;
import com.example.grinhits.Utils.Constants;
import com.example.grinhits.Utils.MyTextWatcher;
import com.example.grinhits.Utils.SharedPreferenceUtils;
import com.example.grinhits.databinding.ActivityOtpVerificationBinding;
import com.example.grinhits.models.ProfileModel;
import com.example.grinhits.viewModel.UserViewModel;

public class OtpVerificationActivity extends BaseActivity implements View.OnClickListener {

    private ActivityOtpVerificationBinding binding;
    private String phone;
    private UserViewModel viewModel;
    private String OTP="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreanActivity();
        super.onCreate(savedInstanceState);
        initView();
        initControl();
    }


    private void initView() {
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp_verification);
        phone = getIntent().getStringExtra("phone");
        binding.otpDescPhoneTv.setText(phone);
        observer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continue_btn: {
                if (isValidate()) {

                    OTP=binding.otp1Et.getText().toString()+binding.otp2Et.getText().toString()+binding.otp3Et.getText().toString()+binding.otp4Et.getText().toString();

                    if (OTP.equals("1234")) {
                        if (phone != null) {
                            viewModel.getOtpUserProfile(phone);
                        }
                    } else {
                        showToast("Please Enter Valid Otp");
                    }

                }
            }
        }
    }

    private void initControl() {
        binding.continueBtn.setOnClickListener(this::onClick);
        binding.otp1Et.addTextChangedListener(new MyTextWatcher(this, binding.otp1Et, binding.otp2Et));
        binding.otp2Et.addTextChangedListener(new MyTextWatcher(this, binding.otp2Et, binding.otp3Et));
        binding.otp3Et.addTextChangedListener(new MyTextWatcher(this, binding.otp3Et, binding.otp4Et));
        binding.otp4Et.addTextChangedListener(new MyTextWatcher(this, binding.otp4Et, binding.otp1Et));
    }


    private void observer() {
        viewModel.getOtpUserProfileModel().observe(this, profileModel -> {
            if (profileModel != null) {
                updatehomeUi(profileModel);
                //goToHome();
            }
        });
    }

    private void updatehomeUi(ProfileModel profileModel) {
        SharedPreferenceUtils.getInstance(this).isLogin(getString(R.string.isLogin), true);
        SharedPreferenceUtils.getInstance(OtpVerificationActivity.this).setUserId(Constants.USER_ID, profileModel.getUser_id());
        SharedPreferenceUtils.getInstance(OtpVerificationActivity.this).setProfileModel(Constants.PROFILE_MODEL, profileModel);
        goToHome();
    }

    void goToHome() {
        Intent intent = new Intent(OtpVerificationActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private boolean isValidate() {
        if (binding.otp1Et.getText().toString().isEmpty()) {
            binding.otp1Et.requestFocus();
            return false;
        }

        if (binding.otp2Et.getText().toString().isEmpty()) {
            binding.otp2Et.requestFocus();
            return false;
        }

        if (binding.otp3Et.getText().toString().isEmpty()) {
            binding.otp3Et.requestFocus();
            return false;
        }

        if (binding.otp4Et.getText().toString().isEmpty()) {
            binding.otp4Et.requestFocus();
            return false;
        }
        return true;
    }
}