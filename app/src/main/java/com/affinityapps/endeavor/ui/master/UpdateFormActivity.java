package com.affinityapps.endeavor.ui.master;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.databinding.ActivityUpdateFormBinding;

import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_DATE;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_HOURS;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_ID;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_MILES;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_ORGANIZATION;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_PROJECT;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_PURCHASES;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_TITLE;

public class UpdateFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUpdateFormBinding binding = ActivityUpdateFormBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        UpdateFormFragment fragment = new UpdateFormFragment();
        Bundle bundle = new Bundle();
        Intent intent = getIntent();
        bundle.putString(EXTRA_ID, intent.getStringExtra(EXTRA_ID));
        bundle.putString(EXTRA_TITLE, intent.getStringExtra(EXTRA_TITLE));
        bundle.putString(EXTRA_ORGANIZATION, intent.getStringExtra(EXTRA_ORGANIZATION));
        bundle.putString(EXTRA_PROJECT, intent.getStringExtra(EXTRA_PROJECT));
        bundle.putString(EXTRA_DATE, intent.getStringExtra(EXTRA_DATE));
        bundle.putString(EXTRA_HOURS, intent.getStringExtra(EXTRA_HOURS));
        bundle.putString(EXTRA_MILES, intent.getStringExtra(EXTRA_MILES));
        bundle.putString(EXTRA_PURCHASES, intent.getStringExtra(EXTRA_PURCHASES));
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit();
    }
}
