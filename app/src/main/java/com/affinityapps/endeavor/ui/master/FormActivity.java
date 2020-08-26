package com.affinityapps.endeavor.ui.master;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.affinityapps.endeavor.databinding.ActivityFormBinding;
import com.affinityapps.endeavor.ui.master.model.Master;
import com.affinityapps.endeavor.ui.master.model.MasterViewModel;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormActivity extends AppCompatActivity {

    public static final String DATABASE_PATH = "user_forms";
    private DatabaseReference databaseForms;

    private EditText editTitle;
    private EditText editOrganization;
    private EditText editProject;
    private EditText editDate;
    private EditText editHours;
    private EditText editMiles;
    private EditText editPurchases;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityFormBinding binding = ActivityFormBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        databaseForms = FirebaseDatabase.getInstance().getReference(DATABASE_PATH);
        Button saveFormButton = binding.saveNoteButton;
        editTitle = binding.editDocumentTitle;
        editOrganization = binding.editOrganization;
        editProject = binding.editProject;
        editDate = binding.editDate;
        editHours = binding.editHours;
        editMiles = binding.editMiles;
        editPurchases = binding.editPurchases;

        saveFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDatabase();
            }
        });
    }

    private void sendToDatabase() {
        String title = editTitle.getText().toString();
        String organization = editOrganization.getText().toString();
        String project = editProject.getText().toString();
        String date = editDate.getText().toString();
        String hours = editHours.getText().toString();
        String miles = editMiles.getText().toString();
        String purchases = editPurchases.getText().toString();

        if (!TextUtils.isEmpty(title)) {
            String id = databaseForms.push().getKey();
            Master master = new Master(id, title, organization, project, date, hours, miles, purchases);

            assert id != null;
            databaseForms.child(id).setValue(master);
            Toast.makeText(this, "Operation Successful", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please insert Title", Toast.LENGTH_SHORT).show();
        }
    }
}

