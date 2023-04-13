package com.example.fire_auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    EditText eml,pswd;
    Button logbn,regbn;
    FirebaseAuth fauth;
    FirebaseDatabase fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eml = findViewById(R.id.usrnm);
        pswd = findViewById(R.id.pswd);
        logbn = findViewById(R.id.logbtn);
        regbn = findViewById(R.id.regbn);
        fauth = FirebaseAuth.getInstance();



        String email = eml.getText().toString().trim();
        String password = pswd.getText().toString().trim();

        regbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });


    }
}