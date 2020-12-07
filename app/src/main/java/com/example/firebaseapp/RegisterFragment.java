package com.example.firebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;


import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {
    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

        auth = FirebaseAuth.getInstance ();

        EditText edtEmail = view.findViewById (R.id.etEmailRegister);
        EditText edtPassword = view.findViewById (R.id.etPasswordlRegister);

        Button btnRegister = view.findViewById (R.id.btnRegister);
        btnRegister.setOnClickListener (v -> {
            registerUser (edtEmail.getText().toString (), edtPassword.getText().toString ());
        });
    }

    private void registerUser (String email, String password) {
        auth.createUserWithEmailAndPassword (email, password)
                .addOnCompleteListener (task -> {
                    if (task.isSuccessful ()) {
                        startActivity(new Intent(getActivity(),MainActivity.class));
                        Toast.makeText (getContext (), "Register completed!", Toast.LENGTH_LONG).show ();
                    } else {
                        if (task.getException () != null) {
                            Log.e("TYAM", task.getException().getMessage());
                        }

                        Toast.makeText (getContext (), "Register failed!", Toast.LENGTH_LONG).show ();
                    }
                });
    }
}
