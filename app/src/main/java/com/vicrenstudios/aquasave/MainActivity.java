package com.vicrenstudios.aquasave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.vicrenstudios.aquasave.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Corroboramos si es primera vez que se ejecuta la aplicación
        SharedPreferences preferences = getSharedPreferences("appPreferences", Context.MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFirstTime", true);

        if (isFirstTime) { //Si es primera vez que se ejecuta la aplicación, mandamos al usuario al activity de galeria
            SharedPreferences.Editor objEditor = preferences.edit();
            objEditor.putBoolean("isFirstTime", false); //Marcamos que ya no es primera vez
            objEditor.commit();

            Intent i = new Intent(MainActivity.this, GalleryActivity.class); //Iniciamos el activity de galeria
            startActivity(i);
            finish();
        }

    }
}
