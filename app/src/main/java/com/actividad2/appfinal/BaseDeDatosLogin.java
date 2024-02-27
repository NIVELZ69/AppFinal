package com.actividad2.appfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
    private static final String COLUMN_TITLE = "titulo";
    private static final String COLUMN_DESCRIPTION = "descripcion";
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

    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
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

    public Long getUserId(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Usuario", new String[]{"id"}, "email=?", new String[]{email}, null, null, null);
        Long userId = null;

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("id");
            if (columnIndex != -1) {
                userId = cursor.getLong(columnIndex);
            }
            cursor.close();
        }

        return userId;
    }

    public List<Element> getElementsForUserId(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_ELEMENTS + " WHERE " + COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        List<Element> elements = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int imageResource = cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE_RESOURCE));

                Element element = new Element(id, userId, title, description, imageResource);
                elements.add(element);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return elements;
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