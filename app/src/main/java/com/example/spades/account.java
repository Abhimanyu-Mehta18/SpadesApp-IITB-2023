package com.example.spades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class account extends AppCompatActivity {
    private TextView textView;
    private TextView textView1;
    private TextView usernameText;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_NEIGHBOUR_CONTACT = "contactNeighbour";
    private static final String KEY_GUARD_CONTACT = "contactGuard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
//        GETTING VALUES FROM SHARED PREF -------
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String contactN = sharedPreferences.getString(KEY_NEIGHBOUR_CONTACT, null);
        String contactG = sharedPreferences.getString(KEY_GUARD_CONTACT, null);

//        SETTING TEXT ------
        usernameText = findViewById(R.id.textView5);
        usernameText.setText(name);

        textView = findViewById(R.id.textView7);
        textView.setText(contactN);

        textView1 = findViewById(R.id.textView8);
        textView1.setText(contactG);
    }

//    EDIT PROFILE BUTTON ------
    public void edit_profile(View v) {
        Intent intent = new Intent(this, editProfile.class);
        startActivity(intent);
    }

//    BACK BUTTON --------
    public void back(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}