package com.affinityapps.endeavor.ui.master;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.endeavor.R;

import java.util.ArrayList;
import java.util.List;

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder> {

    private List<Form> volunteerArrayList = new ArrayList<>();
    private VolunteerClickListener volunteerClickListener;


    public interface VolunteerClickListener {
        void onVolunteerClick(int position);
    }

    public void setVolunteerClickListener(VolunteerClickListener listener) {
        volunteerClickListener = listener;
    }

    public void setForms(List<Form> volunteerArrayList) {
        this.volunteerArrayList = volunteerArrayList;
        notifyDataSetChanged();
    }

    public static class VolunteerViewHolder extends RecyclerView.ViewHolder {

        private TextView volunteerTitle;
        private TextView volunteerOrganization;
        private TextView volunteerProject;
        private TextView volunteerDate;
        private TextView volunteerHours;
        private TextView volunteerMiles;
        private TextView volunteerPurchases;

        public VolunteerViewHolder(View itemView, final VolunteerClickListener listener) {
            super(itemView);

            volunteerTitle = itemView.findViewById(R.id.volunteer_title);
            volunteerOrganization = itemView.findViewById(R.id.volunteer_organization);
            volunteerProject = itemView.findViewById(R.id.volunteer_project);
            volunteerDate = itemView.findViewById(R.id.volunteer_date);
            volunteerHours = itemView.findViewById(R.id.volunteer_hours);
            volunteerMiles = itemView.findViewById(R.id.volunteer_miles);
            volunteerPurchases = itemView.findViewById(R.id.volunteer_purchases);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onVolunteerClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public VolunteerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.volunteer_list_items, parent, false);

        return new VolunteerViewHolder(view, volunteerClickListener);
    }

    @Override
    public void onBindViewHolder(VolunteerViewHolder holder, int position) {

        Form volunteer = volunteerArrayList.get(position);

        holder.volunteerTitle.setText(volunteer.getDocumentTitle());
        holder.volunteerOrganization.setText(volunteer.getOrganization());
        holder.volunteerProject.setText(volunteer.getProject());
        holder.volunteerDate.setText(volunteer.getDate());
        holder.volunteerHours.setText(String.valueOf(volunteer.getHours()));
        holder.volunteerMiles.setText(String.valueOf(volunteer.getMiles()));
        holder.volunteerPurchases.setText(String.valueOf(volunteer.getPurchases()));

    }

    @Override
    public int getItemCount() {
        return volunteerArrayList.size();
    }
}

