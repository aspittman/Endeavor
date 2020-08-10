package com.affinityapps.endeavor.ui.master;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.databinding.FragmentHomeBinding;
import com.affinityapps.endeavor.ui.master.model.Master;
import com.affinityapps.endeavor.ui.master.model.MasterViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.affinityapps.endeavor.ui.master.MainActivity.ADD_FORM_REQUEST;


public class HomeFragment extends Fragment {

    private Context context;
    private Master volunteer;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HomeAdapter homeAdapter;
    private List<Master> homeFragmentArrayList;
    private MasterViewModel masterViewModel;
    private FragmentHomeBinding binding;


    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        homeFragmentArrayList = new ArrayList<>();
        homeFragmentArrayList.add(new Master("1", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf"));
        homeFragmentArrayList.add(new Master("1", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf"));
        homeFragmentArrayList.add(new Master("1", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf"));
        homeFragmentArrayList.add(new Master("1", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf"));
        homeFragmentArrayList.add(new Master("1", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf", "dskdjfhasf"));

        recyclerView = binding.homeFragmentRecyclerview;
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        homeAdapter = new HomeAdapter(context, homeFragmentArrayList);
        recyclerView.setAdapter(homeAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        masterViewModel = new ViewModelProvider(requireActivity()).get(MasterViewModel.class);
//        masterViewModel.getMasterList().observe(getViewLifecycleOwner(), new Observer<List<Master>>() {
//            @Override
//            public void onChanged(List<Master> masters) {
//                homeAdapter.notifyDataSetChanged();
//            }
//        });
//    }

    // Read from the database
//myRef.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            // This method is called once with the initial value and again
//            // whenever data at this location is updated.
//            String value = dataSnapshot.getValue(String.class);
//            Log.d(TAG, "Value is: " + value);
//        }
//
//        @Override
//        public void onCancelled(DatabaseError error) {
//            // Failed to read value
//            Log.w(TAG, "Failed to read value.", error.toException());
//        }
//    });
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_FORM_REQUEST && resultCode == RESULT_OK) {

            Toast.makeText(getActivity(), "Form Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Form didn't Save", Toast.LENGTH_SHORT).show();
        }
    }
}
