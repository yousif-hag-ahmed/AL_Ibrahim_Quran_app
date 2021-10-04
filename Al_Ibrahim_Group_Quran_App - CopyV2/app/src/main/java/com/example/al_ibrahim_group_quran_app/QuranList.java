package com.example.al_ibrahim_group_quran_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class QuranList extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener{
    RecyclerViewAdapter adapter;
    HizbClass hizb;
    ArrayList<HizbClass> hizbNum, hizb2list;
    RecyclerView recyclerView;
    CheckBox PendingBox;
    //    TextView enterName;
    private String Name = "Yousif";
    String groupName,password;
    Intent intent;
    FirebaseDatabase database;
    DatabaseReference myRef;
    HashMap<String, Object> theList;
    Button readMeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_list);
        theList = new HashMap<>();
        hizbNum = new ArrayList<>();


        intent= getIntent();
        groupName = intent.getStringExtra("Group");
        Log.i("Quran message" , groupName);
        password = intent.getStringExtra("Password");
        Log.i("Quran message" , password);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(groupName);
        myRef.child(password).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() ==null) {
                    startActivity(new Intent(QuranList.this,MainActivity.class));
                }else{
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                            hizb = dataSnapshot.getValue(HizbClass.class);
//                            theList.put(dataSnapshot.getKey() , dataSnapshot.getValue());


                            hizbNum.add(hizb);

                        }
                    }

                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.i("Quran message123" , String.valueOf(hizbNum.size()));
        recyclerView   = findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(hizbNum,this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        Log.i("Quran message123" , String.valueOf(hizbNum.size()));
    }

    @Override
    public void onItemClick(View view, int position) {



        hizb = hizbNum.get(position) ;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(groupName);

        if (hizb.getPendingState() == false && hizb.getState()==false){
            Intent intent = new Intent(QuranList.this,QuranRead.class);
            intent.putExtra("hizbNum",position);
            startActivity(intent);
            hizb.setPendingState(true);
            hizb.setPersonAssigned(Name);

//            theList.put(String.valueOf(position), hizb);
//            Log.i("Quran message123" , String.valueOf(theList));

            myRef.child(password).child(String.valueOf(position)).child("personAssigned").setValue(Name );
            myRef.child(password).child(String.valueOf(position)).child("pendingState").setValue(true);


        }else if (hizb.getPendingState() == true && hizb.getState()==false) {
            hizb.setPendingState(false);
            hizb.setState(true);

            myRef.child(password).child(String.valueOf(position)).child("state").setValue(true);

            myRef.child(password).child(String.valueOf(position)).child("pendingState").setValue(false);
        } else if (hizb.getState()==true){
            hizb.setPendingState(false);
            hizb.setState(false);
            hizb.setPersonAssigned(null);
            myRef.child(password).child(String.valueOf(position)).child("state").setValue(false);
            myRef.child(password).child(String.valueOf(position)).child("personAssigned").setValue("");
            myRef.child(password).child(String.valueOf(position)).child("pendingState").setValue(false);
        }



        hizbNum.set(position,hizb);
        adapter.notifyDataSetChanged();


    }
}