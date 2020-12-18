package com.polinema.tracerstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    ImageButton imgBtnAlumni;
    ImageButton imgBtnInfo;
    ImageButton imgBtnKonfirmasi;
    ImageButton imgBtnLacak;
    SharedPreferences sharedpreferences;

    String id, username;
    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBtnAlumni = (ImageButton) findViewById(R.id.btnAlumni);
        imgBtnKonfirmasi = (ImageButton) findViewById(R.id.btnKonfirmasi);
        imgBtnInfo = (ImageButton) findViewById(R.id.btnInfo);
        imgBtnLacak = (ImageButton) findViewById(R.id.btnLacak);

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);
    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }

    public void clickAlumni(View view) {
        Intent i = new Intent(MainActivity.this, AlumniActivity.class);
        startActivity(i);
    }

    public void clickKonfirmasi(View view) {
        Intent i = new Intent(MainActivity.this, ConfirmActivity.class);
        i.putExtra(Config.MHS_ID, id);
        startActivity(i);
    }

    public void clickInfo(View view) {
        Intent i = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(i);
    }

    public void clickLacak(View view) {
        Intent i = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(i);
    }
}