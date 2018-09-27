package com.vladglush.lab4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Pattern;

public class MainActivity extends Activity {

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;
    private EditText fnameEdit;
    private EditText lnameEdit;
    private EditText emailEdit;
    private EditText phoneEdit;
    private EditText passwordEdit;
    private EditText confpassEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        spEditor = sp.edit();
        fnameEdit = findViewById(R.id.editTextFName);
        lnameEdit = findViewById(R.id.editTextLName);
        emailEdit = findViewById(R.id.editTextEmail);
        phoneEdit = findViewById(R.id.editTextPhone);
        passwordEdit = findViewById(R.id.editTextPassword);
        confpassEdit = findViewById(R.id.editTextConfirmPassword);
    }

    private String getFieldText(int id) {
        return ((EditText) findViewById(id)).getText().toString().trim();
    }

    private void clearFields() {
        fnameEdit.setText("");
        lnameEdit.setText("");
        emailEdit.setText("");
        phoneEdit.setText("");
        passwordEdit.setText("");
        confpassEdit.setText("");
    }

    private void addEntry(String firstName, String lastName, String phone) {
        String list = sp.getString("entry_list", "");
        list += firstName + "|" + lastName + "|" + phone + "&";

        spEditor.putString("entry_list", list);
        spEditor.apply();
    }

    public void onSubmitButtonClick(View v) {
        String firstName = getFieldText(R.id.editTextFName);
        String lastName = getFieldText(R.id.editTextLName);
        String email = getFieldText(R.id.editTextEmail);
        String phone = getFieldText(R.id.editTextPhone);
        String password = getFieldText(R.id.editTextPassword);
        String confirmPassword = getFieldText(R.id.editTextConfirmPassword);

        String errorsString = "";

        if (firstName.isEmpty()) {
            errorsString += "- first name is required\n";
        }

        if (lastName.isEmpty()) {
            errorsString += "- last name is required\n";
        }

        if (email.isEmpty()) {
            errorsString += "- email is required\n";
        } else if (!EmailValidator.getInstance().isValid(email)) {
            errorsString += "- email is invalid\n";
        }

        if (phone.isEmpty()) {
            errorsString += "- phone is required\n";
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            errorsString += "- phone is invalid\n";
        }

        if (password.isEmpty()) {
            errorsString += "- password is required\n";
        }

        if (confirmPassword.isEmpty()) {
            errorsString += "- password confirmation is required\n";
        } else if (!password.equals(confirmPassword)) {
            errorsString += "- passwords do not match\n";
        }

        if (!errorsString.isEmpty()) {
            // remove the last '\n'
            errorsString = errorsString.substring(0, errorsString.length() - 1);
        } else {
            addEntry(firstName, lastName, phone);

            clearFields();
        }

        ((TextView) findViewById(R.id.textViewErrors)).setText(errorsString);
    }

    public void onViewListButtonClick(View v) {
        Intent i = new Intent(this, EntryListActivity.class);
        startActivity(i);
    }
}
