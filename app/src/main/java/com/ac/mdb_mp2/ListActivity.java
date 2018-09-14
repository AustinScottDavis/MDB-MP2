package com.ac.mdb_mp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ArrayList<Pokemon> pokemonDataList;
    ArrayList<Pokemon> filteredPokemon;
    private RecyclerView pokeRecyclerView;
    private RecyclerView.Adapter pokeAdapter;
    private RecyclerView.LayoutManager pokeLayoutManager;
    Button listGridSwitch;
    Button moreFiltersButton;
    Button clearButton;
    boolean grid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        String pokemonData = getString(R.string.pokemonData); // fetch JSON string from file (too large to use as constant)
        Utils.parseJSON(pokemonData);

        listGridSwitch = findViewById(R.id.listGridButton);
        moreFiltersButton = findViewById(R.id.moreFilters);
        clearButton = findViewById(R.id.clearButton);
        grid = false;

        //creating the recycler view
        pokemonDataList = Utils.allPokemon;
        pokeRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pokeRecyclerView.setHasFixedSize(true);
        pokeLayoutManager = new LinearLayoutManager(this);

        resetLayout();
        browseList();
    }

    //sets the layout to be either the list or grid view
    public void resetLayout() {
        if (!grid) {
            pokeRecyclerView.setLayoutManager(pokeLayoutManager);
        } else {
            pokeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        pokeAdapter = new PokeAdapter(getApplicationContext(), pokemonDataList);
        pokeRecyclerView.setAdapter(pokeAdapter);
    }

    public void browseList() {
        resetLayout();
        listGridSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid = !grid;
                if (grid) {
                    listGridSwitch.setText("Grid");
                } else {
                    listGridSwitch.setText("List");
                }

                resetLayout();
                //System.out.println(grid);
            }
        });
        moreFiltersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListActivity.this, SearchActivity.class);
                ListActivity.this.startActivityForResult(i, 1);
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pokemonDataList = Utils.allPokemon;
                pokeRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                pokeRecyclerView.setHasFixedSize(true);
                pokeLayoutManager = new LinearLayoutManager(ListActivity.this);

                resetLayout();
            }
        });
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

                resetLayout();
                browseList();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

    }

}
