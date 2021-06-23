package com.example.grinhits.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grinhits.models.CategoryModel;
import com.example.grinhits.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeCategoryAdapter  extends RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder> {

    private Context context;
    private List<CategoryModel> list;
    public HomeCategoryAdapter(Context context, List<CategoryModel> list)
    {
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.home_category_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoryAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(list.size()>0)
        {
            return list.size();
        }else
        {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        RoundedImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.item_image_iv);
            textView=itemView.findViewById(R.id.item_name_tv);
        }
    }

}
