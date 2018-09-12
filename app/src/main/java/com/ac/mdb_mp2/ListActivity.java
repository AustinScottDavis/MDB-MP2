package com.ac.mdb_mp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {
    //ArrayList<Pokemon> currentList;
    private RecyclerView pokeRecyclerView;
    private RecyclerView.Adapter pokeAdapter;
    private RecyclerView.LayoutManager pokeLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        pokeRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pokeRecyclerView.setHasFixedSize(true);
        pokeLayoutManager = new LinearLayoutManager(this);
        pokeRecyclerView.setLayoutManager(pokeLayoutManager);
        //pokeAdapter = new ()
    }



}
