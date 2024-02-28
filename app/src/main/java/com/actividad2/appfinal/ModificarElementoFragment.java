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
    private long elementId;

    // Listener para notificar a Mainfragment y devolver un elemento modificado
    public interface OnUpdateElementListener {
        void onUpdateElement(Element element);
    }

    private OnUpdateElementListener listener;

    // Métodos set para el Listener y el ID del elemento
    public void setOnUpdateElementListener(OnUpdateElementListener listener) {
        this.listener = listener;
    }

    public void setElementId(long elementId) {
        this.elementId = elementId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        View view = inflater.inflate(R.layout.fragment_modificar_elemento, container, false);

        EditText titleEditText = view.findViewById(R.id.titleEditText);
        EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);
        Button updateButton = view.findViewById(R.id.saveButton);

        // Obtener los argumentos pasados desde el fragmento anterior (MainFragment)
        Bundle args = getArguments();
        if (args != null) {
            // Obtener el ID del elemento a editar
            elementId = args.getLong("elementId", -1);

            // Buscar el elemento en la lista y mostrar sus datos en los EditText
            for (Element element : listaElementos) {
                if (element.getId() == elementId) {
                    titleEditText.setText(element.getTitle());
                    descriptionEditText.setText(element.getDescription());
                    break;
                }
            }
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();

                if (!title.isEmpty() && !description.isEmpty()) {
                    // Obtener el userId del elemento actual
                    long userId = getUserIdForElement(elementId);

                    // Actualizar el elemento en la base de datos
                    Element updatedElement = new Element(elementId, userId, title, description, R.drawable.placeholder_image);
                    elementManager.actualizarElemento(updatedElement);

                    // Notificar a MainFragment y devolver el elemento actualizado
                    if (listener != null) {
                        listener.onUpdateElement(updatedElement);
                    }

                    // Limpiar los EditTexts
                    titleEditText.getText().clear();
                    descriptionEditText.getText().clear();

                    // Mostrar un mensaje de éxito
                    Toast.makeText(requireContext(), "Elemento actualizado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    // Mostrar un mensaje de error si los campos están vacíos
                    Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private long getUserIdForElement(long elementId) {
        long userId = -1;
        for (Element element : listaElementos) {
            if (element.getId() == elementId) {
                userId = element.getUserId();
                break;
            }
        }
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
