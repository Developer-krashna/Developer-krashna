package com.example.grinhits.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.grinhits.R;
import com.example.grinhits.Utils.Constants;
import com.example.grinhits.databinding.ActivityAddAddressBinding;
import com.example.grinhits.models.AddAddressModel;
import com.example.grinhits.models.ProfileModel;
import com.example.grinhits.service.Dao;
import com.example.grinhits.service.MyRoomDataBase;
import com.example.grinhits.viewModel.AddressViewModel;

public class AddAddressActivity extends  BaseActivity implements View.OnClickListener {

   private ActivityAddAddressBinding binding;
   private AddressViewModel viewModel;
   private Dao dao;
   private String name;
   private int USER_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inItView();
    }

    private void inItView() {
        viewModel=new ViewModelProvider(this).get(AddressViewModel.class);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_address);
        binding.backIv.setOnClickListener(this);
        binding.saveBtn.setOnClickListener(this);

        if(getIntent()!=null)
        {
            name=getIntent().getExtras().get("addressTitle").toString();
            USER_ID=(int)getIntent().getExtras().get(Constants.USER_ID);
            binding.addAddressTv.setText(name);
             if(name.equals("Edit Address"))
            {
                setdataOnUIView((AddAddressModel) getIntent().getExtras().get("addressModel"));
            }
        }

    }

    private void updateAddress() {
        if(isValidate())
        {

           int i= viewModel.updateAddress(
                   binding.userNameEt.getText().toString(),
                    binding.phoneEt.getText().toString(),
                    binding.street1Et.getText().toString(),
                    binding.street2Et.getText().toString(),
                    binding.cityEt.getText().toString(),
                    binding.stateEt.getText().toString(),
                    binding.zipCodeEt.getText().toString(),USER_ID);

           if(i!=-1)
           {
               Intent intent=new Intent();
               setResult(333,intent);
               finish();
           }
        }
    }

    private void setdataOnUIView(AddAddressModel model) {
        binding.userNameEt.setText(model.getUserName());
        binding.phoneEt.setText(model.getPhone());
        binding.street1Et.setText(model.getStreet1());
        binding.street2Et.setText(model.getStreet2());
        binding.cityEt.setText(model.getCity());
        binding.stateEt.setText(model.getState());
        binding.zipCodeEt.setText(model.getZipCode());
    }

    private void saveAddress() {
        if(isValidate())
        {
            AddAddressModel model=new AddAddressModel(
                    binding.userNameEt.getText().toString(),
                    binding.phoneEt.getText().toString(),
                    binding.street1Et.getText().toString(),
                    binding.street2Et.getText().toString(),
                    binding.cityEt.getText().toString(),
                    binding.stateEt.getText().toString(),
                    binding.zipCodeEt.getText().toString(),
                    USER_ID
            );
            viewModel.insertAddress(model);
            Intent intent=new Intent();
            setResult(222,intent);
            finish();
        }
    }

    private boolean isValidate() {
        if(binding.userNameEt.getText().toString().isEmpty())
        {
            showToast("Please enter the UserNAme.");
            binding.userNameEt.requestFocus();
            return false;
        }

        if(binding.phoneEt.getText().toString().isEmpty())
        {
            showToast("Please enter the Phone number.");
            binding.phoneEt.requestFocus();
            return false;
        }

        if(binding.street1Et.getText().toString().isEmpty())
        {
            showToast("Please enter the Street 1.");
            binding.street1Et.requestFocus();
            return false;
        }

        if(binding.street2Et.getText().toString().isEmpty())
        {
            showToast("Please enter the Street 2.");
            binding.street2Et.requestFocus();
            return false;
        }
        if(binding.cityEt.getText().toString().isEmpty())
        {
            showToast("Please enter the City.");
            binding.cityEt.requestFocus();
            return false;
        }

        if(binding.stateEt.getText().toString().isEmpty())
        {
            showToast("Please enter the State.");
            binding.stateEt.requestFocus();
            return false;
        }

        if(binding.zipCodeEt.getText().toString().isEmpty())
        {
            showToast("Please enter the ZipCode.");
            binding.zipCodeEt.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.save_btn:
            {
                if(name.equals(getString(R.string.edit_address)))
                {
                    updateAddress();
                }else
                {
                    saveAddress();
                }
            }
            case R.id.back_iv:
            {
                onBackPressed();
            }
        }
    }
}