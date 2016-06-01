package com.example.pavel.moneyflow.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pavel.moneyflow.R;
import com.example.pavel.moneyflow.dialogs.AddNewExpencyDialog;
import com.example.pavel.moneyflow.util.Prefs;

public class DashboardActivity extends AppCompatActivity {

    Button btnExpenses;
    Button btnJoin;
    TextView tvExpenses;
    TextView tvExpensesNames;

    //JUST TEST



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnExpenses = (Button) findViewById(R.id.btnExpenses);
        btnJoin = (Button) findViewById(R.id.btnExpenses);

        tvExpenses = (TextView) findViewById(R.id.tvExpenses);
        tvExpensesNames = (TextView) findViewById(R.id.tvExpensesNames);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_expency:
                AddNewExpencyDialog expencyDialog = new AddNewExpencyDialog();
                expencyDialog.show(getSupportFragmentManager(), "ED");
                break;
            case R.id.item_user_profile:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);

                break;
        }
        return true;
    }

    public void onClickBtnExpenses(View view) {

        Intent intent = new Intent(this, ExpensesActivity.class);
        startActivity(intent);

//        Cursor cursorNames = getContentResolver().query(Prefs.URI_EXPENSE_NAME,
//                null, null, null, null, null);
////        Log.d(Prefs.LOG_TAG, cursorNames.getCount() + " - cursor names size");
//        StringBuilder builderNames = new StringBuilder();
//        while (cursorNames.moveToNext()){
//            builderNames.append(cursorNames.getString(cursorNames.getColumnIndex(
//                    Prefs.EXPENCE_NAMES_FIELDS_NAME)) + "\n");
//        }
//        tvExpensesNames.setText(builderNames.toString());

        Cursor cursorExpenses = getContentResolver().query(Prefs.URI_EXPENSE, null, null,
                null, null, null);
//        Log.d(Prefs.LOG_TAG, cursorExpenses.getCount() + " - cursor expenses size");
        StringBuilder builderExpenses = new StringBuilder();
        while (cursorExpenses.moveToNext()){
            builderExpenses.append(cursorExpenses.getInt(
                    cursorExpenses.getColumnIndex(Prefs.EXPENSE_FIELD_VOLUME)) + "\n");
            builderExpenses.append(cursorExpenses.getString(
                    cursorExpenses.getColumnIndex(Prefs.EXPENSE_FIELD_DATE)) + "\n");
            builderExpenses.append(cursorExpenses.getInt(
                    cursorExpenses.getColumnIndex(Prefs.EXPENSE_FIELD_ID_PASSIVE)) + "\n");
            builderExpenses.append("-------------");
        };
        tvExpenses.setText(builderExpenses.toString());

    }

    public void onClickBtnJoin(View view) {
        Cursor c = getContentResolver().query(Prefs.URI_EXPENSE_JOINED, null, null, null, null, null);
        while (c.moveToNext()){
            Log.d(Prefs.LOG_TAG, c.getString(c.getColumnIndex(Prefs.EXPENCE_NAMES_FIELDS_NAME)));
            Log.d(Prefs.LOG_TAG, c.getString(c.getColumnIndex(Prefs.EXPENSE_FIELD_VOLUME)));
            Log.d(Prefs.LOG_TAG, c.getString(c.getColumnIndex(Prefs.EXPENSE_FIELD_DATE)));
            Log.d(Prefs.LOG_TAG, "-----------------------------");
        }

    }
}
