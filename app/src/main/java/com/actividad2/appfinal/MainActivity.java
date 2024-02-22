package com.actividad2.appfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LoginFragment loginFragment;
    private MainFragment mainFragment;
    private LoginManager loginManager;
    private List<Element> listaElementos = new ArrayList<>();
    private RecyclerView recyclerView;
    private ElementAdapter adapter;
    private ElementManager elementManager;
    private View loginLayout;
    private View mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loginLayout = LayoutInflater.from(this).inflate(R.layout.activity_login, null);
        Log.d("MainActivity", "Prueba2");
        setContentView(R.layout.activity_main);

        loginFragment = new LoginFragment();
        mainFragment = new MainFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, loginFragment)
                .commit();

        loginManager = new LoginManager(this);


        // Prueba usuario
        BaseDeDatosLogin loginBD = new BaseDeDatosLogin(this);
        loginBD.insertUser("usario@gmail.com", "12345678");

    }

    public void cambiarFragmento(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

}