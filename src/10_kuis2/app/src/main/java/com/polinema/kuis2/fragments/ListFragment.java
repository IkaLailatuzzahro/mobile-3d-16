package com.polinema.kuis2.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.polinema.kuis2.R;
import com.polinema.kuis2.databinding.FragmentListBinding;
import com.polinema.kuis2.viewmodels.TracerViewModel;

public class ListFragment extends Fragment {
    public static final String DATA_KEY = "alumni";

    private TracerViewModel viewModel;

    public ListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);

        // instance viewmodel here
        binding.setFragment(this);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(requireActivity()).get(TracerViewModel.class);

        binding.setVm(viewModel);
        return binding.getRoot();
    }

    public void onAddClick(View view) {
        ListFragmentDirections.AlumniAction action = ListFragmentDirections.alumniAction(ListFragment.DATA_KEY);
        Navigation.findNavController(view).navigate(action);
        Log.d("clickbutton", "di click");
    }
}
