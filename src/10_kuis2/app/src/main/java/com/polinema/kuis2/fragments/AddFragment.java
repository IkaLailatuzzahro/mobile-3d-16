package com.polinema.kuis2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.polinema.kuis2.R;
import com.polinema.kuis2.models.Alumni;
import com.polinema.kuis2.databinding.FragmentAddBinding;
import com.polinema.kuis2.viewmodels.TracerViewModel;

public class AddFragment extends Fragment {
    private TracerViewModel viewModel;
    private String requestKey;
    private Alumni alumni;

    public AddFragment(){}

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        this.alumni = new Alumni();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentAddBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false);

        binding.setFragment(this);
        binding.setAlumni(alumni);
        requestKey = AddFragmentArgs.fromBundle(getArguments()).getRequestKey();
        viewModel = new ViewModelProvider(requireActivity()).get(TracerViewModel.class);
        return binding.getRoot();
    }

    public void onSaveClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ListFragment.DATA_KEY, alumni);

        viewModel.setAlumni(alumni);
        Navigation.findNavController(view).navigateUp();
    }

    public void onCancelClicked(View view) {
        Navigation.findNavController(view).navigateUp();
    }
}
