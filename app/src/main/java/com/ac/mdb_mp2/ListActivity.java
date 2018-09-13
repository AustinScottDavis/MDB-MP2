package com.ac.mdb_mp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ArrayList<Pokemon> pokemonDataList;
    private RecyclerView pokeRecyclerView;
    private RecyclerView.Adapter pokeAdapter;
    private RecyclerView.LayoutManager pokeLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        String pokemonData = getString(R.string.pokemonData); // fetch JSON string from file (too large to use as constant)
        Utils.parseJSON(pokemonData);

        pokemonDataList = Utils.allPokemon;
        pokeRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pokeRecyclerView.setHasFixedSize(true);
        pokeLayoutManager = new LinearLayoutManager(this);

        pokeRecyclerView.setLayoutManager(pokeLayoutManager);
        //pokeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        pokeAdapter = new PokeAdapter(getApplicationContext(), pokemonDataList);
        pokeRecyclerView.setAdapter(pokeAdapter);
    }
}
