package com.example.grinhits.ui;

import android.content.SharedPreferences;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grinhits.Utils.SharedPreferenceUtils;

public class BaseActivity extends AppCompatActivity {


    void makeFullScreanActivity()
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS , WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

   public void showToast(String msg)
   {
       Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
   }
}
