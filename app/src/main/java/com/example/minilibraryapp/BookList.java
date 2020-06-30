package com.example.minilibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.loader.AssetsProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BookList extends AppCompatActivity {

    private ArrayList<String> books =  new ArrayList<>();
    private ListView bookList;
    private FloatingActionButton floaters;

    public BookList() throws IOException {
    }

    @Override
    public AssetManager getAssets() {
        return super.getAssets();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        bookList = findViewById(R.id.listView);
        floaters = findViewById(R.id.backBtn);

        try {
            addBooksToView();
        } catch (IOException e) {
            e.printStackTrace();
        }

        createAdapter();


        floaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookList.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    public void addBooksToView() throws IOException {
        try {
            InputStream bookslist = getAssets().open("books.txt");
            Scanner myReader = new Scanner(bookslist);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                books.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(BookList.this, "An error eccurred.", Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
    }

    public void createAdapter(){
            ArrayAdapter<String> booksAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,books);
            bookList.setAdapter(booksAdapter);
        }
}


