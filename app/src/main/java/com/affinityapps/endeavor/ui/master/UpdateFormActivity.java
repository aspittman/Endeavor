package com.affinityapps.endeavor.ui.master;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.databinding.ActivityUpdateFormBinding;

public class UpdateFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUpdateFormBinding binding = ActivityUpdateFormBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        UpdateFormFragment fragment = new UpdateFormFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit();
    }
}
