package com.actividad2.appfinal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {

    private LoginManager loginManager;
    private List<Element> listaElementos = new ArrayList<>();
    private RecyclerView recyclerView;
    private ElementAdapter adapter;
    private ElementManager elementManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button loginButton = view.findViewById(R.id.loginButton);
        EditText usernameEditText = view.findViewById(R.id.usernameEditText);
        EditText passwordEditText = view.findViewById(R.id.passwordEditText);

        loginManager = new LoginManager(requireContext());

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
                        Toast.makeText(requireContext(), "Conexión exitosa", Toast.LENGTH_SHORT).show();
                        ((MainActivity) requireActivity()).elementManager = new ElementManager(requireContext());
                        ((MainActivity) requireActivity()).iniciarElementos();
                        ((MainActivity) requireActivity()).cambiarFragmento(new MainFragment());
                    } else {
                        Toast.makeText(requireContext(), "Email o contraseña invalidos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}