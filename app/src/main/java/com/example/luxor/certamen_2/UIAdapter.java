package com.example.luxor.certamen_2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by luxor on 02-11-16.
 */

public class UIAdapter extends RecyclerView.Adapter<UIAdapter.ViewHolder> {
    private List<Repo> mDataset;
    private ItemClickListener clickListener;
    public UIAdapter(List<Repo> myDataset) {
        mDataset = myDataset;
    }
    public void setOnClickListener(ItemClickListener listener) {
        this.clickListener = listener;
    }


    @Override
    public UIAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_repo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repo rep = mDataset.get(position);

        holder.mTextView.setText(rep.getNombre());
        holder.mDescriptionView.setText(rep.getDescription());
        holder.mActualView.setText(rep.getActual());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTextView;
        public TextView mDescriptionView;
        public TextView mActualView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textName);
            mActualView = (TextView) v.findViewById(R.id.textActual);
            mDescriptionView = (TextView) v.findViewById(R.id.textDescription);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
