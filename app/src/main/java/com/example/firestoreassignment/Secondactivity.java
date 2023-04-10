package com.example.firestoreassignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Secondactivity extends AppCompatActivity {
    HelperAdapter2 helperAdapter2;
    RecyclerView recyclerViewSecond;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        FetchData fetchData=(FetchData) bundle.getSerializable("key");
        recyclerViewSecond.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(fetchData.getAdress().toString());
        arrayList.add(fetchData.getTitle().toString());
        arrayList.add(fetchData.getImage().toString());

        helperAdapter2 = new HelperAdapter2(arrayList);
        recyclerViewSecond.setAdapter(helperAdapter2);


    }
}
