package com.actividad2.appfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

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
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        loginFragment = new LoginFragment();
        mainFragment = new MainFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, loginFragment)
                .commit();

        elementManager = new ElementManager(this);
        mainFragment.setElementManager(elementManager);

        drawerLayout = findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;

                if (id == R.id.nav_home) {


                } else if (id == R.id.nav_about) {


                } else if (id == R.id.nav_logout) {

                }

                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentContainer, fragment);
                    ft.commit();
                }

                drawerLayout.closeDrawers();
                return true;

            }
        });

        if (actionBar != null) {
            actionBar.setTitle("App Final");
            actionBar.setDisplayHomeAsUpEnabled(true);
           // actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        // Prueba usuario
        BaseDeDatosLogin loginBD = new BaseDeDatosLogin(this);
        loginBD.insertUser("usuario@gmail.com", "12345678");
        loginBD.insertUser("usuario2@gmail.com", "87654321");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
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
