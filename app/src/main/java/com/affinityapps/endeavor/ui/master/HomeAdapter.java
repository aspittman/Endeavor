package com.affinityapps.endeavor.ui.master;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.databinding.VolunteerListItemsBinding;
import com.affinityapps.endeavor.ui.master.model.Master;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeFragmentViewHolder> {

    private Context context;
    private List<Master> homeFragmentArrayList;
    private HomeFragmentClickListener homeFragmentClickListener;


    public interface HomeFragmentClickListener {
        void onHomeFragmentClick(int position);
    }

    public HomeAdapter(Context context, List<Master> homeFragmentArrayList) {
        this.context = context;
        this.homeFragmentArrayList = homeFragmentArrayList;
    }

    public void setHomeFragmentClickListener(HomeFragmentClickListener listener) {
        homeFragmentClickListener = listener;
    }

    public void setForms(List<Master> volunteerArrayList) {
        this.homeFragmentArrayList = volunteerArrayList;
        notifyDataSetChanged();
    }

    public static class HomeFragmentViewHolder extends RecyclerView.ViewHolder {

        VolunteerListItemsBinding binding;
        TextView volunteerTitle;
        TextView volunteerOrganization;
        TextView volunteerProject;
        TextView volunteerDate;
        TextView volunteerHours;
        TextView volunteerMiles;
        TextView volunteerPurchases;

        public HomeFragmentViewHolder(VolunteerListItemsBinding binding, final HomeFragmentClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;

            volunteerTitle = binding.volunteerTitle;
            volunteerOrganization = binding.volunteerOrganization;
            volunteerProject = binding.volunteerProject;
            volunteerDate = binding.volunteerDate;
            volunteerHours = binding.volunteerHours;
            volunteerMiles = binding.volunteerMiles;
            volunteerPurchases = binding.volunteerPurchases;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onHomeFragmentClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public HomeFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeFragmentViewHolder(VolunteerListItemsBinding.inflate(LayoutInflater.
                from(parent.getContext()), parent, false), homeFragmentClickListener);
    }

    @Override
    public void onBindViewHolder(HomeFragmentViewHolder holder, int position) {
        Master master = homeFragmentArrayList.get(position);

        holder.binding.volunteerTitle.setText(master.getDocumentTitle());
        holder.binding.volunteerOrganization.setText(master.getOrganization());
        holder.binding.volunteerProject.setText(master.getProject());
        holder.binding.volunteerDate.setText(master.getDate());
        holder.binding.volunteerHours.setText(String.valueOf(master.getHours()));
        holder.binding.volunteerMiles.setText(String.valueOf(master.getMiles()));
        holder.binding.volunteerPurchases.setText(String.valueOf(master.getPurchases()));
    }

    @Override
    public int getItemCount() {
        return homeFragmentArrayList.size();
    }
}

