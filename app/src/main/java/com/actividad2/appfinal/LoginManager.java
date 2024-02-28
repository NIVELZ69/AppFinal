package com.actividad2.appfinal;

import android.content.Context;

public class LoginManager {
    private BaseDeDatosLogin loginBD;
    private long userId;

    public LoginManager(Context context) {
        loginBD = new BaseDeDatosLogin(context);
    }

    public boolean login(String email, String contrasenia) {
        boolean loginSuccessful = loginBD.login(email, contrasenia);
        if (loginSuccessful) {
            userId = loginBD.getUserId(email);
        }

        return loginSuccessful;
    }

    public long getCurrentUserId() {
        return userId;
    }

}
