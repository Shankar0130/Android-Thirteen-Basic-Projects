package com.shankaryadav.www.whatsappchatbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;

public class ChatList extends AppCompatActivity {

    RecyclerView recyclerView;

     FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_chat_list);
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        recyclerView = findViewById (R.id.recyclerView);

       // CollectionReference collectionReference = firebaseFirestore.collection ("Notebook");

        FloatingActionButton fab = findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (ChatList.this,AddChat.class));
            }
        });
    }

}
