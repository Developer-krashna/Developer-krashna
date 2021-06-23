package com.example.grinhits.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grinhits.R;
import com.example.grinhits.Utils.SharedPreferenceUtils;
import com.example.grinhits.databinding.ProfileAddressItemBinding;
import com.example.grinhits.models.AddAddressModel;

import java.util.ArrayList;
import java.util.List;

public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.MyViewHolder> {

    ProfileAddressItemBinding binding;
    private List<AddAddressModel> list = new ArrayList<>();
    private final Context context;
    private final OnAddressItemClicListener listener;


    public MyAddressAdapter(Context context, OnAddressItemClicListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_address_item, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressAdapter.MyViewHolder holder, int position) {
        holder.address_name.setText(list.get(position).getUserName());
        holder.address_desc.setText(list.get(position).getState() + "," + list.get(position).getCity() + "," + list.get(position).getStreet1() + "," + list.get(position).getStreet2() + "," + list.get(position).getZipCode());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddressItemClick(list.get(position));
            }
        });

        holder.edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceUtils.getInstance(context).isEditButton("edit",true);
                listener.onAddressItemClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    public void setAddressList(List<AddAddressModel> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnAddressItemClicListener {
        void onAddressItemClick(AddAddressModel model);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView address_name;
        TextView address_desc;
        ImageView delete;
        TextView edit_address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            address_name = itemView.findViewById(R.id.address_name_tv);
            address_desc = itemView.findViewById(R.id.address_ades_tv);
            delete = itemView.findViewById(R.id.delete_item_iv);
            edit_address = itemView.findViewById(R.id.edit_address);
        }
    }
}
