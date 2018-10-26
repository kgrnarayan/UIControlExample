package com.example.vvitcodelabs.uicontrolexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder builder;
    
    Button save;
    EditText contact_name, contact_number, contact_email ;
    Spinner contact_type;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contact_name=findViewById(R.id.id_contact_name);
        contact_type=findViewById(R.id.id_contact_type);
        contact_number=findViewById(R.id.id_contact_number);
        contact_email=findViewById(R.id.id_contact_email);

        save=findViewById(R.id.id_contact_save);
        helper= new DBHelper(this, null,null,0);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you wish to save ?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Contact contact=new Contact();
                        contact.setContact_name(contact_name.getText().toString());
                        contact.setContact_number(contact_number.getText().toString());
                        contact.setContact_type(contact_type.getSelectedItem().toString());
                        contact.setContact_email(contact_email.getText().toString());

                        helper.storeData(contact);




                        Toast.makeText(getApplicationContext(), "contact Saved", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(MainActivity.this,DisplayActivity.class);
                        startActivity(intent);
                        finish();




                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

    }

    
}
