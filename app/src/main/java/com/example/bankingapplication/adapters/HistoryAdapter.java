package com.example.bankingapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapplication.R;
import com.example.bankingapplication.models.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<History> listdata;

    // RecyclerView recyclerView;
    public HistoryAdapter(ArrayList<History> listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_history_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvAccountName.setText(listdata.get(position).getSubscriptionNumber());
        holder.tvAccountnumber.setText("$" + listdata.get(position).getAmountPaid());

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAccountName, tvAccountnumber;
        ImageButton btnInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvAccountName = (TextView) itemView.findViewById(R.id.tvAccountName);
            this.tvAccountnumber = (TextView) itemView.findViewById(R.id.tvAccountnumber);
        }
    }
}
