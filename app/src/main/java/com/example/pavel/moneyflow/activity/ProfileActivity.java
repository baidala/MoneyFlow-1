package com.example.pavel.moneyflow.activity;



import android.os.Bundle;
import android.preference.PreferenceActivity;


import com.example.pavel.moneyflow.R;



public class ProfileActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.profile);

    }
}
