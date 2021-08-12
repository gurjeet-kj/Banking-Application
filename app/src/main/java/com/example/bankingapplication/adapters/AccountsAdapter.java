package com.example.bankingapplication.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapplication.R;
import com.example.bankingapplication.models.AccountsModel;

import java.util.ArrayList;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {
    private ArrayList<AccountsModel> listdata;

    // RecyclerView recyclerView;
    public AccountsAdapter(ArrayList<AccountsModel> listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_account_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvAccountName.setText(listdata.get(position).getAccountName());
        holder.tvAccountnumber.setText(listdata.get(position).getAccountNumber());
        holder.tvBalance.setText("$" +listdata.get(position).getBalance());

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAccountName, tvAccountnumber, tvBalance;
        ImageButton btnInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvAccountName = (TextView) itemView.findViewById(R.id.tvAccountName);
            this.tvAccountnumber = (TextView) itemView.findViewById(R.id.tvAccountnumber);
            this.tvBalance = (TextView) itemView.findViewById(R.id.tvBalance);

        }
    }


}
