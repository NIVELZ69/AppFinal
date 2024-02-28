package com.actividad2.appfinal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;

import androidx.fragment.app.Fragment;

import java.util.List;

public class EliminarElementoFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_eliminar_elemento, container, false);

        Button confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Confirmación");
                builder.setMessage("¿Estás seguro de que quieres eliminar este elemento?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listaElementos.remove(element);
                        elementManager.borrarElemento(element);
                        adapter.notifyDataSetChanged();

                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });

        return view;
    }
}
