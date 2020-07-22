package com.affinityapps.endeavor.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.Volunteer;
import com.affinityapps.endeavor.data.Form;
import com.affinityapps.endeavor.data.FormViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.affinityapps.endeavor.MainActivity.ADD_FORM_REQUEST;
import static com.affinityapps.endeavor.ui.form.FormActivity.EXTRA_DATE;
import static com.affinityapps.endeavor.ui.form.FormActivity.EXTRA_HOURS;
import static com.affinityapps.endeavor.ui.form.FormActivity.EXTRA_MILES;
import static com.affinityapps.endeavor.ui.form.FormActivity.EXTRA_ORGANIZATION;
import static com.affinityapps.endeavor.ui.form.FormActivity.EXTRA_PROJECT;
import static com.affinityapps.endeavor.ui.form.FormActivity.EXTRA_PURCHASES;
import static com.affinityapps.endeavor.ui.form.FormActivity.EXTRA_TITLE;

public class HomeFragment extends Fragment {

    private Context context;
    private Volunteer volunteer;
    private FormViewModel formViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private VolunteerAdapter volunteerAdapter;
    private ArrayList<Volunteer> volunteerArrayList;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        formViewModel = new ViewModelProvider(this).get(FormViewModel.class);
        formViewModel.getAllForms().observe(getViewLifecycleOwner(), new Observer<List<Form>>() {
            @Override
            public void onChanged(List<Form> forms) {
                Toast.makeText(getActivity(), "This is a Database test", Toast.LENGTH_LONG).show();
            }
        });
        volunteerArrayList = new ArrayList<>();
        volunteerArrayList.add(new Volunteer("testTextMe!!!"));
        volunteerArrayList.add(new Volunteer("testTextMe!!!"));
        volunteerArrayList.add(new Volunteer("testTextMe!!!"));

        recyclerView = root.findViewById(R.id.home_fragment_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        volunteerAdapter = new VolunteerAdapter(context, volunteerArrayList);
        recyclerView.setAdapter(volunteerAdapter);

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_FORM_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            String title = data.getStringExtra(EXTRA_TITLE);
            String organization = data.getStringExtra(EXTRA_ORGANIZATION);
            String project = data.getStringExtra(EXTRA_PROJECT);
            String date = data.getStringExtra(EXTRA_DATE);
            int hours = data.getIntExtra(EXTRA_HOURS, 0);
            int miles = data.getIntExtra(EXTRA_MILES, 0);
            int purchases = data.getIntExtra(EXTRA_PURCHASES, 0);

            Form form = new Form(title, organization, project, date, hours, miles, purchases);
            formViewModel.insert(form);

            Toast.makeText(getActivity(), "Form Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Form didn't Save", Toast.LENGTH_SHORT).show();
        }
    }
}
