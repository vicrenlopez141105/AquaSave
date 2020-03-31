package com.vicrenstudios.aquasave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class GalleryActivity extends AppCompatActivity {

    private GalleryFirstFragment fragmentoUno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        fragmentoUno = new GalleryFirstFragment();
        //Iniciamos el fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentGalleryContainerId, fragmentoUno).commit();

    }
}
