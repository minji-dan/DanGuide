package com.example.myapplication.ui.indoor;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentIndoorBinding;


public class IndoorFragment extends Fragment {

    private FragmentIndoorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        IndoorViewModel indoorViewModel =
                new ViewModelProvider(this).get(IndoorViewModel.class);

        binding = FragmentIndoorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final WebView webView = binding.indoorNavi;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://10.0.2.2:3000/indoor");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}