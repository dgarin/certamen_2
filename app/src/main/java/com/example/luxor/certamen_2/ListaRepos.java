package com.example.luxor.certamen_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ListaRepos extends AppCompatActivity {

    EditText nombre;
    TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //nombre = (EditText)findViewById(R.id.editText);
        //temp = (TextView) findViewById(R.id.text1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_repos);

        //String name = nombre.getText().toString();
        //temp.setText("Lista de repositorio de usuario" + name);

    }
}
