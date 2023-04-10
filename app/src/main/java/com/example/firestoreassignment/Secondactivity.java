package com.example.firestoreassignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Secondactivity extends AppCompatActivity {
    HelperAdapter2 helperAdapter2;
    RecyclerView recyclerViewSecond;
    private FirebaseAnalytics mFirebaseAnalytics;
    Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    int second = c.get(Calendar.SECOND);

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        recyclerViewSecond = findViewById(R.id.Secondactivity);
        screenTrack("second screen");
        recyclerViewSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEvent("recycler_View", "recyclerView1", "RecyclerView");
            }
        });


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        screenTrack("second Screen");
        recyclerViewSecond = findViewById(R.id.recycler_View);


        Log.e("hour", String.valueOf(hour));
        Log.e("minute", String.valueOf(minute));
        Log.e("second", String.valueOf(second));




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
        users.put("screenName", "second screen");


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
