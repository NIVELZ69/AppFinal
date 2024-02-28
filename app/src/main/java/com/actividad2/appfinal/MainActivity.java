package com.actividad2.appfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LoginFragment loginFragment;
    public ElementManager elementManager;
    private List<Element> listaElementos = new ArrayList<>();
    private RecyclerView recyclerView;
    private ElementAdapter adapter;
    private View loginLayout;
    private View mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, loginFragment)
                .commit();

        elementManager = new ElementManager(this);
        iniciarElementos();
    }

    public void cambiarFragmento(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void iniciarElementos() {
        if (elementManager == null) {
            Log.e("MainActivity", "elementManager es nulo");
            return;
        }

        MainFragment mainFragment = new MainFragment();
        mainFragment.setElementManager(elementManager);
        mainFragment.setListaElementos(listaElementos);
        mainFragment.setAdapter(adapter);
        cambiarFragmento(mainFragment);
    }
}
