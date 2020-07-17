package com.example.iiituregistrationform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FourthActivity extends AppCompatActivity {

    Intent intent  = getIntent();


    FirebaseDatabase firebaseDatabase;

    FirebaseAuth firebaseAuth;

    DatabaseReference userReference;

    TextView textView1 , textView2 , textView3 , textView4 , textView5;

    public void home(View view){


        Intent intent = new Intent(FourthActivity.this , MainActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);

        firebaseDatabase = FirebaseDatabase.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();

        final String userID =  firebaseAuth.getCurrentUser().getUid();

        userReference =   firebaseDatabase.getReference().child("Users").child(userID);

        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String emailFromDatabase = dataSnapshot.child("Email").getValue(String.class);
                    String cgpaFromDatabase = dataSnapshot.child("CGPA").getValue(String.class);
                    String branchFromDatabase = dataSnapshot.child("Branch").getValue(String.class);
                    String collegeFromDatabase = dataSnapshot.child("College").getValue(String.class);
                    String directorFromDatabase = dataSnapshot.child("Director").getValue(String.class);

                    textView1.setText(" Email :" +emailFromDatabase);

                    textView2.setText("CGPA :" +cgpaFromDatabase);

                    textView3.setText("Branch :" +branchFromDatabase);

                    textView4.setText("College rating :" +collegeFromDatabase);

                    textView5.setText("Director rating :" +directorFromDatabase);


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}