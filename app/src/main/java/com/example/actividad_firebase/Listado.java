package com.example.actividad_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.Profesionales;

public class Listado extends AppCompatActivity {

    private ListView listView;
    private Button bttEliminar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Profesionales profesionalSelecter; //guardar elemento seleccionado

    //mostrar datos [con esto podremoa hacaer que el listado se actualize de inmediato]
    private ArrayList<Profesionales> listaProfesionales = new ArrayList();
    ArrayAdapter<Profesionales> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        listView = (ListView)findViewById(R.id.lView);

        bttEliminar = (Button)findViewById(R.id.btt_eliminar);

        //refrescr listview

        obtenerDatabase();

        //metodo para obtener la seleccion de la lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //obtengo la posicion
                profesionalSelecter = (Profesionales) parent.getItemAtPosition(position);


            }
        });

        //mostrar datos
        databaseReference.child("Profesionales").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //actualizar lista
                listaProfesionales.clear();

                for(DataSnapshot op: snapshot.getChildren()){ //recorremos la cantidad profesinales

                    Profesionales p = op.getValue(Profesionales.class); //Guardo consulta en obj

                    listaProfesionales.add(p);

                    arrayAdapter = new ArrayAdapter<Profesionales>(getBaseContext(), android.R.layout.simple_list_item_1, listaProfesionales);

                    listView.setAdapter(arrayAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void elminarProfesionales(View view) throws InterruptedException {
        Profesionales p = new Profesionales();
        p.setId(profesionalSelecter.getId());

        databaseReference.child("Profesionales").child(p.getId()).removeValue();//elimina lo seleccionado
        Toast.makeText(getBaseContext(),"Profesional Eliminado", Toast.LENGTH_SHORT).show();

    }

    public void obtenerDatabase(){
        FirebaseApp.initializeApp(getBaseContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

}