package com.example.grinhits.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.grinhits.models.ProfileModel;
import com.example.grinhits.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private final UserRepository repository;
    private MutableLiveData<Integer> profileModelMutableLiveData=new  MutableLiveData<>();
    private LiveData<ProfileModel> profileModelLiveData;
    private MutableLiveData<Integer> profileFragmentUSerMutableLiveData=new  MutableLiveData<>();
    private LiveData<ProfileModel> profileFragmentUSerLiveData;
    private MutableLiveData<String> profileModelLoginMutableLiveData=new  MutableLiveData<>();
    private LiveData<ProfileModel> profileModelLoginLiveData;
    private MutableLiveData<String> profileModelGetProfileMutableLiveData=new  MutableLiveData<>();
    private LiveData<ProfileModel> profileModelGetProfileLiveData;
    private MutableLiveData<String> profileModelOtpMutableLiveData=new  MutableLiveData<>();
    private LiveData<ProfileModel> profileModelOtpLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        profileModelLiveData= Transformations.switchMap(profileModelMutableLiveData, id -> repository.getUserProfiele(id));
        profileFragmentUSerLiveData= Transformations.switchMap(profileFragmentUSerMutableLiveData, id -> repository.getUserProfiele(id));
        profileModelLoginLiveData= Transformations.switchMap(profileModelLoginMutableLiveData, phone -> repository.getUserProfilebyProfile(phone));
        profileModelOtpLiveData= Transformations.switchMap(profileModelOtpMutableLiveData, phone -> repository.getUserProfilebyProfile(phone));
        profileModelGetProfileLiveData=Transformations.switchMap(profileModelGetProfileMutableLiveData, input -> repository.getUserProfilebyProfile(input));
    }

    public void insertUser(ProfileModel model) {
        repository.insertUser(model);
    }

    public void getUserProfile(int id) {
        this.profileModelMutableLiveData.setValue(id);
    }

    public LiveData<ProfileModel> getUserProfileModel() {
        return profileModelLiveData;
    }

    public void getOtpUserProfile(String phone) {
        this.profileModelOtpMutableLiveData.setValue(phone);
    }

    public LiveData<ProfileModel> getOtpUserProfileModel() {
        return profileModelOtpLiveData;
    }

    public void getFragmentUserProfile(int id) {
        this.profileFragmentUSerMutableLiveData.setValue(id);
    }

    public LiveData<ProfileModel> getFragmentUserProfileModel() {
        return profileFragmentUSerLiveData;
    }

    public void loginUser(String phone) {
        profileModelLoginMutableLiveData.setValue(phone);
    }

    public LiveData<ProfileModel> getLoginData()
    {
        return profileModelLoginLiveData;
    }

    public LiveData<ProfileModel> getUserProfile() {
        return profileModelGetProfileLiveData;
    }

    public void getUserProfileByPhone(String phone) {
        profileModelGetProfileMutableLiveData.setValue(phone);
    }

    public int updateProfile(String fName,String lNAme,String phone,int id)
    {
        return repository.updateProfile(fName,lNAme,phone,id);
    }

}
