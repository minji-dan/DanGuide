package com.example.app1_botnavi.ui.indoor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app1_botnavi.R;
import com.example.app1_botnavi.databinding.FragmentIndoorBinding;
import com.example.app1_botnavi.databinding.FragmentIndoorBinding;
import com.minew.beacon.BeaconValueIndex;
import com.minew.beacon.MinewBeacon;
import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.minew.beacon.MinewBeaconManagerListener;
import com.minew.beaconset.BluetoothState;
import com.minew.beaconset.ConnectionState;
import com.minew.beaconset.MinewBeaconConnection;
import com.minew.beaconset.MinewBeaconConnectionListener;
import com.minew.beaconset.MinewBeaconManager;

import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1_botnavi.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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