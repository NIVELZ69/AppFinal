package com.actividad2.appfinal;

import android.content.Context;

import java.util.List;

public class ElementManager {
    private BaseDeDatosLogin loginBD;

    public ElementManager(Context context) {
        loginBD = new BaseDeDatosLogin(context);
    }

    public List<Element> getElementsForCurrentUser(long userId) {

        return loginBD.getElementsForUserId(userId);
    }

    public long agregarElemento(long userId, String title, String description, int imageResource) {
        loginBD.agregarElemento(userId, title, description, imageResource);

        return userId;
    }

    public void actualizarElemento(Element element)  {
        loginBD.actualizarElemento(element.getId(), element.getTitle(), element.getDescription(), element.getImageResource());

        // Buscar el elemento en la lista y actualizarlos
        for (int i = 0; i < listaElementos.size(); i++) {
            if (listaEle)
        }
    }

    public void borrarElemento(Element element) {
        loginBD.borrarElemento(element.getId());
    }
}
