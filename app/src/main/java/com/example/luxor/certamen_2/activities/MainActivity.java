package com.example.luxor.certamen_2.acitivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luxor.certamen_2.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button boton;
    EditText editText;
    private ListaRepoActivity mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.editText);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                String user = editText.getText().toString();
                Intent i = new Intent(MainActivity.this,ListaRepoActivity.class);
                i.putExtra("usuario",user);
                startActivity(i);
                break;
        }
    }
}
