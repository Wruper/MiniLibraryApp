package com.example.minilibraryapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class BookList extends AppCompatActivity {

    List<HashMap<String,String>> listItem =  new ArrayList<>(); // for unsorted listview
    private ListView bookList;
    private static final String FILE_NAME = "books.txt";
    private static final String FILE_PAGE = "pages.txt";
    private boolean isButtonPressed = false;
    private FloatingActionButton btn;
    HashMap<String,String> booksPage =  new HashMap<>();
    TreeMap<String,String> sorted = new TreeMap<>(booksPage);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        setIds();
        try {
            addBooksToView();
        } catch (FileNotFoundException e) {
            Toast.makeText(BookList.this,"The file was not found!",Toast.LENGTH_LONG);
            e.printStackTrace();
        }
        iterateOverUnsorted();
        createAdapter();
        setAction();
    }

    public void addBooksToView() throws FileNotFoundException {
        FileInputStream fisb = null;
        FileInputStream fisp = null;
        try {
            fisb = openFileInput(FILE_NAME);
            fisp = openFileInput(FILE_PAGE);
            InputStreamReader isrb = new InputStreamReader(fisb);
            InputStreamReader isrp = new InputStreamReader(fisp);
            BufferedReader brb = new BufferedReader(isrb);
            BufferedReader brp = new BufferedReader(isrp);
            String textB =  null;
            String textP = null;
            while((textB = brb.readLine()) != null && (textP = brp.readLine()) != null){
                booksPage.put(textB,textP);
            }
        } catch (IOException e) {
            Toast.makeText(BookList.this,"The file was not found!",Toast.LENGTH_LONG);
            e.printStackTrace();
        }

    }

    public void iterateOverUnsorted() {
        List<HashMap<String,String>> temp =  new ArrayList<>(); // temp list, that helps me sort the list view
        Iterator it = booksPage.entrySet().iterator();// itterates over the new TreeMap
        while (it.hasNext()) {
            HashMap<String, String> resultMap = new HashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            resultMap.put("First Line", pair.getKey().toString());
            resultMap.put("Second Line", pair.getValue().toString());
            temp.add(resultMap); // add the sorted results in the temp Hashmap
            listItem = temp; // refresh the list view in unsorted order
        }
    }

    public void iterateOverSorted() {
        List<HashMap<String, String>> temp = new ArrayList<>(); // temp list, that helps me sort the list view
        sorted.putAll(booksPage); // I put my HashMap in a TreeMap that sorts the list view elements alphabetically
        Iterator it = sorted.entrySet().iterator(); // itterates over the new TreeMap
        while (it.hasNext()) {
            HashMap<String, String> resultMap = new HashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            resultMap.put("First Line", pair.getKey().toString());
            resultMap.put("Second Line", pair.getValue().toString());
            temp.add(resultMap); // add the sorted results in the temp Hashmap
            listItem = temp; // refresh the list view in sorted order
        }
    }

    public void createAdapter(){
        SimpleAdapter adapter = new SimpleAdapter(BookList.this,listItem,R.layout.list_item,
                new String []{"First Line", "Second Line"}, new int [] {R.id.listMain,
                R.id.listMini});

        bookList.setAdapter(adapter);
    }

    public void setIds(){
        bookList = findViewById(R.id.listView);
        btn = findViewById(R.id.btn);

    }

    public void sortList(){
        if(isButtonPressed){
            iterateOverUnsorted();
            isButtonPressed = false;

        }
        else {
            iterateOverSorted();
            isButtonPressed = true;

        }
        createAdapter();
    }

    public void setAction(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortList();
            }
        });
    }
}



