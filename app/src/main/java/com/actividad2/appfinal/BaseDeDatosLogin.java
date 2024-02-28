package com.actividad2.appfinal;

import static android.app.DownloadManager.COLUMN_DESCRIPTION;
import static android.app.DownloadManager.COLUMN_TITLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDatosLogin extends SQLiteOpenHelper {

    //Versión de la Base de Datos
    private static final int DATABASE_VERSION = 1;

    // Nombre de la Base de Datos
    private static final String DATABASE_NAME = "LoginBD";

    // Nombre de Tabla
    private static final String TABLE_USERS = "Usuario";
    private static final String TABLE_ELEMENTS = "Elementos";

    // Nombres de Columnas
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "contrasenia";
    private static final String COLUMN_USER_ID = "id_usuario";
    private static final String COLUMN_IMAGE_RESOURCE = "imagen";

    // Consulta de tabla
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASSWORD + " TEXT" + ")";

    private static final String CREATE_TABLE_ELEMENTS = "CREATE TABLE " + TABLE_ELEMENTS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_ID + " INTEGER,"
            + COLUMN_TITLE + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_IMAGE_RESOURCE + " INTEGER,"
            + "FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + ")" + ")";


    public BaseDeDatosLogin(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_ELEMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean login (String email, String contrasenia) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_ID}, COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, contrasenia}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public long insertUser(String email, String contrasenia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, contrasenia);
        long id = db.insert(TABLE_USERS, null, values);
        db.close();
        return id;
    }

    public void agregarElemento(long userId, String title, String description, int imageResource) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_ID, userId); // Utiliza el ID del usuario para asociar el elemento con el usuario correcto
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_IMAGE_RESOURCE, imageResource);

        db.insert(TABLE_ELEMENTS, null, values); // Inserta en la tabla de elementos
        db.close();
    }

    public void actualizarElemento(long id, String title, String description, int imageResource) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_IMAGE_RESOURCE, imageResource);

        db.update(TABLE_ELEMENTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void borrarElemento(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ELEMENTS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


}