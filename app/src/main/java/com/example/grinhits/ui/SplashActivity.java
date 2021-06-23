package com.example.grinhits.ui;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.grinhits.R;
import com.example.grinhits.Utils.SharedPreferenceUtils;
import com.example.grinhits.adpters.SplashViewPagerAdapter;
import com.example.grinhits.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity implements View.OnClickListener {

   private ActivitySplashBinding binding;
   private ViewPager viewPager;
   private int[] images={R.drawable.pager1,R.drawable.pager2,R.drawable.pager3,R.drawable.pager4};
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreanActivity();
        super.onCreate(savedInstanceState);
        initControl();
       setViewPager();
    }

    private void initControl() {
        binding= DataBindingUtil.setContentView(SplashActivity.this, R.layout.activity_splash);
        binding.skipTv.setOnClickListener(this);
        binding.getStartedBtn.setOnClickListener(this);
        if(SharedPreferenceUtils.getInstance(this).isLogin(getString(R.string.isLogin)))
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    performG0ToHomeStarted();
                }
            },1000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.skip_tv:
            {

            }
            case R.id.get_started_btn:
            {
                if(SharedPreferenceUtils.getInstance(this).isLogin(getString(R.string.isLogin)))
                {
                    performG0ToHomeStarted();
                }else
                {
                    performGetStarted();
                }
            }

        }
    }

    private void performGetStarted() {
        Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void performG0ToHomeStarted() {
        Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    void setViewPager()
    {
        SplashViewPagerAdapter adpter=new SplashViewPagerAdapter(this,images);
        binding.viewPagerBg.setAdapter(adpter);
    }
}