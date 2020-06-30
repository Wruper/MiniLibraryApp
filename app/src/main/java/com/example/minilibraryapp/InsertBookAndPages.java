package com.example.minilibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class InsertBookAndPages extends AppCompatActivity {

    Button btn;
    EditText editTxt,editText1;
    private FloatingActionButton floaters;

    @Override
    public AssetManager getAssets() {
        return super.getAssets();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book_and_pages);

        setIds();

    }

    public void setIds(){
        btn.findViewById(R.id.save);
        floaters.findViewById(R.id.save);
        editTxt.findViewById(R.id.authTitle);
        editText1.findViewById(R.id.Pages);
    }


    public boolean appendToFiles() throws IOException {
        Writer writer = new BufferedWriter(new FileWriter(getAssets().open("books.txt"),true));
    }

    public void setActions(){
        floaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertBookAndPages.this,MainActivity.class);
                startActivity(intent);
            }
        });

        editTxt.
    }

}