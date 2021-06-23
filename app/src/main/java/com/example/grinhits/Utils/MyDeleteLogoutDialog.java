package com.example.grinhits.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.grinhits.R;
import com.example.grinhits.databinding.YesNoDialogBinding;
import com.example.grinhits.fragments.ProfileFragment;
import com.example.grinhits.ui.HomeActivity;
import com.example.grinhits.ui.LoginActivity;

public class MyDeleteLogoutDialog  implements View.OnClickListener {

    private HomeActivity context;
    private ProfileFragment profileFragment;
    private AlertDialog dialog;
    private YesNoDialogBinding binding;
    private static MyDeleteLogoutDialog myDeleteLogoutDialog;
    private  String title;
    private  String desc;
    private  String from;
    private SharedPreferenceUtils sharePref;



    public MyDeleteLogoutDialog(HomeActivity context)
    {
        this.context=context;
        sharePref=SharedPreferenceUtils.getInstance(context);
    }

    public void openDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context,R.style.MyDialogTheme);
        LayoutInflater inflater=context.getLayoutInflater();
        binding=DataBindingUtil.inflate(inflater,R.layout.yes_no_dialog,null,false);
        builder.setView(binding.getRoot());
        builder.setCancelable(true);
        dialog=builder.create();
        setinItView();
        dialog.show();
    }

    public  void inItData(String title,String desc,String from)
    {
        this.title=title;
        this.desc=desc;
        this.from=from;
    }
    private void setinItView() {
        binding.titleTv.setText(title);
        binding.descTv.setText(desc);
        binding.noBtn.setOnClickListener(this);
        binding.yesBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.no_btn:
            {
                dialog.dismiss();
                break;
            }
            case R.id.yes_btn:
            {
               perFormDialogYes();
                break;
            }

        }
    }

    private void perFormDialogYes() {
        if(from.equals("Logout"))
        {
            if(sharePref.isLogin("login"))
            {
                sharePref.isLogin("login",false);
                sharePref.setProfileModel(Constants.PROFILE_MODEL,null);
                sharePref.setUserId(Constants.USER_ID,-1);
                Intent intent=new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                context.finish();
                Toast.makeText(context,"Logout Success",Toast.LENGTH_SHORT).show();
            }
        }else if(from.equals(context.getString(R.string.delete)))
        {
            context.deleteOnDialog();
        }else if(from.equals(context.getString(R.string.login)))
        {
            gotoLogin();
        }
        dialog.dismiss();
    }

    private void gotoLogin() {
        Intent intent=new Intent(context,LoginActivity.class);
        context.startActivity(intent);
        context.finish();
    }

    public interface CallDialog
    {
        void deleteOnDialog();
    }
}
