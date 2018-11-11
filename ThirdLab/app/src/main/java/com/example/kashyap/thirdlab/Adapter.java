package com.example.kashyap.thirdlab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.VH> {


    ArrayList<UserClass> AL;
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View V = inflater.inflate(R.layout.rv,parent,false);
        VH viewholder = new VH(V);
        return viewholder;
    }

    public Adapter(ArrayList AL){
        this.AL = AL;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.name.setText(AL.get(position).getName());
        holder.phone.setText(new String(""+AL.get(position).getPhone()));
    }

    @Override
    public int getItemCount() {
        return AL.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        TextView name, phone, labelname, labelphone;
        public VH(View itemView) {
            super(itemView);

            labelname = itemView.findViewById(R.id.textView);
            labelphone = itemView.findViewById(R.id.textView4);
            name = itemView.findViewById(R.id.itemname);
            phone = itemView.findViewById(R.id.itemphone);
        }
    }
}
