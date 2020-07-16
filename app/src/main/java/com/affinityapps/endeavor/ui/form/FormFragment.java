package com.affinityapps.endeavor.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.databinding.FragmentFormBinding;

public class FormFragment extends Fragment {

    private EditText editDocumentTitle;
    private EditText editOrganization;
    private EditText editProject;
    private EditText editDate;
    private EditText editHours;
    private EditText editMiles;
    private EditText editPurchases;
    private Button saveFormButton;


    private FragmentFormBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        editDocumentTitle = binding.editDocumentTitle;
        editOrganization = binding.editOrganization;
        editProject = binding.editProject;
        editDate = binding.editDate;
        editHours = binding.editHours;
        editMiles = binding.editMiles;
        editPurchases = binding.editPurchases;
        saveFormButton = binding.saveNoteButton;

        return rootView;
    }

    private void SaveForm() {
        String documentTitle = editDocumentTitle.getText().toString();
        String organization = editOrganization.getText().toString();
        String project = editProject.getText().toString();
        String date = editDate.getText().toString();
        int hours = editHours.getInputType();
        int miles = editMiles.getInputType();
        int purchases = editPurchases.getInputType();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
