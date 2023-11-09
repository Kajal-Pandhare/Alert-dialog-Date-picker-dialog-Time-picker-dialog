package com.example.alertdialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button alertButton;
    private Button timePickerButton;
    private Button datePickerButton;
    private Button buttonLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        alertButton = findViewById(R.id.btnAlert);
        timePickerButton = findViewById(R.id.btnTimePickerDialog);
        datePickerButton = findViewById(R.id.btnDatePickerdialog);
        buttonLogin = findViewById(R.id.btnLogin);


        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Bitcode");
                builder.setMessage("I need job");
                builder.setIcon(R.mipmap.ic_launcher);

                DialogInterface.OnClickListener listener = new AlertDialogButtonListener();

                builder.setPositiveButton("yes", listener);
                builder.setNegativeButton("no",listener);
                builder.setNeutralButton("I dont no",listener);
                builder.show();
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mt("Dialog is canceled");
                    }
                });
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mt("Dialog is dissmised");
                    }
                });
                builder.setCancelable(true);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                timePickerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,new MyTimeSetListener(),2,120,false);
                        timePickerDialog.show();
                    }
                });
                datePickerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,new MyDateSetListener(),2023,8,1);
                        datePickerDialog.show();
                    }
                });
            }
        });
        buttonLogin.setOnClickListener(new MyButtonLoginListener());
    }

    private class MyButtonLoginListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.login_dialog);

            EditText edtUsername = dialog.findViewById(R.id.edtUsername);
            EditText edtPassword = dialog.findViewById(R.id.edtPassword);
            Button btnLogin = dialog.findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edtUsername.getText().toString().equals("kajal") && edtPassword.getText().toString().equals("12345")){
                        mt("login successfully");
                        dialog.dismiss();
                    }
                    else {
                        mt("login failed");
                        dialog.dismiss();
                    }

                }
            });
            dialog.show();
        }
    }
    private class MyTimeSetListener implements TimePickerDialog.OnTimeSetListener{
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            timePickerButton.setText(hourOfDay + " : " + minute);
        }
    }
    private class MyDateSetListener implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            datePickerButton.setText(year + " : " + month + " : " + dayOfMonth);
        }
    }

    private class AlertDialogButtonListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    mt("yes");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    mt("no");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    mt("dont no");
                    break;
            }
        }
        private class AlertDialogPositiveButtonListener implements DialogInterface.OnClickListener{
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mt("I said yes");
            }
        }
        private class AlertDialogNegativeButtonListener implements DialogInterface.OnClickListener{
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mt("I said no");
            }
        }
        private class AlertDialogNutralButtonListener implements DialogInterface.OnClickListener{
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mt("I am confused");
            }
        }
    }
    private void mt(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
