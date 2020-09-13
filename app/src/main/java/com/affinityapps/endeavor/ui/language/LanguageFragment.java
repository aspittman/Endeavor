package com.affinityapps.endeavor.ui.language;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.databinding.FragmentLanguageBinding;

public class LanguageFragment extends Fragment {

    FragmentLanguageBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLanguageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.textLanguages.setText("This is a test of the Language section");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
