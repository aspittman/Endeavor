package com.affinityapps.endeavor.ui.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.databinding.FragmentAboutBinding;
import com.affinityapps.endeavor.databinding.FragmentStatisticsBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {

    FragmentStatisticsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        BarChart barChart = binding.dataInputBarChart;

        ArrayList<BarEntry> inputGraph = new ArrayList<>();
        inputGraph.add(new BarEntry(2000, 765));
        inputGraph.add(new BarEntry(2001, 234));
        inputGraph.add(new BarEntry(2002, 543));
        inputGraph.add(new BarEntry(2003, 654));
        inputGraph.add(new BarEntry(2004, 435));
        inputGraph.add(new BarEntry(2005, 634));
        inputGraph.add(new BarEntry(2006, 567));

        BarDataSet barDataSet = new BarDataSet(inputGraph, "testData");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("First Graph :D");
        barChart.animateY(2000);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
