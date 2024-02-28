package com.actividad2.appfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

public class EliminarElementoFragment extends Fragment {

    private ElementManager elementManager;
    private List<Element> listaElementos;
    private ElementAdapter adapter;
    private long elementId;
    private MainActivity activity;

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eliminar_elemento, container, false);


        // Obtener los argumentos pasados desde el fragmento anterior (MainFragment)
        Bundle args = getArguments();
        if (args != null) {
            // Obtener el ID del elemento a eliminar
            elementId = args.getLong("elementId", -1);
        }

        Button confirmarButton = view.findViewById(R.id.confirmButton);
        Button cancelarButton = view.findViewById(R.id.cancelButton);

        // Accion para confirmar la eliminación
        confirmarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (elementId != -1) {
                    // Eliminar el elemento
                    elementManager.borrarElemento(elementId);
                    // Notificar al adaptador
                    adapter.notifyDataSetChanged();
                    // Cerrar el fragmento
                    requireActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar el fragmento
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;

    }

    public void setElementManager(ElementManager elementManager) {
        this.elementManager = elementManager;
    }

    public void setListaElementos(List<Element> listaElemntos) {
        this.listaElementos = listaElementos;
    }

    public void setAdapter(ElementAdapter adapter) {
        this.adapter = adapter;
    }

}
