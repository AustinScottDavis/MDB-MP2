package com.ac.mdb_mp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ArrayList<Pokemon> pokemonDataList;
    ArrayList<Pokemon> filteredPokemon;
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

        Intent i = new Intent(ListActivity.this, SearchActivity.class);
        ListActivity.this.startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){

                //System.out.println(minHP);
                filteredPokemon = new ArrayList<Pokemon>();
                for (int i = 0; i < Utils.allPokemon.size(); i++) {
                    if (Utils.allPokemon.get(i).hp < Utils.minHPFilter) { continue; }
                    if (Utils.allPokemon.get(i).attack < Utils.minAtkFilter) { continue; }
                    if (Utils.allPokemon.get(i).defense < Utils.minDefFilter) { continue; }
                    if (Utils.allPokemon.get(i).spAttack < Utils.minSpecAtkFilter) { continue; }
                    if (Utils.allPokemon.get(i).spDefense < Utils.minSpecDefFilter) { continue; }
                    if (Utils.allPokemon.get(i).speed < Utils.minSpeedFilter) { continue; }
                    if (Utils.allPokemon.get(i).total < Utils.minTotalFilter) { continue; }
                    if (Utils.firstType != "") {
                        if (!Utils.allPokemon.get(i).type.contains(Utils.firstType)) { continue; }
                    }
                    if (Utils.secondType != "") {
                        if (!Utils.allPokemon.get(i).type.contains(Utils.secondType)) { continue; }
                    }
                    filteredPokemon.add(Utils.allPokemon.get(i));
                }

                pokemonDataList = filteredPokemon;
                pokeRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                pokeRecyclerView.setHasFixedSize(true);
                pokeLayoutManager = new LinearLayoutManager(this);

                pokeRecyclerView.setLayoutManager(pokeLayoutManager);
                //pokeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                pokeAdapter = new PokeAdapter(getApplicationContext(), pokemonDataList);
                pokeRecyclerView.setAdapter(pokeAdapter);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

}
