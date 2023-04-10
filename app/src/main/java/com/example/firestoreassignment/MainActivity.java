package com.example.firestoreassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<FetchData> fetchData;
    HelperAdapter helperAdapter;
    DatabaseReference databaseReference ;
    private FirebaseAnalytics mFirebaseAnalytics;
    Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    int second = c.get(Calendar.SECOND);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        recyclerView = findViewById(R.id.note);
        screenTrack("Home screen");


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        screenTrack("Home Screen");
        recyclerView = findViewById(R.id.note);


        Log.e("hour", String.valueOf(hour));
        Log.e("minute", String.valueOf(minute));
        Log.e("second", String.valueOf(second));

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEvent("recycler_View", "recyclerView1", "RecyclerView");
                btnEvent("recycler_View2", "recyclerView2", "RecyclerView");
                btnEvent("recycler_View3", "recyclerView3", "RecyclerView");


            }
        });


        recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchData = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("razan");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    FetchData fetchData = ds.getValue(FetchData.class);
                    //fetchData.add(fetchDataList);
                }
                helperAdapter = new HelperAdapter(fetchData);
                recyclerView.setAdapter(helperAdapter);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void btnEvent(String id, String name, String contentType) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public void screenTrack(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Main Activity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }


    @Override
    protected void onPause() {
        Calendar c = Calendar.getInstance();
        int hour2 = c.get(Calendar.HOUR_OF_DAY);
        int minute2 = c.get(Calendar.MINUTE);
        int second2 = c.get(Calendar.SECOND);

        int h = hour2 - hour;
        int m = minute2 - minute;
        int s = second2 - second;


        Log.e("hour", String.valueOf(h));
        Log.e("minute", String.valueOf(m));
        Log.e("second", String.valueOf(s));

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        HashMap<String, Object> users = new HashMap<>();
        users.put("hours", h);
        users.put("minute", m);
        users.put("second", s);
        users.put("screenName", "Home screen");


        db.collection("razan")
                .add(users)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        super.onPause();
    }


    }