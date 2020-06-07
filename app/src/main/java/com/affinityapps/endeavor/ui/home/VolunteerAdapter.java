package com.affinityapps.endeavor.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.Volunteer;

import java.util.ArrayList;

public class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder> {

    private ArrayList<Volunteer> volunteerArrayList;
    private Context context;
    private VolunteerClickListener volunteerClickListener;


    public interface VolunteerClickListener {
        void onVolunteerClick(int position);
    }

    public void setVolunteerClickListener(VolunteerClickListener listener) {
        volunteerClickListener = listener;
    }


    public VolunteerAdapter(Context context, ArrayList<Volunteer> volunteerArrayList) {
        this.volunteerArrayList = volunteerArrayList;
        this.context = context;
    }


    public static class VolunteerViewHolder extends RecyclerView.ViewHolder {

        private TextView volunteerTestText;


        public VolunteerViewHolder(View itemView, final VolunteerClickListener listener) {
            super(itemView);

            volunteerTestText = itemView.findViewById(R.id.volunteer_test_text);

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

        Volunteer volunteer = volunteerArrayList.get(position);

        holder.volunteerTestText.setText(volunteer.getTestText());
    }

    @Override
    public int getItemCount() {
        return volunteerArrayList.size();
    }
}

