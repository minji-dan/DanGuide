package com.example.myapplication.ui.indoor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IndoorViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public IndoorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is indoor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}