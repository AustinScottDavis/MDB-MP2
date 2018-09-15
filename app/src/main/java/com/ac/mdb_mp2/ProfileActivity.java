package com.ac.mdb_mp2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // get info when screen opened
        Intent intent = getIntent();
        Pokemon currentPokemon = (Pokemon) intent.getSerializableExtra("pokemon");

        // fill all info
        TextView hp = findViewById(R.id.profileHP);
        hp.setText(getString(R.string.hp) + Integer.toString(currentPokemon.hp));

        TextView speed = findViewById(R.id.profileSpeed);
        speed.setText(getString(R.string.speed) + Integer.toString(currentPokemon.speed));

        TextView attack = findViewById(R.id.profileAttack);
        attack.setText(getString(R.string.attack) + Integer.toString(currentPokemon.attack));

        TextView defense = findViewById(R.id.profileDefense);
        defense.setText(getString(R.string.defense) + Integer.toString(currentPokemon.defense));

        TextView specialAttack = findViewById(R.id.profileSpecAtk);
        specialAttack.setText(getString(R.string.spAtk) + Integer.toString(currentPokemon.spAttack));

        TextView specialDefense = findViewById(R.id.profileSpecDef);
        specialDefense.setText(getString(R.string.spDef) + Integer.toString(currentPokemon.spDefense));

        TextView total = findViewById(R.id.profileTotal);
        total.setText(getString(R.string.total) + Integer.toString(currentPokemon.total));

        TextView name = findViewById(R.id.profileName);
        name.setText(currentPokemon.name);

        TextView number = findViewById(R.id.profileNumber);
        String type = currentPokemon.type.get(0);
        if (currentPokemon.type.size() == 2) {
            type = type + ", " + currentPokemon.type.get(1);
        }
        number.setText(Integer.toString(currentPokemon.number) + ": " + type);

        TextView species = findViewById(R.id.profileSpecies);
        species.setText(currentPokemon.species);

        TextView flavorText = findViewById(R.id.profileFlavor);
        flavorText.setText(currentPokemon.flavorText);


        String currentName = currentPokemon.name.trim().toLowerCase();
        String url = "https://img.pokemondb.net/artwork/" + currentName + ".jpg";
        ImageView profile = findViewById(R.id.profileImage);

        if (URLUtil.isValidUrl(url)) {
            Glide.with(profile.getContext()).load(url).into(profile);
        }

        if (currentName.contains("(")){
            Drawable filler = getResources().getDrawable(R.drawable.pokeball);
            profile.setImageDrawable(filler);
        }


        ImageView searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.searchButton:
                TextView nameText = findViewById(R.id.profileName);
                String escapedQuery = null;
                try {
                    escapedQuery = URLEncoder.encode(nameText.getText().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Uri uri = Uri.parse("http://www.google.com/#q=" + escapedQuery);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }
}
