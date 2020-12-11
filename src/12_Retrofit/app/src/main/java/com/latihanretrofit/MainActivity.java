package com.latihanretrofit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.latihanretrofit.databinding.ActivityMainBinding;
import com.latihanretrofit.models.Repo;
import com.latihanretrofit.services.GitHubServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private GitHubServices services;
    private AlertDialog.Builder errorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         services = retrofit.create(GitHubServices.class);
    }

    public void cariUser(View view){
        String username = binding.userEdit.getText().toString();
        binding.loading.setVisibility(View.VISIBLE);

        Call<List<Repo>> repos = services.listRepos(username);
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Repo repo = response.body().get(0);
                binding.setRepo(repo);
                binding.loading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                errorDialog = new AlertDialog.Builder(MainActivity.this);
                errorDialog.setTitle("Gagal").setMessage(t.getMessage());
                errorDialog.setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = errorDialog.create();
                alertDialog.show();
            }
        });
    }
}