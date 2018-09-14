package com.ac.mdb_mp2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeViewHolder> {
    Context context;
    ArrayList<Pokemon> data;

    public PokeAdapter(Context context, ArrayList<Pokemon> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public PokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_view, parent, false);
        return new PokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokeViewHolder holder, int position) {
        holder.textView.setText(data.get(position).name);
        String currentName = data.get(position).name.trim().toLowerCase();
        //System.out.println(currentName);
        String url = "https://img.pokemondb.net/artwork/" + currentName + ".jpg";
        if (URLUtil.isValidUrl(url)) {
            Glide.with(holder.imageView.getContext()).load(url).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class PokeViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public PokeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    //following two methods prevent the list from repeating
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}