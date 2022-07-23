package com.example.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class transactionAdapter extends RecyclerView.Adapter<transactionAdapter.myViewHolder>{
    Context context;
    CardView x;

    ArrayList<TransactionModel> transactionModelArrayList;
    public transactionAdapter(Context context, ArrayList<TransactionModel> transactionModelArrayList) {
        this.context = context;
        this.transactionModelArrayList = transactionModelArrayList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_recycler_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        TransactionModel model = transactionModelArrayList.get(position);
        String priority = model.getType();

        if(priority.equals("Expense")){
            holder.priority.setBackgroundResource(R.color.red);
        }
        else{
            holder.priority.setBackgroundResource(R.color.green);
        }
//        System.out.println("Note "+ model.getNote());
//        System.out.println("Amount "+ model.getAmount());
//        System.out.println("Id "+ model.getId());
//        idk but why
//                getNote is returning amount
//                getId note
//
        holder.amount.setText(model.getNote());
        holder.note.setText(model.getId());
        holder.date.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        return transactionModelArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView amount, note, date;
        View priority;
        public myViewHolder(@NonNull View itemView){
            super(itemView);
            note = itemView.findViewById(R.id.note_one);
            amount = itemView.findViewById(R.id.amount_one);
            date = itemView.findViewById(R.id.date_one);
            priority = itemView.findViewById(R.id.priority);
        }
    }
}
