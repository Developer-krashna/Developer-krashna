package com.example.grinhits.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.grinhits.R;
import com.example.grinhits.Utils.Constants;
import com.example.grinhits.Utils.SharedPreferenceUtils;
import com.example.grinhits.databinding.ActivitySignUpBinding;
import com.example.grinhits.models.ProfileModel;
import com.example.grinhits.viewModel.UserViewModel;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    private ActivitySignUpBinding binding;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inItView();
    }

    private void inItView() {
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        binding.signinBtn.setOnClickListener(this::onClick);
        binding.signupBtn.setOnClickListener(this::onClick);
    }

    private boolean isValidate() {
        if(binding.firstNameEt.getText().toString().isEmpty())
        {
            showToast("Please enter the First Name.");
            binding.firstNameEt.requestFocus();
            return false;
        }

        if(binding.lastNameEt.getText().toString().isEmpty())
        {
            showToast("Please enter the Last Name.");
            binding.lastNameEt.requestFocus();
            return false;
        }

        if(binding.phoneEt.getText().toString().isEmpty())
        {
            showToast("Please enter the Phone number.");
            binding.phoneEt.requestFocus();
            return false;
        }

        if(binding.addressEt.getText().toString().isEmpty())
        {
            showToast("Please enter the Address.");
            binding.addressEt.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signup_btn:
            {
                perFormSignUp();
                break;
            }

            case R.id.signin_btn:
            {
                perFormSignin();
                break;
            }
        }
    }

    private void perFormSignin() {
        Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void perFormSignUp() {
        if(isValidate())
        {
            ProfileModel model=new ProfileModel(
                    binding.firstNameEt.getText().toString(),
                    binding.lastNameEt.getText().toString(),
                    binding.phoneEt.getText().toString(),
                    binding.addressEt.getText().toString()
            );
            userViewModel.insertUser(model);
            SharedPreferenceUtils.getInstance(SignUpActivity.this).isLogin(getString(R.string.isLogin),true);
            SharedPreferenceUtils.getInstance(SignUpActivity.this).setProfileModel(Constants.PROFILE_MODEL,model);
            Intent intent=new Intent(SignUpActivity.this,OtpVerificationActivity.class);
            intent.putExtra("phone",binding.phoneEt.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}