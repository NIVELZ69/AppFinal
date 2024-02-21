package com.actividad2.appfinal;

import android.content.Context;

public class LoginManager {
    private BaseDeDatosLogin loginBD;

    public LoginManager(Context context) {
        loginBD = new BaseDeDatosLogin(context);
    }

    public boolean login(String email, String contrasenia) {
        return loginBD.login(email, contrasenia);
    }

}
