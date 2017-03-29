package com.example.matteo.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.matteo.app1.MyFirebaseInstaceService.REG_TOKEN;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    //prendamo un'istanza
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //prendaimo la root
    protected DatabaseReference rootReference = firebaseDatabase.getReference();
    //child node
    private DatabaseReference childReference = rootReference.child("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.msgTxt);
        textView.setText("Message appear here");




    }

    @Override
    protected void onStart() {
        super.onStart();
        //listener del cambio data
        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                //display data
                textView.setText(message);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
