package com.example.app1_botnavi.ui.indoor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IndoorViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public IndoorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
