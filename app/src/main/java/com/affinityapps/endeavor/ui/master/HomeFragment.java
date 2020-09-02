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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.endeavor.databinding.FragmentHomeBinding;
import com.affinityapps.endeavor.ui.master.model.Master;
import com.affinityapps.endeavor.ui.master.model.MasterViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.affinityapps.endeavor.ui.master.FormActivity.DATABASE_PATH;
import static com.affinityapps.endeavor.ui.master.MainActivity.ADD_FORM_REQUEST;


public class HomeFragment extends Fragment {

    public static final String TAG = HomeFragment.class.getSimpleName();
    public static final String EXTRA_ID = "dataId";
    public static final String EXTRA_TITLE = "dataTitle";
    public static final String EXTRA_ORGANIZATION = "dataOrganization";
    public static final String EXTRA_PROJECT = "dataProject";
    public static final String EXTRA_DATE = "dataDate";
    public static final String EXTRA_HOURS = "dataHours";
    public static final String EXTRA_MILES = "dataMiles";
    public static final String EXTRA_PURCHASES = "dataPurchases";

    private DatabaseReference databaseForms;
    private Context context;
    private Master volunteer;
    private RecyclerView recyclerView;
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

        databaseForms = FirebaseDatabase.getInstance().getReference(DATABASE_PATH);

        recyclerView = binding.homeFragmentRecyclerview;
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseForms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                homeFragmentArrayList.clear();

                for (DataSnapshot formSnapshot : snapshot.getChildren()) {
                    Master master = formSnapshot.getValue(Master.class);

                    homeFragmentArrayList.add(master);
                }
                homeAdapter = new HomeAdapter(context, homeFragmentArrayList);
                recyclerView.setAdapter(homeAdapter);
                homeAdapter.setHomeFragmentClickListener(new HomeAdapter.HomeFragmentClickListener() {
                    @Override
                    public void onHomeFragmentClick(int position) {
                        Intent intent = new Intent(getActivity(), FormActivity.class);
                        Master master = homeFragmentArrayList.get(position);

                        intent.putExtra(EXTRA_ID, master.getId());
                        intent.putExtra(EXTRA_TITLE, master.getDocumentTitle());
                        intent.putExtra(EXTRA_ORGANIZATION, master.getOrganization());
                        intent.putExtra(EXTRA_PROJECT, master.getProject());
                        intent.putExtra(EXTRA_DATE, master.getDate());
                        intent.putExtra(EXTRA_HOURS, master.getHours());
                        intent.putExtra(EXTRA_MILES, master.getMiles());
                        intent.putExtra(EXTRA_PURCHASES, master.getPurchases());

                        startActivity(intent);
                    }
                });
                ItemTouchHelper.Callback callback = new HomeItemTouchHelper(homeAdapter);
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
                homeAdapter.setTouchHelper(itemTouchHelper);
                itemTouchHelper.attachToRecyclerView(recyclerView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
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
