package com.affinityapps.endeavor.ui.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.affinityapps.endeavor.databinding.FragmentPurchasesGraphBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PurchasesGraphFragment extends Fragment {
    private FragmentPurchasesGraphBinding binding;

    public PurchasesGraphFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPurchasesGraphBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        BarChart barChart = binding.purchasesBarChart;

        ArrayList<BarEntry> inputGraph = new ArrayList<>();
        inputGraph.add(new BarEntry(2000, 756));
        inputGraph.add(new BarEntry(2001, 456));
        inputGraph.add(new BarEntry(2002, 543));
        inputGraph.add(new BarEntry(2003, 534));
        inputGraph.add(new BarEntry(2004, 234));
        inputGraph.add(new BarEntry(2005, 657));
        inputGraph.add(new BarEntry(2006, 234));

        BarDataSet barDataSet = new BarDataSet(inputGraph, "testData");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Purchases Graph :D");
        barChart.animateY(2000);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
