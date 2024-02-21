package com.actividad2.appfinal;

import android.content.Context;

public class ElementManager {
    private BaseDeDatosLogin loginBD;

    public ElementManager(Context context) {
        loginBD = new BaseDeDatosLogin(context);
    }

    public void agregarElemento(Element element) {
        loginBD.agregarElemento(element.getId(), element.getTitle(), element.getDescription(), element.getImageResource());
    }

    public void actualizarElemento(Element element)  {
        loginBD.actualizarElemento(element.getId(), element.getTitle(), element.getDescription(), element.getImageResource());
    }

    public void borrarElemento(Element element) {
        loginBD.borrarElemento(element.getId());
    }
}
