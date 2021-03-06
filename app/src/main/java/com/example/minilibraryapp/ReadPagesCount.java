package com.example.minilibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadPagesCount extends AppCompatActivity {
    private TextView readTime;
    private static final String FILE_PAGES = "pages.txt";
    private int pageCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_pages);
        setIds();
        getPages();
        setActions();
    }

    public boolean getPages(){
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_PAGES);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            while((text = br.readLine()) != null){
                int newData = Integer.parseInt(text);
                pageCount += newData;
            }
            return true;
        }
        catch (IOException e) {
            Toast.makeText(ReadPagesCount.this, "ERROR: No file exists.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }


    }

    public void setIds(){
        readTime = findViewById(R.id.readTime_txt);
    }

    public void setActions(){

        readTime.setText("In total you read: " + pageCount + " pages!");


    }



}