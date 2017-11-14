package com.example.mvryan.belajardatabase;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by mvryan on 10/11/17.
 */

public class AddData extends Activity implements View.OnClickListener{

    EditText title,author;
    boolean update = false;
    long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_form);
        title =(EditText) findViewById(R.id.editText_tittle);
        author =(EditText) findViewById(R.id.editText_author);
        Button save = (Button) findViewById(R.id.button_save);
        save.setOnClickListener(this);

        id = getIntent().getLongExtra("_id",0);
        if (id!=0){
            update=true;
            //populate form with data
            title.setText(getIntent().getStringExtra("title"));
            author.setText(getIntent().getStringExtra("author"));
        }
    }

    @Override
    public void onClick(View view) {
        BookHelper helper = new BookHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",title.getText().toString().trim());
        cv.put("author",author.getText().toString().trim());
        if(update){
            db.update("book_entries",cv,"_id=?",new String[]{String.valueOf(id)});
        }else {
            long newid = db.insert("book_entries",null,cv);
        }
        //back to main activity
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
