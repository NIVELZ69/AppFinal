package com.actividad2.appfinal;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

public class ModificarElementoFragment extends Fragment {

    private ElementManager elementManager;
    private List<Element> listaElementos;
    private ElementAdapter adapter;
    private Element element;

    public void setElementManager(ElementManager elementManager) {
        this.elementManager = elementManager;
    }

    public void setListaElementos(List<Element> listaElementos) {
        this.listaElementos = listaElementos;
    }

    public void setAdapter(ElementAdapter adapter) {
        this.adapter = adapter;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modificar_elemento, container, false);

        EditText titleEditText = view.findViewById(R.id.titleEditText);
        EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);
        // Aquí necesitarás asignar los elementos de diseño para cada campo editable

        titleEditText.setText(element.getTitle());
        descriptionEditText.setText(element.getDescription());
        // Aquí estableces los valores actuales del elemento en los elementos de diseño

        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = titleEditText.getText().toString();
                String newDescription = descriptionEditText.getText().toString();
                // Aquí obtienes los nuevos valores del elemento desde los elementos de diseño

                element.setTitle(newTitle);
                element.setDescription(newDescription);
                // Aquí actualizas los valores del elemento

                elementManager.actualizarElemento(element);
                adapter.notifyDataSetChanged();
                // Aquí actualizas la lista y el adaptador

                getActivity().getSupportFragmentManager().popBackStack();
                // Aquí cierras el fragmento
            }
        });

        return view;
    }
}

