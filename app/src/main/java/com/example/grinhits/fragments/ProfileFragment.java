package com.example.grinhits.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.grinhits.R;
import com.example.grinhits.Utils.Constants;
import com.example.grinhits.Utils.MyDeleteLogoutDialog;
import com.example.grinhits.Utils.SharedPreferenceUtils;
import com.example.grinhits.adpters.MyAddressAdapter;
import com.example.grinhits.databinding.FragmentProfileBinding;
import com.example.grinhits.models.AddAddressModel;
import com.example.grinhits.models.ProfileModel;
import com.example.grinhits.ui.AddAddressActivity;
import com.example.grinhits.ui.EditProfileActivity;
import com.example.grinhits.ui.HomeActivity;
import com.example.grinhits.viewModel.AddressViewModel;
import com.example.grinhits.viewModel.UserViewModel;

import java.util.List;

public class ProfileFragment extends Fragment implements View.OnClickListener, MyAddressAdapter.OnAddressItemClicListener {

    private final int DELETE_REQ_CODE = 111;
    private final int EDIT_REQ_CODE = 222;
    private Context context;
    private HomeActivity homeActivity;
    private MyAddressAdapter myAddressAdapter;
    private FragmentProfileBinding binding;
    private OnOpenDialogListener openDialogListener;
    private AddressViewModel addressViewModel;
    private UserViewModel userViewModel;
    private AddAddressModel model;
    private MyDeleteLogoutDialog myDeleteLogoutDialog;
    private int USER_ID = -1;


    public ProfileFragment(OnOpenDialogListener dialogListener) {
        this.openDialogListener = dialogListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.homeActivity = (HomeActivity) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        setUpAddressRv();
        if(SharedPreferenceUtils.getInstance(context).isLogin(getString(R.string.isLogin)))
        {
            initView();
        }else
        {

        }
        inItControl();
    }

    private void inItControl() {
        binding.profileAddAddressTv.setOnClickListener(this);
        binding.editProfileTv.setOnClickListener(this);
        binding.profileLogoutTv.setOnClickListener(this);
    }

    private void initView() {
        observer();
        if (getArguments() != null) {
            USER_ID = Integer.parseInt(getArguments().get("id").toString());
        }
        addressViewModel.setArgumentForAllAddress(USER_ID);
        userViewModel.getFragmentUserProfile(USER_ID);
    }

    private void updateProfilefragmentUi(ProfileModel profileModel) {
        binding.titleProfileTv.setText(profileModel.getFirst_name() + " " + profileModel.getLast_name());
        binding.profileDescTv.setText(profileModel.getPhone_name());
    }

    private void setUpAddressRv() {
        binding.addressRv.setLayoutManager(new LinearLayoutManager(context));
        myAddressAdapter = new MyAddressAdapter(context, this);
        binding.addressRv.setAdapter(myAddressAdapter);
        myAddressAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_add_address_tv: {
                openActivityAddAddresss("Add Address");
                break;
            }

            case R.id.edit_profile__tv: {
                if(SharedPreferenceUtils.getInstance(context).isLogin(getString(R.string.isLogin)))
                {
                    openEditProfielActivity();
                }
                break;
            }

            case R.id.profile_logout__tv: {
                if(SharedPreferenceUtils.getInstance(context).isLogin(getString(R.string.isLogin)))
                {
                    openDialogListener.openDialog(getString(R.string.logout), getString(R.string.dimen_are_you_want_sure), getString(R.string.logout));
                }
                break;
            }


        }
    }

    private void openEditProfielActivity() {
        Intent intent = new Intent(context, EditProfileActivity.class);
        intent.putExtra("id",USER_ID);
        startActivityForResult(intent, EDIT_REQ_CODE);
    }

    void openDialog() {
        myDeleteLogoutDialog = new MyDeleteLogoutDialog((HomeActivity) context);
        myDeleteLogoutDialog.inItData(getString(R.string.delete), getString(R.string.are_you_want_sure), getString(R.string.delete));
        myDeleteLogoutDialog.openDialog();
    }

    private void openActivityAddAddresss(String addressTitle) {
        Intent intent = new Intent(context, AddAddressActivity.class);
        intent.putExtra("addressTitle", addressTitle);
        intent.putExtra("addressModel", model);
        intent.putExtra(Constants.USER_ID, USER_ID);
        startActivityForResult(intent, DELETE_REQ_CODE);
    }

    @Override
    public void onAddressItemClick(AddAddressModel model) {
        this.model = model;
        if (SharedPreferenceUtils.getInstance(context).isEditButton("edit")) {
            SharedPreferenceUtils.getInstance(context).isEditButton("edit", false);
            openActivityAddAddresss(getString(R.string.edit_address));
        } else {
            openDialog();
        }
    }

    void observer() {
        addressViewModel.getAddressList().observe((LifecycleOwner) context, new Observer<List<AddAddressModel>>() {
            @Override
            public void onChanged(List<AddAddressModel> addAddressModels) {
                myAddressAdapter.setAddressList(addAddressModels);
            }
        });
        userViewModel.getFragmentUserProfileModel().observe((HomeActivity) context, new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel profileModel) {
                updateProfilefragmentUi(profileModel);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 222) {
            addressViewModel.setArgumentForAllAddress(USER_ID);
        }
    }

    public void deleteAddress() {
        if (model != null) {
            addressViewModel.deleteAddress(model);
        }
    }


    public interface OnOpenDialogListener {
        void openDialog(String title,String desc,String from);
    }
}