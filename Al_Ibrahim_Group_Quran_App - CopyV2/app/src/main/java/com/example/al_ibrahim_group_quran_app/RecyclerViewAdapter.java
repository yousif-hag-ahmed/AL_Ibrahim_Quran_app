package com.example.al_ibrahim_group_quran_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<HizbClass> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;


    // data is passed into the constructor


    public RecyclerViewAdapter(List<HizbClass> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.from(parent.getContext())
                .inflate(R.layout.recycler_rows,parent,false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HizbClass hizbClass = mData.get(position);
        holder.hizbName.setText(hizbClass.getHizmName());
        holder.checkBox.setChecked(hizbClass.getState());
        holder.checkPending.setChecked(hizbClass.getPendingState());
        holder.editName.setText(hizbClass.getPersonAssigned());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public  TextView hizbName;
        public CheckedTextView checkBox;
        public CheckedTextView checkPending;
        public TextView editName;
        public Button readMeButton;

        ViewHolder(View itemView) {
            super(itemView);
            hizbName = itemView.findViewById(R.id.hizbText);
            checkBox = itemView.findViewById(R.id.row_readBox);
            checkPending = itemView.findViewById(R.id.row_Pending);
            editName = itemView.findViewById(R.id.row_enterYourName);
            readMeButton = itemView.findViewById(R.id.readMeButton);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return String.valueOf(mData.get(id));
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

