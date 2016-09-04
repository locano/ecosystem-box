package com.ingsoft.url.gudbox;

<<<<<<< HEAD
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
=======
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
>>>>>>> user_interface

public class seedInfo extends AppCompatActivity {

    private String seedName;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seed_info);
        Intent intent = getIntent();
        seedName = intent.getStringExtra("itemName");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(seedName);
        setSupportActionBar(toolbar);

<<<<<<< HEAD
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView helpIcon = (ImageView)findViewById(R.id.helpIcon);
        helpIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(seedInfo.this);

                View subView = inflater.inflate(R.layout.nutritional_values_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(seedInfo.this, R.style.MyAlertDialogStyle);

                builder.setTitle("Nutritional values");
                builder.setView(subView);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //finish();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    /**
     * This method creates an "onClickListener" to help icon.
     * After clicking it, a DialogView will be shown
     */
    private void showHelpDialog(View view){
        LayoutInflater inflater = LayoutInflater.from(seedInfo.this);

        View subView = inflater.inflate(R.layout.nutritional_values_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(seedInfo.this, R.style.MyAlertDialogStyle);

        builder.setTitle("Nutritional values");
        builder.setView(subView);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.create();
        builder.show();
        //alertDialog.show();
=======

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


>>>>>>> user_interface


    }

}
