package com.example.hacktrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

public class SignIn extends AppCompatActivity {
    EditText date;
    DatePickerDialog.OnDateSetListener mDateLisrener;
    Button signup;
    boolean signup_bool= false;
    FirebaseAuth mAuth;
    String email_;
    String password_;
    String username_;
    String firstname_, lastname_, date_;
    EditText edit_name,edit_email,edit_date,edit_last_name,edit_password,edit_username;
    private FirebaseFirestore db;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //remove ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //date
        date=findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year= calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog= new DatePickerDialog(SignIn.this, mDateLisrener, year, month, day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.));
                dialog.show();
            }
        });
        mDateLisrener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month= month+1;
                date.setText(day + "/" + month +"/"+ year);
            }
        };

        //signup
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //firebase
        edit_date=findViewById(R.id.date);
        edit_email=findViewById(R.id.email);
        edit_name=findViewById(R.id.firstname);
        edit_last_name=findViewById(R.id.lastname);
        edit_password=findViewById(R.id.password);
        edit_username=findViewById(R.id.username);
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
                                        Log.d("YEEEEEh","Sucesss");
                                        UserClass userClass=new UserClass(firstname_,lastname_,username_,date_);
                                        HashMap map=new HashMap();
                                        map.put("First_Name",firstname_);
                                        map.put("Last_Name",lastname_);
                                        map.put("User_Name",username_);
                                        map.put("Date",date_);
                                        final FirebaseUser user = mAuth.getCurrentUser();
                                        db.collection("users").document(user.getUid()).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(SignIn.this, "OMG", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Toast.makeText(SignIn.this, ""+task.getResult()+  "   "+task.getException(), Toast.LENGTH_SHORT).show();
                                                    updateUI(mAuth.getCurrentUser());

                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(SignIn.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                        Log.d("EmailWrong",task.getException().getMessage()+"    "+task.getResult());
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
        date_=date.getText().toString();
        firstname_=edit_name.getText().toString();
        lastname_=edit_last_name.getText().toString();
        username_=edit_username.getText().toString();


        if(!Validations.isEmailValid(email_)){
            Toast.makeText(this, "Wrong Email!", Toast.LENGTH_SHORT).show();
            return false;
        }if(!Validations.isValidPass(password_)){
            Toast.makeText(this, "Invalid  Password!", Toast.LENGTH_SHORT).show();
            return  false;
        }if(!Validations.isValidName(firstname_)){
            Toast.makeText(this, "Invalid  First Name!", Toast.LENGTH_SHORT).show();
           return false;
        }if(!Validations.isValidName(lastname_)){
            Toast.makeText(this, "Invalid  Last Name!", Toast.LENGTH_SHORT).show();

            return false;
        }if(!Validations.isValidName(username_)){
            Toast.makeText(this, "Invalid  User Name!", Toast.LENGTH_SHORT).show();

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


}//class
