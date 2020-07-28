package com.affinityapps.endeavor.ui.master;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.affinityapps.endeavor.databinding.ActivityFormBinding;

public class FormActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_ORGANIZATION = "organization";
    public static final String EXTRA_PROJECT = "project";
    public static final String EXTRA_DATE = "date";
    public static final String EXTRA_HOURS = "hours";
    public static final String EXTRA_MILES = "miles";
    public static final String EXTRA_PURCHASES = "purchases";

    private EditText editDocumentTitle;
    private EditText editOrganization;
    private EditText editProject;
    private EditText editDate;
    private EditText editHours;
    private EditText editMiles;
    private EditText editPurchases;
    private Button saveFormButton;
    private ActivityFormBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        editDocumentTitle = binding.editDocumentTitle;
        editOrganization = binding.editOrganization;
        editProject = binding.editProject;
        editDate = binding.editDate;
        editHours = binding.editHours;
        editMiles = binding.editMiles;
        editPurchases = binding.editPurchases;
        saveFormButton = binding.saveNoteButton;

        saveFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveForm();
            }
        });
    }

    private void saveForm() {
        String documentTitle = editDocumentTitle.getText().toString();
        String organization = editOrganization.getText().toString();
        String project = editProject.getText().toString();
        String date = editDate.getText().toString();
        double hours = editHours.getInputType();
        double miles = editMiles.getInputType();
        double purchases = editPurchases.getInputType();

        if(documentTitle.trim().isEmpty() && organization.trim().isEmpty() && project.trim().isEmpty()) {
            Toast.makeText(this, "Please insert Title, Organization, or Project", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, documentTitle);
        data.putExtra(EXTRA_ORGANIZATION, organization);
        data.putExtra(EXTRA_PROJECT, project);
        data.putExtra(EXTRA_DATE, date);
        data.putExtra(EXTRA_HOURS, hours);
        data.putExtra(EXTRA_MILES, miles);
        data.putExtra(EXTRA_PURCHASES, purchases);

        setResult(RESULT_OK, data);
        finish();
    }
}
