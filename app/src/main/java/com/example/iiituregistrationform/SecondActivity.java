package com.example.iiituregistrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.iiituregistrationform.MainActivity.databaseReference;

public class SecondActivity extends AppCompatActivity {

    EditText editText1 , editText2 , editText3 , editText4;

    TextView textView1 , textView2 , textView3 , textView4 , textView5;

    FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference userRef;



    public void submit(View view){


        String cgpa = editText1.getText().toString();
        String branch = editText2.getText().toString();
        String college = editText3.getText().toString();
        String director = editText4.getText().toString();

        Map<String , Object> dataToSave = new HashMap<String, Object>();

            dataToSave.put("CGPA" , cgpa);

            dataToSave.put("Branch" , branch);

            dataToSave.put("College" , college);

            dataToSave.put("Director", director);

            if(cgpa.isEmpty() || branch.isEmpty() || college.isEmpty() || director.isEmpty() ){

                Toast.makeText(SecondActivity.this , "Please fill in the above info" , Toast.LENGTH_SHORT).show();
            }

            
            else {

            String userID = firebaseAuth.getCurrentUser().getUid();

            userRef = firebaseDatabase.getReference().child("Users").child(userID);

            userRef.updateChildren(dataToSave);

            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

            startActivity(intent);


        }}





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();



        Intent intent = getIntent();

        editText1 = (EditText) findViewById(R.id.cgpaEditText);
        editText2 = (EditText) findViewById(R.id.branchEditText);
        editText3 = (EditText) findViewById(R.id.collegeEditText);
        editText4 = (EditText) findViewById(R.id.directorEditText);


        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);













    }

}