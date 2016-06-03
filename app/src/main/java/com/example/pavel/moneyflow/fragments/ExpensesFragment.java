package com.example.pavel.moneyflow.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pavel.moneyflow.util.Prefs;

import java.util.HashMap;

/**
 * Created by oracle on 6/3/16.
 */
public class ExpensesFragment extends Fragment implements LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    private static final String CURRENT_MONTH = "current";
    TextView tvExpensesSummary;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate( android.R.layout.simple_list_item_1, container , false);
        tvExpensesSummary = (TextView)view.findViewById(android.R.id.text1);
        getActivity().getSupportLoaderManager().initLoader(1, null, this);
        return view;
    }


    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        Cursor cursor = getActivity().getContentResolver().query(Prefs.URI_EXPENSE, new String[]{Prefs.EXPENSE_FIELD_VOLUME}, null, null, null);
        int value = 0;
        do {
            value +=cursor.getInt(cursor.getColumnIndex(Prefs.EXPENSE_FIELD_VOLUME));
        } while (cursor.moveToNext());
        HashMap<String, String> result = new HashMap<>();
        result.put(CURRENT_MONTH,   String.valueOf(value));

        return new HashMapLoader(getActivity(), result);
    }

    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {

    }

    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }

    private class HashMapLoader extends Loader<HashMap<String, String>> {
        public HashMapLoader(Context context, HashMap<String, String> result) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
        }
    }
}
