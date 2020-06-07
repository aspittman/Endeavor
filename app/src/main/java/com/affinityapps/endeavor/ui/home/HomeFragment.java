package com.affinityapps.endeavor.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.Volunteer;
import com.affinityapps.endeavor.model.Form;
import com.affinityapps.endeavor.model.FormViewModel;

import java.util.ArrayList;
import java.util.List;

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
        volunteerArrayList.add(new Volunteer("testTextMe!!!"));
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
}
