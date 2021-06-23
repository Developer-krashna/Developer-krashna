package com.example.grinhits.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import com.example.grinhits.R;


public class SplashViewPagerAdapter extends PagerAdapter {
    int[] images;
    private Context context;
    public SplashViewPagerAdapter(Context context, int[] images) {
        this.context=context;
        this.images=images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull  View view, @NonNull  Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_pager_item,container,false);
        ImageView imageView;
        imageView=view.findViewById(R.id.item_bg);
        imageView.setImageDrawable(ContextCompat.getDrawable(context,images[position]));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull  ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View)object);
    }
}
