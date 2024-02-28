package com.actividad2.appfinal;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ViewHolder> {
    private List<Element> listaElementos;
    private ElementManager elementManager;
    private MainActivity activity;
    private OnEditElementListener editListener;
    private long elementId = -1;

    public ElementAdapter(List<Element> listaElementos, ElementManager elementManager, MainActivity activity) {
        this.listaElementos = listaElementos;
        this.elementManager = elementManager;
        this.activity = activity;
    }

    public interface OnEditElementListener {
        void onEditElement(Element element);
    }

    public void setEditListener(OnEditElementListener listener) {
        this.editListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Element element = listaElementos.get(position);

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el ID del elemento en la posición adapterPosition
                elementId = element.getId();

                // Crear el fragmento de eliminación
                EliminarElementoFragment fragment = new EliminarElementoFragment();
                Bundle args = new Bundle();
                args.putLong("elementId", element.getId());
                fragment.setArguments(args);

                // Establecer el manager y el adaptador
                fragment.setElementManager(elementManager);
                fragment.setActivity(activity);

                // Abrir el fragmento de eliminación
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elementId = element.getId();

                if (editListener != null) {
                    editListener.onEditElement(element);
                }
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ElementAdapter", "Eliminar pulsado");
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listaElementos.remove(adapterPosition);
                    elementManager.borrarElemento(elementId);
                    notifyItemRemoved(adapterPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaElementos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView descriptionTextView;
        public ImageView imageView;
        public Button editButton;
        public Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            imageView = itemView.findViewById(R.id.imageView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
