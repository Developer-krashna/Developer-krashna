package com.example.grinhits.service;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.grinhits.Utils.Constants;
import com.example.grinhits.models.AddAddressModel;
import com.example.grinhits.models.ProfileModel;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insert(AddAddressModel addressModel);

    @Delete
    void delete(AddAddressModel addressModel);

    @Query("SELECT * FROM "+ Constants.ADD_ADDRESS_MODEL +" WHERE user_id=:id")
    LiveData<List<AddAddressModel>> getAllAddresses(int id);

    @Query("UPDATE ADD_ADDRESS_MODEL SET  userName=:userName ,phone=:phone , street1=:street1, street2=:street2, city=:city , state=:state, zipCode=:zipCode WHERE user_id = :id")
    int updateAddress(String userName, String phone, String street1, String street2, String city, String state, String zipCode, int id);
/*
    @Update
  int updateAddress(AddAddressModel profileModel);
*/
@Query("UPDATE PROFILE_MODEL SET  first_name=:firstName ,last_name=:lastNAme , phone_name=:phone WHERE user_id = :id")
int updateProfile(String firstName, String lastNAme, String phone, int id);

    @Insert
    void insert(ProfileModel profileModel);

    @Query("SELECT * FROM "+Constants.PROFILE_MODEL+" WHERE user_id=:id")
      LiveData<ProfileModel>  getUserProfile(int id);

    @Query("SELECT * FROM "+Constants.PROFILE_MODEL+" WHERE  phone_name=:phone")
    LiveData<ProfileModel>  getUserProfilebyProfile(String phone);

}
