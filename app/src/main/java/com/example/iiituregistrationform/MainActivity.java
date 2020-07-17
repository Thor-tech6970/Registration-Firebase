package com.example.iiituregistrationform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;

    static  FirebaseDatabase firebaseDatabase;

    FirebaseAuth firebaseAuth;

     static  DatabaseReference databaseReference, userReference;

    public void register(View view) {

        String email = emailEditText.getText().toString();

        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {

            return;

        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                FirebaseUser user = authResult.getUser();

                user.sendEmailVerification();


                Toast.makeText(MainActivity.this, "Account Created Successfully!  UserID : " +  user.getUid() +  "  Verfied : "  +  user.isEmailVerified(), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        //Toast.makeText(MainActivity.this , "New account created successfully" , Toast.LENGTH_SHORT).show();*/

    }


    public void login(View view) {

        final String email = emailEditText.getText().toString();

        final String password = passwordEditText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {

            return;

        }
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = authResult.getUser();
                if (user.isEmailVerified()) {

                   // Toast.makeText(MainActivity.this, " Login Successful! UID :  " +  authResult.getUser().getUid(), Toast.LENGTH_SHORT).show();

                    Map<String, Object> dataToSave = new HashMap<String, Object>();


                    dataToSave.put("Email", email);

                    dataToSave.put("Password", password);

                    String UserID = user.getUid();

                    userReference = databaseReference.child(UserID);

                    userReference.setValue(dataToSave);


                    Intent intent = new Intent(MainActivity.this , SecondActivity.class);

                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Login UnSuccessful! You need to verify your email first. ", Toast.LENGTH_SHORT).show();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        String UserID = email.replace("@", "_").replace(".", "_");
//
//        userReference = databaseReference.child(UserID);
//
//        userReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                if (dataSnapshot.exists()) {
//
//                    String emailFromDatabase = dataSnapshot.child("Email").getValue(String.class);
//
//                    String passwordFromDatabase = dataSnapshot.child("Password").getValue(String.class);
//
//                    if (email.equals(emailFromDatabase) && !password.equals(passwordFromDatabase)) {
//
//                        Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
//
//                    } else if (email.equals(emailFromDatabase) && password.equals(passwordFromDatabase)) {
//
//                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//
//                    }
//                 else {
//
//                    Toast.makeText(MainActivity.this, "User not registered", Toast.LENGTH_SHORT).show();
//
//         }
//    }
//
//}
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = (EditText) findViewById(R.id.emailEditText);

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        firebaseDatabase = FirebaseDatabase.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            //startActivity(intent);
        }

        databaseReference = firebaseDatabase.getReference().child("Users");


    }
}