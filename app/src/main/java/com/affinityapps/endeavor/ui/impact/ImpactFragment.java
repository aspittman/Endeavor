package com.affinityapps.endeavor.ui.impact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.databinding.FragmentImpactBinding;

public class ImpactFragment extends Fragment {

    FragmentImpactBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentImpactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.textImpact.setText("This is a test of the Impact Fragment");
        return root;
    }
}
