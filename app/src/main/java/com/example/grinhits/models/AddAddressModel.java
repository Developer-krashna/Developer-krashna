package com.example.grinhits.models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.grinhits.Utils.Constants;

@Entity(tableName = Constants.ADD_ADDRESS_MODEL)
public class AddAddressModel implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int user_id;
    private String userName;
    private String phone;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipCode;

    public AddAddressModel(String userName, String phone, String street1, String street2, String city, String state, String zipCode,int user_id) {
        this.user_id = user_id;
        this.userName = userName;
        this.phone = phone;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    protected AddAddressModel(Parcel in) {
        id = in.readInt();
        user_id = in.readInt();
        userName = in.readString();
        phone = in.readString();
        street1 = in.readString();
        street2 = in.readString();
        city = in.readString();
        state = in.readString();
        zipCode = in.readString();
    }

    public static final Creator<AddAddressModel> CREATOR = new Creator<AddAddressModel>() {
        @Override
        public AddAddressModel createFromParcel(Parcel in) {
            return new AddAddressModel(in);
        }

        @Override
        public AddAddressModel[] newArray(int size) {
            return new AddAddressModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(user_id);
        dest.writeString(userName);
        dest.writeString(phone);
        dest.writeString(street1);
        dest.writeString(street2);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zipCode);
    }
}
