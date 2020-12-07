package com.example.firebaseapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class FormFragment extends Fragment {
    //    private static final int RESULT_OK = 0;
    FirebaseAuth auth;
    DatabaseReference referencia;
    Button btnsave,btncambiar;
    EditText ednombre,edapellido,eddireccion,ededad,edtelefono;
    ImageView foto;
    StorageReference mStorage;
    private static final int GALLERY_INTENT = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btncambiar = view.findViewById(R.id.button);
        btnsave = view.findViewById(R.id.button2);
        ednombre = view.findViewById(R.id.etNombre);
        edapellido = view.findViewById(R.id.etApellido);
        eddireccion = view.findViewById(R.id.etDireccion);
        ededad = view.findViewById(R.id.etEdad);
        edtelefono = view.findViewById(R.id.etTel);
        foto = view.findViewById(R.id.imageView);
        referencia = FirebaseDatabase.getInstance().getReference("Users");
        mStorage = FirebaseStorage.getInstance().getReference();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = ednombre.getText().toString();
                String apellido = edapellido.getText().toString();
                String edad = ededad.getText().toString();
                String direccion = eddireccion.getText().toString();
                String telefono = edtelefono.getText().toString();

                enviardatos(nombre,apellido,edad,direccion,telefono);
                Toast.makeText (getContext (), "Register completed!", Toast.LENGTH_LONG).show ();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.rootContainer,new ListFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        });

        btncambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            Uri uri = data.getData();
            StorageReference filepath = mStorage.child("imagenes").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText (getContext (), "Upload File", Toast.LENGTH_SHORT).show ();
                }
            });
        }
    }

    public void enviardatos(String nombre, String apellido, String edad, String direccion, String telefono){

        Map<String,Object> datosUsuario = new HashMap<>();
        datosUsuario.put("apellido",apellido);
        datosUsuario.put("direccion",direccion);
        datosUsuario.put("edad",edad);
        datosUsuario.put("nombre",nombre);
        datosUsuario.put("telefono",telefono);

        referencia.push().setValue(datosUsuario);
    }
}


