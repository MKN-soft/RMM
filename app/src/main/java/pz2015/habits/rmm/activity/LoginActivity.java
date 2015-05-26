package pz2015.habits.rmm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pz2015.habits.rmm.R;


public class LoginActivity extends ActionBarActivity {

    private Button _loginBtn;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Sprawdza czy już się logowaliśmy za pierwszym razem
        SharedPreferences prefs = getSharedPreferences("rmm_sign_up", MODE_PRIVATE);
        String savedUsername = prefs.getString("username", null);
        String savedPassword = prefs.getString("password", null);

        if (savedUsername != null && savedPassword != null) {
            //Hooking Activity
            Intent intent = new Intent(LoginActivity.this, LoadingActivity.class);
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _loginBtn = (Button) findViewById(R.id.btn_login);

        _loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText edUser = (EditText) findViewById(R.id.fld_username);
                EditText edPswd = (EditText) findViewById(R.id.fld_pwd);

                username = edUser.getText().toString();
                password = edPswd.getText().toString();

                SharedPreferences prefs = getSharedPreferences("rmm_sign_up", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("username", username);
                editor.putString("password", password);

                editor.commit();

                //Hooking Activity
                Intent intent = new Intent(LoginActivity.this, LoadingActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addHabit) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
