package com.actividad2.appfinal;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

public class AgregarElementoFragment extends Fragment {

    private ElementManager elementManager;
    private List<Element> listaElementos;
    private ElementAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_elemento, container, false);

        EditText titleEditText = view.findViewById(R.id.titleEditText);
        EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);
        Button addButton = view.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();

                if (!title.isEmpty() && !description.isEmpty()) {
                    // Crear un nuevo Element y agregarlo a la base de datos
                    long id = System.currentTimeMillis(); // Podemos usar el tiempo actual como ID
                    Element newElement = new Element(id, title, description, R.drawable.placeholder_image);
                    elementManager.agregarElemento(newElement);

                    // Notificar al adaptador que se ha insertado un nuevo elemento
                    listaElementos.add(newElement);
                    adapter.notifyItemInserted(listaElementos.size() - 1);

                    // Limpiar los EditTexts
                    titleEditText.getText().clear();
                    descriptionEditText.getText().clear();

                    // Mostrar un mensaje de éxito
                    Toast.makeText(requireContext(), "Elemento agregado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    // Mostrar un mensaje de error si los campos están vacíos
                    Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void setElementManager(ElementManager elementManager) {
        this.elementManager = elementManager;
    }

    public void setListaElementos(List<Element> listaElementos) {
        this.listaElementos = listaElementos;
    }

    public void setAdapter(ElementAdapter adapter) {
        this.adapter = adapter;
    }
}
