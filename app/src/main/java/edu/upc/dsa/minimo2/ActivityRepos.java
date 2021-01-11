package edu.upc.dsa.minimo2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRepos extends AppCompatActivity {
    TextView siguiendo;
    ImageView usuarioImg;
    TextView seguidor;
    TextView nombre;
    String name;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    AdaptadorRecycleView myAdapter;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        progressBar = findViewById(R.id.progres);
        siguiendo = findViewById(R.id.followings);
        nombre = findViewById(R.id.nombreUsuario);
        seguidor = findViewById(R.id.follow);
        usuarioImg = findViewById(R.id.perfilImg);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("username");
        nombre.setText(name);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        getUsuario();
        getRepos();
        context = getApplicationContext();

    }


    public void getUsuario()
    {

        progressBar.setVisibility(View.VISIBLE);
        Call<Usuario> call = ApiClient.getUserService().getUser(name);
        Intent intent = new Intent(this,MainActivity.class);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response)
            {
                if (response.code() == 200)
                {
                    Usuario usuario = response.body();
                    progressBar.setVisibility(View.INVISIBLE);
                    seguidor.setText(usuario.getFollowing());
                    siguiendo.setText(usuario.getFollowers());
                    String url = usuario.getAvatar_url();
                    Glide.with(context).load(url).into(usuarioImg);
                }
               else if (response.code() == 404)
               {
                    AlertDialog alertDialog = new AlertDialog.Builder (ActivityRepos.this).create();

                    alertDialog.setMessage("NO SE HA ENCONTRADO");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ACEPTAR",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    startActivity(intent);
                                }
                            });
                    alertDialog.show();

                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t)
            {
                AlertDialog alertDialog = new AlertDialog.Builder (ActivityRepos.this).create();

                alertDialog.setMessage("NO CONEXIÓN");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });



    }

    public void getRepos()
    {
        Call<List<Repos>> call = ApiClient.getUserService().getRespos(name);
        call.enqueue(new Callback<List<Repos>>()
        {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response)
            {
                List<Repos> repos = response.body();
                myAdapter = new AdaptadorRecycleView();
                myAdapter.setData(repos);
                recyclerView.setAdapter(myAdapter);


            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t)
            {
                AlertDialog alertDialog = new AlertDialog.Builder (ActivityRepos.this).create();

                alertDialog.setMessage("ERROR");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                alertDialog.show();

            }
        });


    }

}