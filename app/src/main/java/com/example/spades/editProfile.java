package com.example.spades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class editProfile extends AppCompatActivity {
    private EditText username;
    private TextView neighbourPhone;
    private TextView guardPhone;
    private Button saveButton;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_NEIGHBOUR_CONTACT = "contactNeighbour";
    private static final String KEY_GUARD_CONTACT = "contactGuard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        username = findViewById(R.id.username);
        neighbourPhone = findViewById(R.id.neighbourPhone);
        guardPhone = findViewById(R.id.guardPhone);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        
        saveButton = findViewById(R.id.save);
//        SAVING ALL THE INFO OF THE USER ON CLICKING THE SAVE BUTTON -----
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PUTTING ALL THE VALUES INPUTTED BY THE USER IN SHARED PREF
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, username.getText().toString());
                editor.putString(KEY_NEIGHBOUR_CONTACT, neighbourPhone.getText().toString());
                editor.putString(KEY_GUARD_CONTACT, guardPhone.getText().toString());
                editor.apply();
                Intent intent = new Intent(view.getContext(), account.class);
                startActivity(intent);
            }
        });
    }
}