package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayList<String> seconditems;
    private ArrayAdapter<String> itemsAdapter;
    private ArrayAdapter<String> seconditemsAdapter;
    private ListView listView;

    private EditText editText;
    private Button button;
    private Button clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        clear = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.clear();
                itemsAdapter = new ArrayAdapter(TodoActivity.this, android.R.layout.simple_list_item_1, items);
                listView.setAdapter(itemsAdapter);
                Toast.makeText(TodoActivity.this, "deleted all", Toast.LENGTH_LONG).show();
            }
        });


        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);

        seconditems = new ArrayList<>();
        seconditemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, seconditems);

        setUpListViewListener();

    }

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TodoActivity.this);

                alertDialog.setTitle("title...");
                alertDialog.setMessage("Are you sure you want delete this?");


                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        items.remove(i);
                        seconditems.remove(i);
                        itemsAdapter.notifyDataSetChanged();

                    }
                });


                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
                return true;
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int a, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context,"Item Checked", Toast.LENGTH_LONG).show();

                items.set(a,seconditems.get(a) + "  --CHECKED--");
                itemsAdapter.notifyDataSetChanged();

            }
        });
    }


    private void addItem(View view) {
        EditText input = findViewById(R.id.editText2);
        String itemText = input.getText().toString();


        if(!(itemText.equals(""))){
            itemsAdapter.add(itemText);
            seconditemsAdapter.add(itemText);
            input.setText("");

        }
        else{
            Toast.makeText(getApplicationContext(),"Please enter text...", Toast.LENGTH_LONG).show();
        }
    }

}