package com.example.hacktrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

public class ContinueSignIn extends AppCompatActivity {
    String firstname_, lastname_, date_,username_;
    EditText edit_name,edit_email,edit_date,edit_last_name,edit_password,edit_username;
    private FirebaseFirestore db;
    Button  signup;
    EditText date;
    DatePickerDialog.OnDateSetListener mDateLisrener;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_sign_in);
        initialze();
    }

    private void initialze() {
        edit_date=findViewById(R.id.date);
        edit_email=findViewById(R.id.email);
        edit_name=findViewById(R.id.firstname);
        edit_last_name=findViewById(R.id.lastname);
        edit_password=findViewById(R.id.password);
        edit_username=findViewById(R.id.username);
        signup=findViewById(R.id.signup);
        mAuth=FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
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
                DatePickerDialog dialog= new DatePickerDialog(ContinueSignIn.this, mDateLisrener, year, month, day);
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
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date_=date.getText().toString();
                firstname_=edit_name.getText().toString();
                lastname_=edit_last_name.getText().toString();
                username_=edit_username.getText().toString();
                UserClass userClass=new UserClass(firstname_,lastname_,username_,date_);
                HashMap<String,String> map= new HashMap<>();
                map.put("First_Name",firstname_);
                map.put("Last_Name",lastname_);
                map.put("User_Name",username_);
                map.put("Date",date_);
                    db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(map)
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("Failur",e.getMessage());
                                    Toast.makeText(ContinueSignIn.this, "FAAAAAAAAAAAAAAAAAAAAAAil", Toast.LENGTH_LONG).show();
                                }
                            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("Sucess","success");
                            Toast.makeText(ContinueSignIn.this, "YEEEEEEEEEEEEEEEEEEEEEEEEEEES", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(ContinueSignIn.this,MainActivity.class));
                        }
                    });

                    startActivity(new Intent(ContinueSignIn.this,MainActivity.class));
                }

        });


    }
}
