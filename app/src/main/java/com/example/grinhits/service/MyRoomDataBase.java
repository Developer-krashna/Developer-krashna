package com.example.grinhits.service;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.grinhits.Utils.Constants;
import com.example.grinhits.models.AddAddressModel;
import com.example.grinhits.models.ProfileModel;

@Database(entities = {AddAddressModel.class, ProfileModel.class},version = 1)
public abstract class MyRoomDataBase extends RoomDatabase {
    private static MyRoomDataBase  myRoomDataBase;

    public static synchronized MyRoomDataBase getInstance(Context context)
    {
        if(myRoomDataBase==null)
        {
            myRoomDataBase= Room.databaseBuilder(context.getApplicationContext(),MyRoomDataBase.class, Constants.ADD_ADDRESS_MODEL)
                    .allowMainThreadQueries()
                    .build();
        }
        return  myRoomDataBase;
    }

    public abstract Dao getDao();
}
