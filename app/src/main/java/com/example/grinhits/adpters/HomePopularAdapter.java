package com.example.grinhits.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.grinhits.R;
import com.example.grinhits.models.CategoryModel;
import com.example.grinhits.models.PopularModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomePopularAdapter extends RecyclerView.Adapter<HomePopularAdapter.MyViewHolder> {

    private final Context context;
    private List<PopularModel> list=new ArrayList<>();

    public HomePopularAdapter(Context context, List<PopularModel> list) {
        this.context = context;
        this.list=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_popular_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePopularAdapter.MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).getName());
        holder.price.setText(context.getString(R.string.rupees)+list.get(position).getPrice());
        holder.weight.setText(list.get(position).getWeight());
        Picasso.get().load(list.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if (list.size()> 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView image;
        TextView title,price,weight;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.item_image_iv);
            title=itemView.findViewById(R.id.popular_item_tv);
            price=itemView.findViewById(R.id.price_tv);
            weight=itemView.findViewById(R.id.weight_tv);
        }
    }
}
