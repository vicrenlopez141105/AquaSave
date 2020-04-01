package com.vicrenstudios.aquasave;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

import static com.vicrenstudios.aquasave.R.id.fragmentGalleryContainerId;
import static com.vicrenstudios.aquasave.R.id.text;

public class GalleryActivity extends AppCompatActivity {

    private LinearLayout linearGaleria, linearOmitir;
    private GalleryFirstFragment fragmentoUno;
    private GallerySecondFragment fragmentoDos;
    private GalleryThirdFragment fragmentoTres;
    private int firstTouchX, firstTouchY;
    static int galleryPosition = 1;
    private ImageView treepoints;
    private TextView toolbarId, tvtitle, tvsubtitle, tvomitir;
    //Ajenos
    private TextView text1, text2, text3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        linearGaleria = (LinearLayout) findViewById(R.id.linearGalleryId);
        linearOmitir = (LinearLayout) findViewById(R.id.linearOmitirId);
        fragmentoUno = new GalleryFirstFragment();
        fragmentoDos = new GallerySecondFragment();
        fragmentoTres = new GalleryThirdFragment();
        treepoints = (ImageView) findViewById(R.id.actualPhotoId);
        toolbarId = (TextView)findViewById(R.id.toolbarTVId);
        tvtitle = (TextView)findViewById(R.id.tvTitleId);
        tvsubtitle = (TextView)findViewById(R.id.tvSubtitleId);
        tvomitir = (TextView)findViewById(R.id.tvOmitirId);


        //Asignamos las fuentes
        toolbarId.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-BlackItalic.ttf"));
        tvtitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"));
        tvsubtitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf"));
        tvomitir.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"));


        //Iniciamos el fragment
        getSupportFragmentManager().beginTransaction().add(fragmentGalleryContainerId, fragmentoUno).commit();

        listeners();
    }

    private void listeners() {

        linearGaleria.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //Guardamos las coordenadas del primer toque
                        firstTouchX = (int) motionEvent.getX();
                        firstTouchY = (int) motionEvent.getY();
                        break;

                    case MotionEvent.ACTION_MOVE: //Determinamos si se produjo un swipe hacia la derecha
                        if (firstTouchX > motionEvent.getX()) {//Se produjo
                            switch (galleryPosition) {
                                case 1: //Recorremos la lista
                                    transaction.replace(fragmentGalleryContainerId, fragmentoDos); //Si la posicion actual de la galeria es 1
                                    galleryPosition = 2;        //Pasamos a la imagen 2
                                    transaction.commit();
                                    treepoints.setImageResource(R.drawable.gallerysecondselected);
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    transaction.replace(fragmentGalleryContainerId, fragmentoTres);//Si la posicion actual de la galeria es 2
                                    galleryPosition = 3; //Pasamos a la imagen 3
                                    transaction.commit();
                                    treepoints.setImageResource(R.drawable.gallerythirdselected);
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 3:
                                    transaction.replace(fragmentGalleryContainerId, fragmentoUno);//Si la posicion actual de la galeria es 3
                                    galleryPosition = 1; //Pasamos a la imagen 1
                                    transaction.commit();
                                    treepoints.setImageResource(R.drawable.galleryfirstselected);
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                            }
                        }
                        if (firstTouchX < motionEvent.getX()) {
                            switch (galleryPosition) {
                                case 1: //Recorremos la lista
                                    transaction.replace(fragmentGalleryContainerId, fragmentoTres); //Si la posicion actual de la galeria es 1
                                    galleryPosition = 3;        //Pasamos a la imagen 3
                                    treepoints.setImageResource(R.drawable.gallerythirdselected);
                                    transaction.commit();
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    transaction.replace(fragmentGalleryContainerId, fragmentoUno);//Si la posicion actual de la galeria es 2
                                    galleryPosition = 1; //Pasamos a la imagen 1
                                    treepoints.setImageResource(R.drawable.galleryfirstselected);
                                    transaction.commit();
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 3:
                                    transaction.replace(fragmentGalleryContainerId, fragmentoDos);//Si la posicion actual de la galeria es 3
                                    galleryPosition = 2; //Pasamos a la imagen 2
                                    treepoints.setImageResource(R.drawable.gallerysecondselected);
                                    transaction.commit();
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                            }
                        }
                        break;

                }
                return true;
            }
        });

        linearOmitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GalleryActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
