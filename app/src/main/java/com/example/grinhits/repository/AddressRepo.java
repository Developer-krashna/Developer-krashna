package com.example.grinhits.repository;

import android.app.Application;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.grinhits.models.AddAddressModel;
import com.example.grinhits.models.ProfileModel;
import com.example.grinhits.service.Dao;
import com.example.grinhits.service.MyRoomDataBase;

import java.util.List;

public class AddressRepo {
    private Dao dao;

   public AddressRepo(Application application)
   {
       MyRoomDataBase myRoomDataBase=MyRoomDataBase.getInstance(application);
       dao=myRoomDataBase.getDao();

   }



   public LiveData<List<AddAddressModel>> getAllAddressById(int id)
   {
       return  dao.getAllAddresses(id);
   }


    public void deleteAddress(AddAddressModel model)
    {
        dao.delete(model);
    }


    public int updateAddress(String userName, String phone, String street1, String street2, String city, String state, String zipCode, int id) {
       return dao.updateAddress(userName,phone,street1,street2,city,state,zipCode,id);
    }

    /*public int updateAddress(AddAddressModel model)
    {
        return dao.updateAddress(model);
    }*/

    public void insertAddress(AddAddressModel model) {
        dao.insert(model);
    }
}
