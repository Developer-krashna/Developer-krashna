package com.example.grinhits.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.grinhits.Utils.Constants;

@Entity(tableName = Constants.PROFILE_MODEL)
public class ProfileModel implements Parcelable {
  @PrimaryKey(autoGenerate = true)
   private int user_id;

    private String first_name;
    private String last_name;
    private String phone_name;
    private String address;

  public ProfileModel(String first_name, String last_name, String phone_name, String address) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.phone_name = phone_name;
    this.address = address;
  }


  protected ProfileModel(Parcel in) {
    first_name = in.readString();
    last_name = in.readString();
    phone_name = in.readString();
    address = in.readString();
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public static Creator<ProfileModel> getCREATOR() {
    return CREATOR;
  }

  public static final Creator<ProfileModel> CREATOR = new Creator<ProfileModel>() {
    @Override
    public ProfileModel createFromParcel(Parcel in) {
      return new ProfileModel(in);
    }

    @Override
    public ProfileModel[] newArray(int size) {
      return new ProfileModel[size];
    }
  };

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getPhone_name() {
    return phone_name;
  }

  public void setPhone_name(String phone_name) {
    this.phone_name = phone_name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(first_name);
    dest.writeString(last_name);
    dest.writeString(phone_name);
    dest.writeString(address);
  }
}
