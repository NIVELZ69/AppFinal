package com.actividad2.appfinal;

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

public class MainFragment extends Fragment {

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
        Button editButton = view.findViewById(R.id.editButton);
        Button deleteButton = view.findViewById(R.id.deleteButton);

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
        adapter = new ElementAdapter(listaElementos, elementManager);
        recyclerView.setAdapter(adapter);

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
