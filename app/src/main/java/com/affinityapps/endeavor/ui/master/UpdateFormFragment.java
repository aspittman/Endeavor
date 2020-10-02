package com.affinityapps.endeavor.ui.master;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.databinding.FragmentUpdateFormBinding;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_DATE;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_HOURS;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_ID;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_MILES;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_ORGANIZATION;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_PROJECT;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_PURCHASES;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_TITLE;

public class UpdateFormFragment extends Fragment {

    public static final String TAG = AddFormActivity.class.getSimpleName();
    public static final String DATABASE_PATH = "user_forms";

    private DatabaseReference databaseForms;
    private FragmentUpdateFormBinding binding;
    private EditText editTitle;
    private EditText editOrganization;
    private EditText editProject;
    private EditText editDate;
    private EditText editHours;
    private EditText editMiles;
    private EditText editPurchases;


    public UpdateFormFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUpdateFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AdLoader adLoader = new AdLoader.Builder(requireActivity(), "ca-app-pub-2999844319459986~8520610002")
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.colorBlack));
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().withMainBackgroundColor(colorDrawable).build();

                        TemplateView template = binding.updateFormTemplate;
                        template.setStyles(styles);
                        template.setNativeAd(unifiedNativeAd);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());

        databaseForms = FirebaseDatabase.getInstance().getReference(DATABASE_PATH);
        Button updateFormButton = binding.updateNoteButton;
        editTitle = binding.editDocumentTitle;
        editOrganization = binding.editOrganization;
        editProject = binding.editProject;
        editDate = binding.editDate;
        editHours = binding.editHours;
        editMiles = binding.editMiles;
        editPurchases = binding.editPurchases;

        assert getArguments() != null;
        final String id = getArguments().getString(EXTRA_ID);
        String title = getArguments().getString(EXTRA_TITLE);
        String organization = getArguments().getString(EXTRA_ORGANIZATION);
        String project = getArguments().getString(EXTRA_PROJECT);
        String date = getArguments().getString(EXTRA_DATE);
        String hours = getArguments().getString(EXTRA_HOURS);
        String miles = getArguments().getString(EXTRA_MILES);
        String purchases = getArguments().getString(EXTRA_PURCHASES);

        editTitle.setText(title);
        editOrganization.setText(organization);
        editProject.setText(project);
        editDate.setText(date);
        editHours.setText(hours);
        editMiles.setText(miles);
        editPurchases.setText(purchases);

        updateFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDatabase(id);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateDatabase(String id) {
        String title = editTitle.getText().toString().trim();
        String organization = editOrganization.getText().toString().trim();
        String project = editProject.getText().toString().trim();
        String date = editDate.getText().toString().trim();
        String hours = editHours.getText().toString().trim();
        String miles = editMiles.getText().toString().trim();
        String purchases = editPurchases.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(getActivity(), "Please insert Document Title", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(organization)) {
            Toast.makeText(getActivity(), "Please insert Organization", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(project)) {
            Toast.makeText(getActivity(), "Please insert Project name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(date)) {
            Toast.makeText(getActivity(), "Please insert Date", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(hours)) {
            Toast.makeText(getActivity(), "Please insert Hours", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(miles)) {
            Toast.makeText(getActivity(), "Please insert Miles", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(purchases)) {
            Toast.makeText(getActivity(), "Please insert Purchases", Toast.LENGTH_SHORT).show();
        } else {
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
            Toast.makeText(getActivity(), "Document Updated", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }
}
