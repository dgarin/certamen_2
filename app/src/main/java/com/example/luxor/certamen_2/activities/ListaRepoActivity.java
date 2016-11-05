package com.example.luxor.certamen_2.acitivities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.luxor.certamen_2.ItemClickListener;
import com.example.luxor.certamen_2.R;
import com.example.luxor.certamen_2.adapter.UIAdapter;
import com.example.luxor.certamen_2.connection.HttpServerConnection;
import com.example.luxor.certamen_2.model.Repo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luxor on 02-11-16.
 */

public class ListaRepoActivity extends AppCompatActivity implements ItemClickListener {

    TextView header;
    String url = "";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Repo> repositories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        header = (TextView) findViewById(R.id.titleRepo);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            String user = extras.getString("usuario");
            if (user.isEmpty() ) {
                header.setText("NO EXISTE EL USUARIO INGRESADO");
                url = "https://api.github.com/users//repos";
            } else {
                header.setText("REPOSITORIOS DE "+user);
                url = "https://api.github.com/users/"+ user +"/repos";
            }

        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute(){

            }

            @Override
            protected String doInBackground(Void... params) {
                String resultado = new HttpServerConnection().connectToServer(url, 15000);
                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {
                if(result != null){
                    System.out.println(result);
                    repositories = getLista(result);
                    mAdapter = new UIAdapter(repositories);
                    mRecyclerView.setAdapter(mAdapter);
                    ((UIAdapter) mAdapter).setOnClickListener(ListaRepoActivity.this);
                }
            }
        };
        task.execute();

    }
    @Override
    public void onClick(View view, int position) {
        final Repo repo = repositories.get(position);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(repo.getUrl()));
        startActivity(browserIntent);
    }
    private List<Repo> getLista(String result){
        List<Repo> repositories_list = new ArrayList<Repo>();
        try {
            JSONObject object = new JSONObject(result);
            return repositories_list;
        }catch (JSONException e) {
            e.printStackTrace();

            try {
                JSONArray list = new JSONArray(result);
                int size = list.length();
                for (int i = 0; i < size; i++) {
                    Repo rep = new Repo();
                    JSONObject object = list.getJSONObject(i);

                    rep.setId(object.getInt("id"));
                    rep.setNombre(object.getString("name"));
                    if (object.getString("description") == "null" || object.getString("description").isEmpty()) {
                        rep.setDescription("No Description Available");
                    } else {
                        rep.setDescription(object.getString("description"));
                    }
                    String fecha = object.getString("updated_at");
                    String[] partfecha = fecha.split("T");
                    fecha = partfecha[0];
                    rep.setActual("Last Update: " + fecha);
                    rep.setUrl(object.getString("html_url"));
                    repositories_list.add(rep);
                }
                return repositories_list;
            }catch (JSONException e1) {
                e.printStackTrace();
                return repositories_list;
            }
        }

    }
}
