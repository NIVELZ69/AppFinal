package com.actividad2.appfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        loginLayout = LayoutInflater.from(this).inflate(R.layout.activity_login, null);
        Log.d("MainActivity", "Prueba2");
        setContentView(R.layout.activity_login);
        Button loginButton = loginLayout.findViewById(R.id.loginButton);
        EditText usernameEditText = loginLayout.findViewById(R.id.usernameEditText);
        EditText passwordEditText = loginLayout.findViewById(R.id.passwordEditText);
        Log.d("MainActivity", "Prueba");

        loginManager = new LoginManager(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Conectar presionado");
                String email = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                Log.d("MainActivity", "Email: " + email + ", Contraseña: " + password);

                if (!email.isEmpty() && !password.isEmpty()) {
                    boolean success = loginManager.login(email, password);
                    if (success) {
                        Toast.makeText(MainActivity.this, "Conexión exitosa", Toast.LENGTH_SHORT).show();
                        setContentView(R.layout.activity_main);
                        recyclerView = findViewById(R.id.recyclerView);
                        elementManager = new ElementManager(MainActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        adapter = new ElementAdapter(listaElementos, elementManager);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(MainActivity.this, "Email o contraseña invalidos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Prueba usuario
        BaseDeDatosLogin loginBD = new BaseDeDatosLogin(this);
        loginBD.insertUser("usario@gmail.com", "12345678");

    }

}