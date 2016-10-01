package com.example.luxor.certamen_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button buscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscar = (Button)findViewById(R.id.button);



        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent buscar = new Intent(MainActivity.this, ListaRepos.class);
                startActivity(buscar);


            }
        });
    }
}
