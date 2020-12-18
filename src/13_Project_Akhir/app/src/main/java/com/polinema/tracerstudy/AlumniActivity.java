package com.polinema.tracerstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class AlumniActivity extends AppCompatActivity implements ListView.OnItemClickListener{

    private ListView listView;
    private String JSON_STRING;
    private EditText etCari;
    private Button btCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        etCari = (EditText) findViewById(R.id.editCari);
        btCari = (Button) findViewById(R.id.btnCari);
        btCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cariData();
            }
        });
        getJSON();
    }

    private void cariData() {
        String cari = etCari.getText().toString();
        class CariMahasiswa extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AlumniActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showData(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_SEARCH,cari);
                return s;
            }
        }
        CariMahasiswa cm = new CariMahasiswa();
        cm.execute();
    }

    private void showData(String json) {
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            for(int i = 0; i<result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String nim = jo.getString(Config.TAG_NIM);
                String nama = jo.getString(Config.TAG_NAMA);
                String prodi = jo.getString(Config.TAG_PRODI);
                String tahunMasuk = jo.getString(Config.TAG_TAHUNMASUK);
                String tahunLulus = jo.getString(Config.TAG_TAHUNLULUS);
                String judulSkripsi = jo.getString(Config.TAG_JUDULSKRIPSI);
                String email = jo.getString(Config.TAG_EMAIL);
                String alamat = jo.getString(Config.TAG_ALAMAT);
                String gambar = jo.getString(Config.TAG_GAMBAR);

                HashMap<String,String> alumni = new HashMap<>();
                alumni.put(Config.TAG_ID,id);
                alumni.put(Config.TAG_NIM,nim);
                alumni.put(Config.TAG_NAMA,nama);
                alumni.put(Config.TAG_PRODI,prodi);
                alumni.put(Config.TAG_TAHUNMASUK,tahunMasuk);
                alumni.put(Config.TAG_TAHUNLULUS,tahunLulus);
                alumni.put(Config.TAG_JUDULSKRIPSI,judulSkripsi);
                alumni.put(Config.TAG_EMAIL,email);
                alumni.put(Config.TAG_ALAMAT,alamat);
                alumni.put(Config.TAG_GAMBAR,gambar);
                list.add(alumni);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                AlumniActivity.this, list, R.layout.list_layout_alumni,
                new String[]{Config.TAG_NIM, Config.TAG_NAMA, Config.TAG_PRODI, Config.TAG_TAHUNMASUK, Config.TAG_TAHUNLULUS},
                new int[]{R.id.textNim, R.id.textNama, R.id.textProdi, R.id.textTahunMasuk, R.id.textTahunKeluar});

        listView.setAdapter(adapter);
    }


    private void showAlumni() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            for(int i = 0; i<result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String nim = jo.getString(Config.TAG_NIM);
                String nama = jo.getString(Config.TAG_NAMA);
                String prodi = jo.getString(Config.TAG_PRODI);
                String tahunMasuk = jo.getString(Config.TAG_TAHUNMASUK);
                String tahunLulus = jo.getString(Config.TAG_TAHUNLULUS);
                String judulSkripsi = jo.getString(Config.TAG_JUDULSKRIPSI);
                String email = jo.getString(Config.TAG_EMAIL);
                String alamat = jo.getString(Config.TAG_ALAMAT);
                String gambar = jo.getString(Config.TAG_GAMBAR);

                HashMap<String,String> alumni = new HashMap<>();
                alumni.put(Config.TAG_ID,id);
                alumni.put(Config.TAG_NIM,nim);
                alumni.put(Config.TAG_NAMA,nama);
                alumni.put(Config.TAG_PRODI,prodi);
                alumni.put(Config.TAG_TAHUNMASUK,tahunMasuk);
                alumni.put(Config.TAG_TAHUNLULUS,tahunLulus);
                alumni.put(Config.TAG_JUDULSKRIPSI,judulSkripsi);
                alumni.put(Config.TAG_EMAIL,email);
                alumni.put(Config.TAG_ALAMAT,alamat);
                alumni.put(Config.TAG_GAMBAR,gambar);
                list.add(alumni);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                AlumniActivity.this, list, R.layout.list_layout_alumni,
                new String[]{Config.TAG_NIM, Config.TAG_NAMA, Config.TAG_PRODI, Config.TAG_TAHUNMASUK, Config.TAG_TAHUNLULUS},
                new int[]{R.id.textNim, R.id.textNama, R.id.textProdi, R.id.textTahunMasuk, R.id.textTahunKeluar});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AlumniActivity.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showAlumni();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String mhsId = map.get(Config.TAG_ID);
        intent.putExtra(Config.MHS_ID,mhsId);
        startActivity(intent);
    }

}