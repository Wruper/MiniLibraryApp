package com.example.minilibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InsertBook extends AppCompatActivity {

    private Button btn;
    private EditText editAuth,editPage;
    private FloatingActionButton floaters;
    private static final String FILE_BOOKS = "books.txt";
    private static final String FILE_PAGES = "pages.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book);
        initiateIds();
        onAction();


    }

    public void initiateIds(){
        btn = findViewById(R.id.save);
        editAuth = findViewById(R.id.authTitle);
        editPage = findViewById(R.id.pages);
        floaters = findViewById(R.id.backBtn);
    }

    public void onAction(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String book = editAuth.getText().toString();
                String pages = editPage.getText().toString();
                FileOutputStream fosBook = null;
                FileOutputStream fosPage = null;

                try {
                    fosBook = openFileOutput(FILE_BOOKS,MODE_APPEND);
                    fosPage = openFileOutput(FILE_PAGES,MODE_APPEND);
                    fosPage.write(pages.getBytes());
                    fosPage.write("\n".getBytes());
                    fosBook.write(book.getBytes());
                    fosBook.write("\n".getBytes());
                    editAuth.getText().clear();
                    editPage.getText().clear();
                    Toast.makeText(InsertBook.this,"The list ahs been updated",Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(InsertBook.this,"The file was not found!",Toast.LENGTH_LONG);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fosBook != null && fosPage != null){
                        try {
                            fosBook.close();
                            fosPage.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        floaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertBook.this,MainActivity.class);
                startActivity(intent);
                }
            });
        }
    }


