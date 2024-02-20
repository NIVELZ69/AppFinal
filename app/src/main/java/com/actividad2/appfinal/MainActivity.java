package com.actividad2.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BaseDeDatosLogin loginBD;
    Button loginButton;
    EditText usernameEditText;
    EditText getPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBD = new BaseDeDatosLogin(this);

        Button loginButton = findViewById(R.id.loginButton);
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    boolean success = loginBD.login(email, password);
                    if (success) {
                        Toast.makeText(MainActivity.this, "Login realizado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Email o contraseña invalidos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginBD.insertUser("usuario@gmail.com", "12345678");
    }


}