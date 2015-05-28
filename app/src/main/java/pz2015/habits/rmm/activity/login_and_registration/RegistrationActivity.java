package pz2015.habits.rmm.activity.login_and_registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pz2015.habits.rmm.R;

public class RegistrationActivity extends ActionBarActivity {

    private Button registrationBtn;
    private EditText etEmailAddress;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registerViews();
    }

    private void registerViews() {
        etEmailAddress = (EditText) findViewById(R.id.fld_username);
        etEmailAddress.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validate.isEmailAddress(etEmailAddress, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etPassword = (EditText) findViewById(R.id.fld_pwd);
        etPassword.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validate.isPassword(etPassword, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        registrationBtn = (Button) findViewById(R.id.btn_login);
        registrationBtn.setOnClickListener(new View.OnClickListener() {
            /*
            Validation class will check the error and display the error on respective fields
            but it won't resist the form submission, so we need to check again before submit
             */
            @Override
            public void onClick(View v) {
                if (checkValidation())
                    submitForm();
                else
                    Toast.makeText(RegistrationActivity.this, "Form contains error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean checkValidation() {
        boolean result = true;

        if (!Validate.isEmailAddress(etEmailAddress, true)) result = false;
        if (!Validate.isPassword(etPassword, true)) result = false;

        return result;
    }

    private void submitForm() {
        SharedPreferences prefs = getSharedPreferences("rmm_sign_up", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", etEmailAddress.getText().toString().trim());
        editor.putString("password", etPassword.getText().toString().trim());

        editor.commit();

        //Hooking Activity
        Intent intent = new Intent(RegistrationActivity.this, LoadingActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
