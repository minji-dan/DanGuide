package com.example.myapplication.ui.outdoor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentOutdoorBinding;

import net.daum.mf.map.api.MapView;

public class OutdoorFragment extends Fragment {

    private FragmentOutdoorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        OutdoorViewModel dashboardViewModel =
//                new ViewModelProvider(this).get(OutdoorViewModel.class);
//
//        binding = FragmentDashboardBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        MapView mapView = new MapView(getActivity());

        View v = inflater.inflate(R.layout.fragment_outdoor, container, false);

        ViewGroup mapViewContainer = (ViewGroup) v.findViewById(R.id.MapView);
        mapViewContainer.addView(mapView);
        return v;
    }
}