package com.affinityapps.endeavor.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FormViewModel extends AndroidViewModel {
    private FormRepository formRepository;
    private LiveData<List<Form>> allForms;

    public FormViewModel(@NonNull Application application) {
        super(application);
        formRepository = new FormRepository(application);
        allForms = formRepository.getAllForms();
    }

    public void insert(Form form) {
        formRepository.insert(form);
    }

    public void update(Form form) {
        formRepository.update(form);
    }

    public void delete(Form form) {
        formRepository.delete(form);
    }

    public void deleteAllForms() {
        formRepository.deleteAllForms();
    }

    public LiveData<List<Form>> getAllForms() {
        return allForms;
    }
}
