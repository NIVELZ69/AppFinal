package com.actividad2.appfinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements ElementAdapter.OnEditClickListener, ElementAdapter.OnDeleteClickListener {

    private List<Element> listaElementos = new ArrayList<>();
    private RecyclerView recyclerView;
    private ElementAdapter adapter;
    private ElementManager elementManager;
    private LoginManager loginManager;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        elementManager = new ElementManager(requireContext());
        loginManager = new LoginManager(requireContext());

        long userId = loginManager.getCurrentUserId();
        listaElementos = elementManager.getElementsForCurrentUser(userId);

        Button addButton = view.findViewById(R.id.addButton);

        adapter = new ElementAdapter(listaElementos, elementManager);
        adapter.setOnEditClickListener(this);
        adapter.setOnDeleteClickListener(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarElementoFragment agregarElementoFragment = new AgregarElementoFragment();
                agregarElementoFragment.setElementManager(elementManager);
                agregarElementoFragment.setListaElementos(listaElementos);
                agregarElementoFragment.setAdapter(adapter);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, agregarElementoFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onEditClick(Element element) {
        ModificarElementoFragment modificarElementoFragment = new ModificarElementoFragment();
        modificarElementoFragment.setElementManager(elementManager);
        modificarElementoFragment.setListaElementos(listaElementos);
        modificarElementoFragment.setAdapter(adapter);
        modificarElementoFragment.setElement(element);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, modificarElementoFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDeleteClick(Element element) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que quieres eliminar este elemento?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listaElementos.remove(element);
                elementManager.borrarElemento(element);
                adapter.notifyDataSetChanged(); // notificar al adaptador que el conjunto de datos ha cambiado
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
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

