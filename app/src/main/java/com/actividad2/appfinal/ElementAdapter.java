package com.actividad2.appfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ViewHolder> {
    private List<Element> listaElementos;
    private ElementManager elementManager;

    public ElementAdapter(List<Element> listaElementos, ElementManager elementManager) {
        this.listaElementos = listaElementos;
        this.elementManager = elementManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Element element = listaElementos.get(holder.getAdapterPosition());
        holder.titleTextView.setText(element.getTitle());
        holder.descriptionTextView.setText(element.getDescription());
        holder.imageView.setImageResource(element.getImageResource());

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fragmento edición
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {

                    listaElementos.remove(adapterPosition);
                    elementManager.borrarElemento(element);
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