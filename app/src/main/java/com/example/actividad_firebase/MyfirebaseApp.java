package com.example.actividad_firebase;

import com.google.firebase.database.FirebaseDatabase;

//creamos clase para la persistencia de datos
//en el maifest debemos de pasar el parametro que contiene esta clase

public class MyfirebaseApp extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
