package com.affinityapps.endeavor.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.endeavor.databinding.VolunteerListItemsBinding;
import com.affinityapps.endeavor.ui.master.Master;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeFragmentViewHolder>
        implements ItemTouchHelperAdapter {

    public static final String DATABASE_PATH = "user_forms";
    private Context context;
    private List<Master> homeFragmentArrayList;
    private ItemTouchHelper itemTouchHelper;
    private HomeFragmentClickListener listener;

    public HomeAdapter(Context context, List<Master> homeFragmentArrayList) {
        this.context = context;
        this.homeFragmentArrayList = homeFragmentArrayList;
    }

    public void setHomeFragmentClickListener(HomeFragmentClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Master fromMaster = homeFragmentArrayList.get(fromPosition);
        homeFragmentArrayList.remove(fromMaster);
        homeFragmentArrayList.add(toPosition, fromMaster);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSwiped(int position) {
        Master master = homeFragmentArrayList.get(position);
        DatabaseReference databaseForms = FirebaseDatabase.getInstance().getReference(DATABASE_PATH).child(master.getId());
        databaseForms.removeValue();
        notifyItemRemoved(position);
    }

    public void setTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    @NonNull
    @Override
    public HomeFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeFragmentViewHolder(VolunteerListItemsBinding.inflate(LayoutInflater.
                from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(HomeFragmentViewHolder holder, int position) {
        Master master = homeFragmentArrayList.get(position);

        holder.binding.volunteerTitle.setText(master.getDocumentTitle());
        holder.binding.volunteerDate.setText(master.getDate());
    }

    @Override
    public int getItemCount() {
        return homeFragmentArrayList.size();
    }

    public interface HomeFragmentClickListener {
        void onHomeFragmentClick(int position);
    }

    public class HomeFragmentViewHolder extends RecyclerView.ViewHolder
            implements View.OnTouchListener, GestureDetector.OnGestureListener {

        VolunteerListItemsBinding binding;
        GestureDetector gestureDetector;
        TextView volunteerTitle;
        TextView volunteerDate;

        public HomeFragmentViewHolder(VolunteerListItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            volunteerTitle = binding.volunteerTitle;
            volunteerDate = binding.volunteerDate;
            itemView.setOnTouchListener(this);
            gestureDetector = new GestureDetector(binding.getRoot().getContext(), this);
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onHomeFragmentClick(position);
                }
            }
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            itemTouchHelper.startDrag(this);
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            gestureDetector.onTouchEvent(motionEvent);
            return true;
        }
    }
}

