package com.example.app1_botnavi.ui.settings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.app1_botnavi.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

    binding = FragmentSettingsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textSettings;
        settingsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
//public class SettingsFragment extends Fragment {
//
//    public SettingsFragment(){
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater,
//            ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.Setting_Fragment, container, false);
//        String[] values = new String[] {"이동순위", "음성안내", "b", "c"};
//        ListView listView = (ListView) view.findViewById(R.id.SettingFragment);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                getActivity(), android.R.layout.simple_list_item_1, values);
//
//        listView.setAdapter(adapter);
//
//        return view;
//    }
//}

