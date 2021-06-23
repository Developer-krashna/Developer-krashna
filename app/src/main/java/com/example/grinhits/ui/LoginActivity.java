package com.example.grinhits.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grinhits.R;
import com.example.grinhits.Utils.Constants;
import com.example.grinhits.Utils.SharedPreferenceUtils;
import com.example.grinhits.databinding.ActivityLoginBinding;
import com.example.grinhits.models.ProfileModel;
import com.example.grinhits.viewModel.UserViewModel;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    private UserViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreanActivity();
        super.onCreate(savedInstanceState);
        initControl();
    }

    private void initControl() {
        viewModel=new ViewModelProvider(this).get(UserViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.signInBtn.setOnClickListener(this);
        binding.signupBtn.setOnClickListener(this);
        binding.skipNowTv.setOnClickListener(this);
        observer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_btn: {
                if (loginValidation()) {
                    viewModel.loginUser(binding.phoneEt.getText().toString());
                }
                break;
            }
            case R.id.signup_btn:
            {
                perFormSignUp();
                break;
            }

            case R.id.skip_now_tv:
            {
                gotoHome();
                break;
            }
        }
    }

    private void perFormSignUp() {
        Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    private void observer() {
        viewModel.getLoginData().observe(this, profileModel -> {

           if(profileModel!=null)
           {
               if(profileModel.getPhone_name().equals(binding.phoneEt.getText().toString()))
               {
                   SharedPreferenceUtils.getInstance(LoginActivity.this).isLogin(getString(R.string.isLogin),true);
                   SharedPreferenceUtils.getInstance(LoginActivity.this).setUserId(Constants.USER_ID,profileModel.getUser_id());
                   SharedPreferenceUtils.getInstance(LoginActivity.this).setProfileModel(Constants.PROFILE_MODEL,profileModel);
                   gotoHome();
               }else
               {
                   showToast("Invalid phone number.");
               }
           }else
           {
               showToast("No User Available.");
           }

        });

    }

    private void gotoHome() {
        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private boolean loginValidation() {
        if(binding.phoneEt.getText().toString().isEmpty() && binding.phoneEt.getText().toString().equals(""))
        {
            binding.phoneEt.requestFocus();
            showToast(getString(R.string.please_enter_phone_number));
            return false;
        }
        return true;
    }
}