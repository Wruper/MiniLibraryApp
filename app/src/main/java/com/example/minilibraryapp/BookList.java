package com.example.minilibraryapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class BookList extends AppCompatActivity {

    private ArrayList<String> books =  new ArrayList<>();
    private ListView bookList;
    private FloatingActionButton floaters;
    private static final String FILE_NAME = "books.txt";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        setIds();
        setActions();
        try {
            addBooksToView();
        } catch (FileNotFoundException e) {
            Toast.makeText(BookList.this,"The file was not found!",Toast.LENGTH_LONG);
            e.printStackTrace();
        }
        createAdapter();



    }

    public void addBooksToView() throws FileNotFoundException {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            while((text = br.readLine()) != null){
                books.add(text);
            }
        } catch (IOException e) {
            Toast.makeText(BookList.this,"The file was not found!",Toast.LENGTH_LONG);
            e.printStackTrace();
        }
    }


    public void createAdapter(){
            ArrayAdapter<String> booksAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,books);
            bookList.setAdapter(booksAdapter);
        }

    public void setIds(){
        bookList = findViewById(R.id.listView);
        floaters = findViewById(R.id.backBtn);


    }


    public void setActions(){
        floaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookList.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}



