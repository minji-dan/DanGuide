package com.example.app1_botnavi.ui.indoor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.app1_botnavi.databinding.FragmentIndoorBinding;
import com.example.app1_botnavi.databinding.FragmentIndoorBinding;

public class IndoorFragment extends Fragment {

private FragmentIndoorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        IndoorViewModel indoorViewModel =
                new ViewModelProvider(this).get(IndoorViewModel.class);

    binding = FragmentIndoorBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textIndoor;
        indoorViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}