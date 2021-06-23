package com.example.grinhits.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.grinhits.R;
import com.example.grinhits.databinding.ActivityEditProfileBinding;
import com.example.grinhits.viewModel.UserViewModel;

public class EditProfileActivity extends BaseActivity implements View.OnClickListener {

private ActivityEditProfileBinding binding;
private UserViewModel userViewModel;
private int USER_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inItViewControl();
    }

    private void inItViewControl() {
        binding= DataBindingUtil.setContentView(this,R.layout.activity_edit_profile);
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        if(getIntent()!=null)
        {
            USER_ID=(int)getIntent().getExtras().get("id");
        }

        binding.backIv.setOnClickListener(this::onClick);
        binding.saveBtn.setOnClickListener(this::onClick);

    }

    private void updateProfile(String fName, String lName, String phone, int user_id) {
       int i= userViewModel.updateProfile(fName, lName, phone, user_id);
       if(i>0)
       {
           Intent intent=new Intent();
           setResult(444,intent);
           finish();
       }
    }

    private boolean isValidate() {
        if(binding.userNameEt.getText().toString().isEmpty())
        {
            showToast("Please enter the First Name.");
            binding.userNameEt.requestFocus();
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

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_iv:
            {
                onBackPressed();
                break;
            }
            case R.id.save_btn:
            {
                if(isValidate())
                {
                    updateProfile(binding.userNameEt.getText().toString(),binding.lastNameEt.getText().toString(),binding.phoneEt.getText().toString(),USER_ID);
                }
            }
        }
    }
}