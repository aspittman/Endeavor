package com.affinityapps.endeavor.ui.master.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MasterViewModel extends ViewModel {

    private MutableLiveData<List<Master>> masterList;

    public void init() {

    }

    public LiveData<List<Master>> getMasterList() {
        return masterList;
    }
}
