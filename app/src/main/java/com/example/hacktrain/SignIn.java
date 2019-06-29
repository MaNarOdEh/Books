package com.example.hacktrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;
import java.util.HashMap;

public class SignIn extends AppCompatActivity {
    Button signup;
    boolean signup_bool= false;
    FirebaseAuth mAuth;
    String email_;
    String password_;
    EditText edit_email,edit_password;
    private FirebaseFirestore db;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        //signup
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //firebase
        edit_email=findViewById(R.id.email);
        edit_password=findViewById(R.id.password);

        signup=findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                signup.setAlpha(1);

                signup_bool = true;
                boolean valid = checkInfo();
                if (valid){
                    mAuth.createUserWithEmailAndPassword(email_, password_)
                            .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(SignIn.this,ContinueSignIn.class));
                                    } else {
                                        Toast.makeText(SignIn.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }// end click
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
        email_=edit_email.getText().toString();
        password_= edit_password.getText().toString();



        if(!Validations.isEmailValid(email_)){
            Toast.makeText(this, "Wrong Email!", Toast.LENGTH_SHORT).show();
            return false;
        }if(!Validations.isValidPass(password_)){
            Toast.makeText(this, "Invalid  Password!", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;

    }





    void updateUI(FirebaseUser user){

        if (user != null) {
            String name = user.getDisplayName();
            Intent intent = new Intent(SignIn.this, MainActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
            finish();
        }
    }


    public void addtodatabase (String name ,String pw){









    }


}//class
