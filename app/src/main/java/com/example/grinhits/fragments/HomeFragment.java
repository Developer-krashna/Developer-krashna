package com.example.grinhits.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.grinhits.R;
import com.example.grinhits.Utils.MyContextCall;
import com.example.grinhits.adpters.HomeCategoryAdapter;
import com.example.grinhits.adpters.HomePopularAdapter;
import com.example.grinhits.databinding.FragmentHomeBinding;
import com.example.grinhits.models.CategoryModel;
import com.example.grinhits.models.PopularModel;
import com.example.grinhits.ui.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private Context context;
    FragmentHomeBinding binding;
    private HomeCategoryAdapter categoryAdapter;
    List<CategoryModel> category_list=new ArrayList<>();
    List<PopularModel> popular_list=new ArrayList<>();
    int[] images={R.drawable.item1,R.drawable.item2,R.drawable.item3,R.drawable.item4,R.drawable.item5,R.drawable.item6};
    String[] names={"Italian","Chines","Maxican","Soudi","Indian","American"};
    String[] prices={"50.00","100.00","30.00","40.00","23.00","54.00"};
    String[] weights={"500gm","600gm","300gm","400gm","230gm","540gm"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=(HomeActivity)context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }





    private void initView() {
        category_list.clear();
        popular_list.clear();
        for(int i=0;i<names.length;i++)
        {
            category_list.add(new CategoryModel(images[i],names[i]));
            popular_list.add(new PopularModel(images[i],names[i],prices[i],weights[i]));
        }
        setUpCategoryAdapter();
        setUppopularAdapter();
    }

    private void setUppopularAdapter() {
        binding.popularItemRv.setLayoutManager(new LinearLayoutManager(context));
        HomePopularAdapter popularAdapter =new HomePopularAdapter(context,popular_list);
        binding.popularItemRv.setAdapter(popularAdapter);
        binding.popularItemRv.setNestedScrollingEnabled(false);
        popularAdapter.notifyDataSetChanged();
    }

    private void setUpCategoryAdapter() {
        binding.categoryRv.setLayoutManager(new GridLayoutManager(context,3));
        categoryAdapter=new HomeCategoryAdapter(context,category_list);
        binding.categoryRv.setAdapter(categoryAdapter);
        binding.categoryRv.setNestedScrollingEnabled(false);
        categoryAdapter.notifyDataSetChanged();
    }
}