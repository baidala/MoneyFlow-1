package com.example.pavel.moneyflow.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.pavel.moneyflow.R;
import com.example.pavel.moneyflow.adapters.DashboardPagerAdapter;
import com.example.pavel.moneyflow.dialogs.AddNewExpencyDialog;
import com.example.pavel.moneyflow.util.Prefs;



public class DashboardActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    Button btnExpenses;
    Button btnJoin;
    TextView tvExpenses;

    DashboardPagerAdapter dashboardPagerAdapter;
    ViewPager viewPager;
    Toolbar toolbar;
    int tabPosition = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        dashboardPagerAdapter = new DashboardPagerAdapter(this, getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.vpDashboard);
        viewPager.setAdapter(dashboardPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabDashboard);
        tabLayout.setupWithViewPager(viewPager);


        /*
        btnExpenses = (Button) findViewById(R.id.btnExpenses);

        btnJoin = (Button) findViewById(R.id.btnExpenses);

        tvExpenses = (TextView) findViewById(R.id.tvExpenses);
        tvExpensesNames = (TextView) findViewById(R.id.tvExpensesNames);
        */
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
        getMenuInflater().inflate(R.menu.menu_cash_flow, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        switch (tabPosition){
            case DashboardPagerAdapter.FRAGMENT_CASH_FLOW:
                getMenuInflater().inflate(R.menu.menu_cash_flow, menu);
                break;
            case DashboardPagerAdapter.FRAGMENT_INCOMES:
                getMenuInflater().inflate(R.menu.menu_incomes, menu);
                break;
            case DashboardPagerAdapter.FRAGMENT_EXPENSES:
                getMenuInflater().inflate(R.menu.menu_expences, menu);
                break;

        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.item_list_expences:
                intent = new Intent(this, ExpensesActivity.class);
                startActivity(intent);
                //AddNewExpencyDialog expencyDialog = new AddNewExpencyDialog();
                //expencyDialog.show(getSupportFragmentManager(), "ED");
                break;
            case R.id.item_user_profile:
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);

                break;
            case R.id.item_list_incomes:
                AddNewExpencyDialog incomesDialog = new AddNewExpencyDialog();
                incomesDialog.show(getSupportFragmentManager(), "ED");
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

    public void setFragmentInfo(int position) {
        switch (position) {
            case 0:
                toolbar.setTitle(R.string.title_tab_cash_flow);

                break;
            case 1:
                toolbar.setTitle(R.string.title_tab_expences);
                break;
            case 2:
                toolbar.setTitle(R.string.title_tab_incomes);
                break;

        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(Prefs.LOG_TAG, "onPageScrolled: " +" |" + position + " | "+ positionOffset+ " | "+ positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        Log.d(Prefs.LOG_TAG, "onPageSelected: " + " | " + position);

        this.tabPosition = position;

        switch (tabPosition) {
            case 0:
                toolbar.setTitle(R.string.app_name);
                break;
            case 1:
                toolbar.setTitle(R.string.title_tab_expences);
                break;
            case 2:
                toolbar.setTitle(R.string.title_tab_incomes);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d(Prefs.LOG_TAG, "onPageScrollStateChanged: " + state);
    }
}
