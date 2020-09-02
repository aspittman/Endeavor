package com.affinityapps.endeavor.ui.master;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.affinityapps.endeavor.databinding.ActivityFormBinding;
import com.affinityapps.endeavor.ui.master.model.Master;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.affinityapps.endeavor.ui.master.HomeFragment.EXTRA_DATE;
import static com.affinityapps.endeavor.ui.master.HomeFragment.EXTRA_HOURS;
import static com.affinityapps.endeavor.ui.master.HomeFragment.EXTRA_MILES;
import static com.affinityapps.endeavor.ui.master.HomeFragment.EXTRA_ORGANIZATION;
import static com.affinityapps.endeavor.ui.master.HomeFragment.EXTRA_PROJECT;
import static com.affinityapps.endeavor.ui.master.HomeFragment.EXTRA_PURCHASES;
import static com.affinityapps.endeavor.ui.master.HomeFragment.EXTRA_TITLE;

public class FormActivity extends AppCompatActivity {

    public static final String DATABASE_PATH = "user_forms";
    public static final String TAG = FormActivity.class.getSimpleName();
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

        Intent intent = getIntent();

        String title = intent.getStringExtra(EXTRA_TITLE);
        String organization = intent.getStringExtra(EXTRA_ORGANIZATION);
        String project = intent.getStringExtra(EXTRA_PROJECT);
        String date = intent.getStringExtra(EXTRA_DATE);
        String hours = intent.getStringExtra(EXTRA_HOURS);
        String miles = intent.getStringExtra(EXTRA_MILES);
        String purchases = intent.getStringExtra(EXTRA_PURCHASES);

        editTitle.setText(title);
        editOrganization.setText(organization);
        editProject.setText(project);
        editDate.setText(date);
        editHours.setText(hours);
        editMiles.setText(miles);
        editPurchases.setText(purchases);

        saveFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDatabase();
            }
        });
    }

    private void sendToDatabase() {
        String title = editTitle.getText().toString().trim();
        String organization = editOrganization.getText().toString().trim();
        String project = editProject.getText().toString().trim();
        String date = editDate.getText().toString().trim();
        String hours = editHours.getText().toString().trim();
        String miles = editMiles.getText().toString().trim();
        String purchases = editPurchases.getText().toString().trim();

        if (!TextUtils.isEmpty(title)) {
            String id = databaseForms.push().getKey();
            Master master = new Master(id, title, organization, project, date, hours, miles, purchases);

            assert id != null;
            databaseForms.child(id).setValue(master).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                        Log.d(TAG, "onComplete: ");
                    else
                        Log.d(TAG, "no onComplete: ");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + e.getMessage());
                }
            });
            Toast.makeText(this, "Operation Successful", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please insert Document Title", Toast.LENGTH_SHORT).show();
        }
    }
}

