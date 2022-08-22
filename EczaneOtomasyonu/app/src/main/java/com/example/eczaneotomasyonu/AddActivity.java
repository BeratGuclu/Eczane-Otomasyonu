package com.example.eczaneotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name_input, skt_input, adet_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        skt_input = findViewById(R.id.skt_input);
        adet_input = findViewById(R.id.adet_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addproduct(name_input.getText().toString().trim(),
                        skt_input.getText().toString().trim(),
                        Integer.valueOf(adet_input.getText().toString().trim()));
            }
        });
    }
}
