package com.example.matteo.app1;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ValueEventListener {

    private TextView headingText;
    private EditText headingInput;
    private RadioButton rbRed, rbBlue;
    //prendamo un'istanza
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //prendaimo la root
    protected DatabaseReference mRootRef = firebaseDatabase.getReference();
    //child node
    private DatabaseReference childReference = mRootRef.child("heading");
    private DatabaseReference childRef = mRootRef.child("key");
    private DatabaseReference mHeadingReference = mRootRef.child("heading");
    private DatabaseReference mFontColorRef = mRootRef.child("fontColor");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setGraphicItems();

        headingText.setText("Message appear here");


    }

    private void setGraphicItems() {
        headingInput = (EditText) findViewById(R.id.headingInput);
        headingText = (TextView) findViewById(R.id.msgTxt);
        rbRed = (RadioButton) findViewById(R.id.rbRed);
        rbBlue = (RadioButton) findViewById(R.id.rBlue);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //listener del cambio data
       /* childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                //display data
                headingText.setText(message);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        mHeadingReference.addValueEventListener(this);
        mFontColorRef.addValueEventListener(this);
    }

    public void submitHeading(View view) {
        String heading = headingInput.getText().toString();
        mHeadingReference.setValue(heading);
        headingInput.setText("");

    }

    public void onRadioButtonClicked(View view) {

        switch (view.getId()) {

            case R.id.rbRed:
                mFontColorRef.setValue("red");
                break;
            case R.id.rBlue:
                mFontColorRef.setValue("blue");
                break;
        }

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.getValue(String.class) != null) {
            String key = dataSnapshot.getKey();
            if (key.equals("heading")) {
                String heading = dataSnapshot.getValue(String.class);
                headingText.setText(heading);
            } else if (key.equals("fontcolor")) {
                String color = dataSnapshot.getValue(String.class);

                if (color.equals("red")) {
                    headingText.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
                    rbRed.setChecked(true);
                } else if (color.equals("blue")) {
                    headingText.setTextColor(ContextCompat.getColor(this, R.color.colorBlue));
                    rbBlue.setChecked(true);

                }
            }
        }


    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
