package com.example.myapplication.ui.home;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    TextView tv;
    MediaPlayer mp;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = clickEvent(inflater, container);
        return view;
    }

    public View clickEvent(LayoutInflater inflater, ViewGroup container) {
        mp = MediaPlayer.create(getActivity(), R.raw.map);
        mp.setLooping(false);

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        tv = (TextView) v.findViewById(R.id.hellobutton);
        tv.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mp.isPlaying()) {
                    mp.start();
                    tv.setText("Stop");
                } else {
                    mp.pause();
                    tv.setText("Start");
                }

            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}