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

public class ThirdActivity extends AppCompatActivity {

    Intent intent = getIntent();







public void home(View view){

    Intent intent = new Intent(ThirdActivity.this , MainActivity.class);

    startActivity(intent);


}

 public void responses(View view){

    Intent intent = new Intent(ThirdActivity.this , FourthActivity.class);

    startActivity(intent);

 }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);





































    }
}