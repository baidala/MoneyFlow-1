package com.example.pavel.moneyflow.dialogs;


import android.app.Activity;
import android.app.Dialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.pavel.moneyflow.R;
import com.example.pavel.moneyflow.service.MyIntentService;
import com.example.pavel.moneyflow.util.Prefs;

public class AddNewExpencyDialog extends DialogFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    EditText etVolumeOfExpenses;
    AutoCompleteTextView acNameOfExpenses;
    SimpleCursorAdapter simpleCursorAdapter;
    Activity activity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        activity = getActivity();

        activity.getLoaderManager().initLoader(Prefs.ID_EXPENSE_NAMES, null, this);

        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_add_expency, null, false);
        //TODO set adapter for AutoCompleateTextView

        etVolumeOfExpenses = (EditText) view.findViewById(R.id.etVolumeOfExpenses);
        acNameOfExpenses = (AutoCompleteTextView) view.findViewById(R.id.acNameOfExpenses);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view)
            .setMessage(R.string.message_add_new_expenses)
            .setTitle(R.string.title_add_new_expency_dialog)
            .setPositiveButton(R.string.positive_button_add_new_expency_dialog, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    addNewExpense();
                }
            })
            .setNegativeButton(R.string.negative_button_add_new_expency_dialog, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dismiss();
                }
            });

        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void addNewExpense() {
        String name = acNameOfExpenses.getText().toString();
        int volume = Integer.parseInt(etVolumeOfExpenses.getText().toString());

        MyIntentService.startActionInsertExpency(activity, name, volume);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), Prefs.URI_EXPENSE_NAME, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, final Cursor data) {
        simpleCursorAdapter = new SimpleCursorAdapter(
                getContext(),
                android.R.layout.simple_dropdown_item_1line,
                data,
                new String[]{Prefs.EXPENCE_NAMES_FIELDS_NAME},
                new int[]{android.R.id.text1},
                Adapter.NO_SELECTION
        );
        acNameOfExpenses.setAdapter(simpleCursorAdapter);
        acNameOfExpenses.setValidator(new AutoCompleteTextView.Validator() {
            @Override
            public boolean isValid(CharSequence text) {
                data.moveToFirst();
                do {
                    String localNameValue = data.getString(data.getColumnIndex(Prefs.EXPENCE_NAMES_FIELDS_NAME));

                } while ( data.moveToNext());
                return false;
            }

            @Override
            public CharSequence fixText(CharSequence invalidText) {
                return null;
            }
        });

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
