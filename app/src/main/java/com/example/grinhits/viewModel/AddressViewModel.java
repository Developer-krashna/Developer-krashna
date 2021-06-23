package com.example.grinhits.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.grinhits.models.AddAddressModel;
import com.example.grinhits.models.ProfileModel;
import com.example.grinhits.repository.AddressRepo;

import java.util.List;

public class AddressViewModel extends AndroidViewModel {

    private MutableLiveData<Integer> listMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<Integer> editAddressMutableLiveData=new MutableLiveData<>();
    private LiveData<List<AddAddressModel>> listLiveData;
    private LiveData<List<AddAddressModel>> editAddressLiveData;
    private AddressRepo addressRepo;
    public AddressViewModel(@NonNull  Application application) {
        super(application);
        addressRepo =new AddressRepo(application);
        listLiveData= Transformations.switchMap(listMutableLiveData, input -> addressRepo.getAllAddressById(input));
    }

    public void setArgumentForAllAddress(int id)
    {
        listMutableLiveData.setValue(id);
    }
    public LiveData<List<AddAddressModel>> getAddressList() {
        return listLiveData;
    }
    public void deleteAddress(AddAddressModel model)
    {
        addressRepo.deleteAddress(model);
    }

    public void insertAddress(AddAddressModel model)
    {
        addressRepo.insertAddress(model);
    }

      public int updateAddress(String userName, String phone, String street1, String street2, String city, String state, String zipCode, int id) {
        return addressRepo.updateAddress(userName,phone,street1,street2,city,state,zipCode,id);
    }

  /*  public int updateAddress(AddAddressModel model)
    {
        return addressRepo.updateAddress(model);
    }*/
}
