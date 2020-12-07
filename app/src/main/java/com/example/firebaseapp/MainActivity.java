package com.example.firebaseapp;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar ();
        if (actionBar != null) {
            actionBar.setTitle ("UserInfoApp");
        }

        getSupportFragmentManager ()
                .beginTransaction ()
                .add (R.id.rootContainer, new LoginFragment())
                .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit ();
    }


    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {

        int id = item.getItemId ();
        if (id == R.id.mnuRegister) {
            showRegisterView();
        }
        if(id == R.id.mnuAñadir){
            showAddView();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRegisterView () {
        getSupportFragmentManager ()
                .beginTransaction ()
                .addToBackStack (null)
                .replace (R.id.rootContainer, new RegisterFragment())
                .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit ();
    }
    public void showAddView () {
        getSupportFragmentManager ()
                .beginTransaction ()
                .addToBackStack (null)
                .replace (R.id.rootContainer, new FormFragment())
                .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit ();
    }
}