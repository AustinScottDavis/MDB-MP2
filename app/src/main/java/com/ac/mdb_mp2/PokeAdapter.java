package com.ac.mdb_mp2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeViewHolder> {
    Context context;
    ArrayList<String> data;

    public PokeAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public PokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_list, parent, false);
        return new PokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokeViewHolder holder, int position) {
        //holder.textView.setText(data.get(position).color);
        //Glide.with(context).load(data.get(position).urlToImage).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PokeViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public PokeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
