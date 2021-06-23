package com.example.grinhits.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.grinhits.models.ProfileModel;
import com.example.grinhits.service.Dao;
import com.example.grinhits.service.MyRoomDataBase;

public class UserRepository {
    private Dao dao;

    public UserRepository(Application application)
    {
        MyRoomDataBase myRoomDataBase=MyRoomDataBase.getInstance(application);
        dao=myRoomDataBase.getDao();
    }
    public void insertUser(ProfileModel model) {
        dao.insert(model);
    }

    public LiveData<ProfileModel> getUserProfiele(int id) {
       return dao.getUserProfile(id);
    }


    public LiveData<ProfileModel> getUserProfilebyProfile(String phone)
    {
       return dao.getUserProfilebyProfile(phone);
    }

    public int updateProfile(String fName, String lNAme, String phone, int id) {
        return dao.updateProfile(fName,lNAme,phone,id);
    }
}
