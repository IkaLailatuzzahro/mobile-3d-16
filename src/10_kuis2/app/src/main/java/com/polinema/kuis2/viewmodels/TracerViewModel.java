package com.polinema.kuis2.viewmodels;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import com.polinema.kuis2.fragments.ListFragment;
import com.polinema.kuis2.fragments.ListFragmentDirections;
import com.polinema.kuis2.models.Alumni;

public class TracerViewModel  extends ViewModel {
    private final MutableLiveData<ArrayList<Alumni>> alumniList = new MutableLiveData<>(new ArrayList<Alumni>());

    public void setAlumni(Alumni alumni) {
        alumniList.getValue().add(alumni);
    }

    public String getAlumni() {
        StringBuilder result = new StringBuilder();
        for(Alumni a : alumniList.getValue()) {
            result.append("NIM : " + a.getNim())
                    .append("\n")
                    .append("Nama : " + a.getNama())
                    .append("\n")
                    .append("Prodi : " + a.getProdi())
                    .append("\n")
                    .append("Tahun Masuk : " + a.getTahunMasuk())
                    .append("\n")
                    .append("Tahun Lulus : " + a.getTahunLulus())
                    .append("\n\n");
        }
        return result.toString();
    }

    public void onAddClick(View view) {
        ListFragmentDirections.AlumniAction action = ListFragmentDirections.alumniAction(ListFragment.DATA_KEY);
        Navigation.findNavController(view).navigate(action);
        Log.d("clickbutton", "di click");
    }
}
