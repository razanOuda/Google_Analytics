package com.example.firestoreassignment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HelperAdapter2 extends RecyclerView.Adapter {
    List<String> fetchData;

    public HelperAdapter2(List<String> fetchData) {
        this.fetchData = fetchData;
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
        ViewHolderClass viewHolderClass = (ViewHolderClass) holder;
        final String fetchDataList = fetchData.get(position);
        viewHolderClass.textview.setText(fetchDataList);

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
