package com.polinema.tracerstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    Button btKembali;
    TextView tvNim, tvNama, tvProdi, tvTahunMasuk, tvTahunLulus, tvJudulSkripsi, tvEmail, tvAlamat;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.MHS_ID);

        tvNim = (TextView) findViewById(R.id.textNim);
        tvNama = (TextView) findViewById(R.id.textNama);
        tvProdi = (TextView) findViewById(R.id.textProdi);
        tvTahunMasuk = (TextView) findViewById(R.id.textTahunMasuk);
        tvTahunLulus = (TextView) findViewById(R.id.textTahunKeluar);
        tvJudulSkripsi = (TextView) findViewById(R.id.textJudulSkripsi);
        tvEmail = (TextView) findViewById(R.id.textEmail);
        tvAlamat = (TextView) findViewById(R.id.textAlamat);

        btKembali = (Button) findViewById(R.id.btnKembali);
        btKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getMahasiswa();
    }

    private void getMahasiswa() {
        class GetMahasiswa extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showAlumni(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ID,id);
                return s;
            }
        }
        GetMahasiswa gm = new GetMahasiswa();
        gm.execute();
    }

    private void showAlumni(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);

            String nim = c.getString(Config.TAG_NIM);
            String nama = c.getString(Config.TAG_NAMA);
            String prodi = c.getString(Config.TAG_PRODI);
            String tahunMasuk = c.getString(Config.TAG_TAHUNMASUK);
            String tahunLulus = c.getString(Config.TAG_TAHUNLULUS);
            String judulSkripsi = c.getString(Config.TAG_JUDULSKRIPSI);
            String email = c.getString(Config.TAG_EMAIL);
            String alamat = c.getString(Config.TAG_ALAMAT);
            String gambar = c.getString(Config.TAG_GAMBAR);

            tvNim.setText(nim);
            tvNama.setText(nama);
            tvProdi.setText(prodi);
            tvTahunMasuk.setText(tahunMasuk);
            tvTahunLulus.setText(tahunLulus);
            tvJudulSkripsi.setText(judulSkripsi);
            tvEmail.setText(email);
            tvAlamat.setText(alamat);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}