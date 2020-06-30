package com.example.minilibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ReadPagesCount extends AppCompatActivity {
    TextView readTime;
    private FloatingActionButton floaters;
    private int pageCount = 0;

    @Override
    public AssetManager getAssets() {
        return super.getAssets();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_time);
        setIds();
        getPages();
        setActions();
    }



    public boolean getPages(){
        try {
            InputStream readTime = getAssets().open("pages.txt");
            Scanner myReader = new Scanner(readTime);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int newData = Integer.parseInt(data);
                pageCount += newData;
            }
            return true;
        }

        catch (IOException e) {
            Toast.makeText(ReadPagesCount.this, "ERROR: No file exists.", Toast.LENGTH_SHORT);
            e.printStackTrace();
            return false;
        }


    }

    public void setIds(){
        floaters = findViewById(R.id.backBtn);
        readTime = findViewById(R.id.readTime_txt);
    }

    public void setActions(){
        floaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadPagesCount.this,MainActivity.class);
                startActivity(intent);
            }
        });

        readTime.setText("In total you read: " + pageCount + " pages!");


    }



}