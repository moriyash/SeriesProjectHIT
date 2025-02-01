package com.example.myapplicationrv.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationrv.R;
import com.example.myapplicationrv.models.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.myViewHolder> {

    private List<Data> arr;
    private List<Data> fullList; // full list
    private List<Data> filteredList; // filtered list
    public CustomeAdapter(ArrayList<Data> arr) {

        this.arr = arr;
        this.fullList = new ArrayList<>(arr);
        this.filteredList = new ArrayList<>(arr);
    }
    public class myViewHolder extends RecyclerView.ViewHolder {


        TextView username;
        TextView nameVersion;
        ImageView imageViewItem;

        public myViewHolder ( View itemView){
            super(itemView);
           username = itemView.findViewById(R.id.textName);
           nameVersion = itemView.findViewById(R.id.textVer);
           imageViewItem = itemView.findViewById(R.id.imageView);
        }

    }
    @NonNull
    @Override
    public CustomeAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview , parent , false ) ;

        myViewHolder MyViewHolder = new myViewHolder(view);

       return MyViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        Data character = filteredList.get(position);
        holder.username.setText(character.getName());
        holder.nameVersion.setText(character.getVersion());
        holder.imageViewItem.setImageResource(character.getImage());
        holder.itemView.setOnClickListener(v -> {
            String message = "name:" + character.getName() + "\n" +
                    "discription" + character.getVersion();
//toast
            Toast.makeText(holder.itemView.getContext(), message, Toast.LENGTH_LONG).show();
        });
    }

    public void filterList(String query) {
        filteredList.clear();

        if (query.isEmpty()) {
            filteredList.addAll(fullList);
        } else {
            for (Data item : fullList) {
                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        Log.d("FilterList", "Filtered items: " + filteredList.size());

        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return filteredList.size();
    }

}
