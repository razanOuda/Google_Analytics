package com.example.firestoreassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HelperAdapter extends RecyclerView.Adapter {
    List<FetchData> fetchData;

    public HelperAdapter(List<FetchData> fetchData) {
        this.fetchData=fetchData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_detilse,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        FetchData fetchDataList = fetchData.get(position);
        viewHolderClass.textview.setText(fetchDataList.getTitle());
        viewHolderClass.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Secondactivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("key",fetchDataList);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fetchData.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView textview;


        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.maintextview);
        }
    }
}
