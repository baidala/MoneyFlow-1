package com.example.pavel.moneyflow.activity;




import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;

import com.example.pavel.moneyflow.R;





public class ProfileActivity extends AppCompatPreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener  {

    String summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //addPreferencesFromResource(R.xml.profile);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        EditTextPreference editTextPreference = (EditTextPreference) findPreference(key);
        summary = sharedPreferences.getString(key, getResources().getString(R.string.sumary_login_pref));

    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.profile);
        }
    }


}
