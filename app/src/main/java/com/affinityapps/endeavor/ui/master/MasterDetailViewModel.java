package com.affinityapps.endeavor.ui.master;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MasterDetailViewModel extends ViewModel {
    private final MutableLiveData<String> data = new MutableLiveData<String>();

    public void setId(String id) {
        data.setValue(id);
    }

    public void setTitle(String title) {
        data.setValue(title);
    }

    public void setOrganization(String organization) {
        data.setValue(organization);
    }

    public void setProject(String project) {
        data.setValue(project);
    }

    public void setDate(String date) {
        data.setValue(date);
    }

    public void setHours(String hours) {
        data.setValue(hours);
    }

    public void setMiles(String miles) {
        data.setValue(miles);
    }

    public void setPurchases(String purchases) {
        data.setValue(purchases);
    }

    public LiveData<String> getData() {
        return data;
    }
}

