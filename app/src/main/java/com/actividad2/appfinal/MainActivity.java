package com.actividad2.appfinal;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
    public ElementManager elementManager;
    private View loginLayout;
    private View mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment = new LoginFragment();
        mainFragment = new MainFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, loginFragment)
                .commit();

        elementManager = new ElementManager(this);
        mainFragment.setElementManager(elementManager);

        // Inicializar el RecyclerView y el Adapter
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ElementAdapter(listaElementos, elementManager);
        recyclerView.setAdapter(adapter);

        // Prueba usuario
        BaseDeDatosLogin loginBD = new BaseDeDatosLogin(this);
        loginBD.insertUser("usuario@gmail.com", "12345678");
        loginBD.insertUser("usuario2@gmail.com", "87654321");

    }

    public void cambiarFragmento(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }
    public void iniciarElementos() {
        AgregarElementoFragment agregarElementoFragment = new AgregarElementoFragment();
        agregarElementoFragment.setElementManager(elementManager);
        agregarElementoFragment.setListaElementos(listaElementos);
        agregarElementoFragment.setAdapter(adapter);
    }

}
