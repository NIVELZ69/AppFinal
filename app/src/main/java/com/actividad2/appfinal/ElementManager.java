package com.actividad2.appfinal;

import android.content.Context;

import java.util.List;

public class ElementManager {
    private BaseDeDatosLogin loginBD;
    private List<Element> listaElementos;

    public ElementManager(Context context) {
        loginBD = new BaseDeDatosLogin(context);
    }

    public List<Element> getElementsForCurrentUser(long userId) {
        listaElementos = loginBD.getElementsForUserId(userId);
        return listaElementos;
    }

    public long agregarElemento(long userId, String title, String description, int imageResource) {
        loginBD.agregarElemento(userId, title, description, imageResource);

        return userId;

    public void agregarElemento(Element element) {
        loginBD.agregarElemento(element.getId(), element.getTitle(), element.getDescription(), element.getImageResource());
    }

    public void actualizarElemento(Element element)  {
        loginBD.actualizarElemento(element.getId(), element.getTitle(), element.getDescription(), element.getImageResource());

        // Buscar el elemento en la lista y actualizarlos
        for (int i = 0; i < listaElementos.size(); i++) {
            if (listaElementos.get(i).getId() == element.getId()) {
                listaElementos.set(i, element);
                break;
            }
        }

    }

    public void borrarElemento(long elementId) {
        loginBD.borrarElemento(elementId);
    }
}
