package com.timebanking.firebase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> emaillist;
    ArrayList<String> namelist;


    class SearchViewHolder extends RecyclerView.ViewHolder{
        TextView name, email;
        Button select;


        public SearchViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            select = itemView.findViewById(R.id.btn_select);
            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), DescriptionActivity.class);
                    context.startActivity(myIntent);
                }

            });

        }
    }

    public SearchAdapter(Context context, ArrayList<String> emaillist, ArrayList<String> namelist) {
        this.context = context;
        this.emaillist = emaillist;
        this.namelist = namelist;
    }



    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items, parent, false);
        return new SearchAdapter.SearchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.name.setText(namelist.get(position));
        holder.email.setText(emaillist.get(position));


    }


    @Override
    public int getItemCount() {
        return namelist.size();
    }
}