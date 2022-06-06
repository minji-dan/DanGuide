package com.example.app1_botnavi.ui.outdoor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app1_botnavi.databinding.FragmentOutdoorBinding;

public class OutdoorFragment extends Fragment {

private FragmentOutdoorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        OutdoorViewModel outdoorViewModel =
                new ViewModelProvider(this).get(OutdoorViewModel.class);

    binding = FragmentOutdoorBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textOutdoor;
        outdoorViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
