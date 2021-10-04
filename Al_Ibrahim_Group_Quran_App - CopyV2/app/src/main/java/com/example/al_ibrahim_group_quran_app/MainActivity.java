package com.example.al_ibrahim_group_quran_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button createNow ,searchNow;
    Intent intent;
    HizbClass hizb;
    private EditText searchPassword, createPassword, searchGroup, createGroup;
    String searchPassword1,searchGroup1;
    private static final String TAG = "AfterPass";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchPassword = findViewById(R.id.searchPassword);
        searchGroup = findViewById(R.id.searchGroup);
        createGroup = findViewById(R.id.createGroup);
        createPassword = findViewById(R.id.createPassword);
        TextView textView = (TextView) findViewById(R.id.textView5);
        searchGroup1= searchGroup.getText().toString();
        searchPassword1 = searchPassword.getText().toString();

        searchNow = findViewById(R.id.searchNow);

        searchNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String searchGroup1= searchGroup.getText().toString();
//                String searchPassword1 = searchPassword.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(searchGroup1);

                myRef.child(searchPassword1).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getValue()!=null) {
                            intent=new  Intent(MainActivity.this , QuranList.class);
                            intent.putExtra("Group",searchGroup1);
                            intent.putExtra("Password",searchPassword1);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        createNow = findViewById(R.id.createNow);

        createNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String createGroup1 = createGroup.getText().toString();
                String createPassword1 = createPassword.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(createGroup1);

                HashMap<String, Object> theList = new HashMap<>();
                for (int i =0;  i < 60; i++) {
                    int count = i+1;
                    hizb = new HizbClass("Hizb  "+count ,i, false ,false , "");
                    theList.put(String.valueOf(i),hizb);
                    myRef.child(createPassword1).updateChildren(theList);
                    theList.clear();
                }
                intent=new  Intent(MainActivity.this , QuranList.class);

                intent.putExtra("Group",createGroup1);
                intent.putExtra("Password",createPassword1);
                startActivity(intent);

            }
        });

        // Write a message to the database



    }
}
//    }
//}
//
//            for (int i = 1; i < 61; i++) {
//        theList.put("Read" + i, false);
//        myRef.child(createPassword1).child("hizb" + i).updateChildren(theList);
//        theList.clear();
//        }