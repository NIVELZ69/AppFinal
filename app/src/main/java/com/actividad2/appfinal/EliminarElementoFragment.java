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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eliminar_elemento, container, false);


        // Obtener los argumentos pasados desde el fragmento anterior (MainFragment)
        Bundle args = getArguments();
        if (args != null) {
            // Obtener el ID del elemento a eliminar
            elementId = args.getLong("elementId", -1);
        }

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
