package com.example.actividad_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import Model.Profesionales;


public class MainActivity extends AppCompatActivity {

    private EditText edtNombre, edtSalario, edtProfesion;
    private Button bttAgregar, bttListado;



    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText)findViewById(R.id.edt_nombre);
        edtSalario = (EditText)findViewById(R.id.edt_salario);
        edtProfesion = (EditText)findViewById(R.id.edt_profesion);

        bttAgregar = (Button)findViewById(R.id.btt_guardar);
        bttListado = (Button)findViewById(R.id.btt_listado);

        obtenerDatabase();

        //añadir datos
        bttAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Profesionales p = new Profesionales();

                p.setId(UUID.randomUUID().toString());
                p.setNombre(edtNombre.getText().toString());
                p.setSalario(edtSalario.getText().toString());
                p.setProfesion(edtProfesion.getText().toString());

                databaseReference.child("Profesionales").child(p.getId()).setValue(p);

                limpiarCampos();

                Toast.makeText(getBaseContext(),"Profesional Agregado",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void obtenerDatabase(){
        FirebaseApp.initializeApp(getBaseContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        //persistencia de firebase para que almacene los datos hasta sin conexion a internet
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    public void limpiarCampos(){
        edtNombre.setText("");
        edtSalario.setText("");
        edtProfesion.setText("");
    }

    public void Listado(View view){
        Intent i = new Intent(this, Listado.class);
        startActivity(i);
    }
}