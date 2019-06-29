package com.example.hacktrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFab,mFab_log_out,mFab_menu,mFab_search_books
            ,mFab_btn_exchange_book,mFab_suggestion_books,mFab_notification,mFab_request;
    private Button mBtn_search_cook, mBtn_exchange_book, mBtn_suggestion_books;
    boolean isFABOpen = false;
    FirebaseAuth mAuth; //firebase authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeallWighet();
        defineEvents();
    }

    private void initializeallWighet() {
        mFab=findViewById(R.id.fab);
        mFab_log_out=findViewById(R.id.fab_log_out);
        mFab_menu=findViewById(R.id.fab_menu);
        mFab_search_books=findViewById(R.id.fab_search_books);
        mBtn_search_cook =findViewById(R.id.btn_search_cook);
        mFab_btn_exchange_book=findViewById(R.id.fab_btn_exchange_book);
        mBtn_exchange_book =findViewById(R.id.btn_exchange_book);
        mFab_suggestion_books=findViewById(R.id.fab_suggestion_books);
        mBtn_suggestion_books =findViewById(R.id.btn_suggestion_books);
        mFab_notification=findViewById(R.id.fab_notification);
        mFab_request=findViewById(R.id.fab_request);
        mAuth=FirebaseAuth.getInstance();
    }
    private void defineEvents() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
        mFab_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }
        });
        mFab_search_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBooks();
            }
        });
        mBtn_search_cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBooks();
            }
        });
        mFab_btn_exchange_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exchangeBooks();
            }
        });
        mBtn_exchange_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exchangeBooks();
            }
        });
        mFab_suggestion_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestionBooks();
            }
        });
        mBtn_suggestion_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestionBooks();
            }
        });
        mFab_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NotificationActivity.class));
            }
        });
        mFab_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mFab_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                logInPage();
            }
        });
    }

    private  void searchBooks(){
        startActivity(new Intent(MainActivity.this,SearchForABook.class));

    }
    private  void exchangeBooks(){
        startActivity(new Intent(MainActivity.this,PostABook.class));

    }
    private  void suggestionBooks(){
        startActivity(new Intent(MainActivity.this,suggestionBooksActivity.class));

    }


    private void showFABMenu(){
        isFABOpen=true;
        mFab.animate().translationX(-getResources().getDimension(R.dimen.standard_55));
        mFab_log_out.animate().translationX(-getResources().getDimension(R.dimen.standard_105));
        mFab_menu.animate().translationX(-getResources().getDimension(R.dimen.standard_155));
    }

    private void closeFABMenu(){
         isFABOpen = false;
        mFab.animate().translationX(0);
        mFab_menu.animate().translationX(0);
        mFab_log_out.animate().translationX(0);
    }
    private void logInPage(){
        startActivity(new Intent(MainActivity.this,login.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()==null){
            logInPage();
        }
    }
}
