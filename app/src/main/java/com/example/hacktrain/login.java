package com.example.hacktrain;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//com.example.hacktrain.login activity
public class login extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText username;
    EditText password;
    String username_;
    String password_;
    Button login;
    Button signup;
    boolean signup_bool= false;
    boolean valid=false;
    boolean found = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //remove ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mAuth = FirebaseAuth.getInstance();

        //com.example.hacktrain.login
        username= findViewById((R.id.username));
        password= findViewById(R.id.password);
        login= findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                login.setAlpha(1);
                mAuth = FirebaseAuth.getInstance();
                username_=username.getText().toString();
                password_=password.getText().toString();
                signup_bool=false;
                valid = checkInfo();
                if (valid) {
                    mAuth.signInWithEmailAndPassword(username_, password_)
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        Toast.makeText(login.this, task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }
                                }
                            });
                }// end valid
            }//end click
        });
        //signup
        signup= findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                signup.setAlpha(1);
               // Intent signup_intent = new Intent(login.this, SignUp.class);
                startActivity(new Intent(login.this,SignIn.class));
            }
        });

    }//create

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    boolean checkInfo () {
        username_ = username.getText().toString().trim();
        password_ = password.getText().toString();

        valid = true;
        found = false;

        if ((TextUtils.isEmpty(password_)) || (TextUtils.isEmpty(username_))) {
            Toast.makeText(getApplicationContext(), "Empty fields", Toast.LENGTH_SHORT).show();
            return false;
        } else {


            if (!(TextUtils.isEmpty(username_)) && !(TextUtils.isEmpty(password_)) && !found ) {
                return true;
            } else {
                return false;
            }
        }
    }


    void updateUI(FirebaseUser user){

        if (user != null) {
            String name = user.getDisplayName();
            Intent intent = new Intent(login.this, MainActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
            finish();
        }
    }


}//class
