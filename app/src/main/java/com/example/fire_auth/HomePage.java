package com.example.fire_auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity {

    TextView t1,t2;
    FirebaseDatabase fb;
    FirebaseAuth fauth;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        fauth = FirebaseAuth.getInstance();
        userId = fauth.getCurrentUser().getUid();
        fb = FirebaseDatabase.getInstance();

        fb.getReference("Users").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){

                    Toast.makeText(HomePage.this, "data came", Toast.LENGTH_SHORT).show();
                    DataSnapshot dataSnapshot = task.getResult();
                    String email = String.valueOf(dataSnapshot.child("eml").getValue());
                    t1.setText(email);

                    String pass = String.valueOf(dataSnapshot.child("pswd").getValue());
                    t2.setText(pass);


                }
            }
        });



    }
}