package com.example.grinhits.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grinhits.R;
import com.example.grinhits.Utils.Constants;
import com.example.grinhits.Utils.MyDeleteLogoutDialog;
import com.example.grinhits.Utils.SharedPreferenceUtils;
import com.example.grinhits.databinding.ActivityHomeBinding;
import com.example.grinhits.fragments.HomeFragment;
import com.example.grinhits.fragments.ProfileFragment;
import com.example.grinhits.models.AddAddressModel;
import com.example.grinhits.models.ProfileModel;
import com.example.grinhits.viewModel.AddressViewModel;
import com.example.grinhits.viewModel.UserViewModel;

import java.security.acl.Owner;

public class HomeActivity extends BaseActivity implements View.OnClickListener, ProfileFragment.OnOpenDialogListener, MyDeleteLogoutDialog.CallDialog {

    private ActivityHomeBinding binding;
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    private MyDeleteLogoutDialog myDeleteLogoutDialog;
    private UserViewModel viewModel;
    private int USER_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreanActivity();
        super.onCreate(savedInstanceState);
        initView();
        initControl();
    }

    private void initControl() {
        binding.headerLayout1.navMenuIcon.setOnClickListener(this);
        binding.menuIcon.setOnClickListener(this);
        binding.navHome.setOnClickListener(this);
        binding.navProfile.setOnClickListener(this);
        binding.logoutBtn.setOnClickListener(this);
        binding.loginBtn.setOnClickListener(this);
    }

    private void initView() {
        USER_ID=SharedPreferenceUtils.getInstance(this).getUserId(Constants.USER_ID);
        viewModel=new ViewModelProvider(this).get(UserViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment(this);
        Bundle bundle=new Bundle();
        bundle.putInt("id",USER_ID);
        profileFragment.setArguments(bundle);
        addFragment(homeFragment);

        observer();
        if(SharedPreferenceUtils.getInstance(this).isLogin(getString(R.string.isLogin)))
        {
            viewModel.getUserProfile(USER_ID);
        }else{
            binding.logoutBtn.setVisibility(View.GONE);
            binding.loginBtn.setVisibility(View.VISIBLE);
        }
    }

    private void observer() {
        viewModel.getUserProfileModel().observe( this, new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel profileModel) {
                updatehomeUi(profileModel);
            }
        });
    }

    private void updatehomeUi(ProfileModel profileModel) {
        binding.headerLayout1.userName.setText(profileModel.getFirst_name()+" "+profileModel.getLast_name());
        binding.headerLayout1.navPhone.setText(profileModel.getPhone_name());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_menu_icon: {
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            }

            case R.id.menu_icon: {
                binding.drawerLayout.openDrawer(Gravity.LEFT);
                break;
            }
            case R.id.nav_home: {
                replaceFragment(homeFragment);
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            }

            case R.id.nav_profile: {
                if(SharedPreferenceUtils.getInstance(this).isLogin(getString(R.string.isLogin)))
                {
                    replaceFragment(profileFragment);
                }else
                {
                    openDialog(getString(R.string.warning),getString(R.string.login_first_proceed),getString(R.string.login));
                }
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            }
            case R.id.logout_btn: {
                openDialog(getString(R.string.logout), getString(R.string.dimen_are_you_want_sure), getString(R.string.logout));
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            }
            case R.id.login_btn: {
                openDialog(getString(R.string.login),getString(R.string.dimen_are_you_want_sure_login),getString(R.string.login));
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            }
        }
    }


    @Override
    public void openDialog(String title,String desc,String from) {
        myDeleteLogoutDialog = new MyDeleteLogoutDialog(this);
        myDeleteLogoutDialog.inItData(title, desc, from);
        myDeleteLogoutDialog.openDialog();
    }


    void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout, fragment);
        transaction.commit();
    }

    void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }


    @Override
    public void deleteOnDialog() {
        profileFragment.deleteAddress();
    }
}