package com.example.fire_auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText eml,pswd;
    Button regbtn;
    FirebaseAuth fauth;
    FirebaseDatabase fb;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eml = findViewById(R.id.usr);
        pswd = findViewById(R.id.psd);
        fauth = FirebaseAuth.getInstance();
        regbtn = findViewById(R.id.regbtn);
        fb = FirebaseDatabase.getInstance();


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = eml.getText().toString();
                String password = pswd.getText().toString();

                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            userId = fauth.getCurrentUser().getUid();
                            Log.d("User Id1234455666777",userId);

                            Map<String,Object> user = new HashMap<>();
                            user.put("eml",email);
                            user.put("pswd",password);

                            fb.getReference("Users").child(userId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast.makeText(Register.this, "success", Toast.LENGTH_SHORT).show();

                                }
                            });


                            Toast.makeText(Register.this, "registerd succesfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomePage.class));

                        }
                    }
                });



            }
        });

    }
}