package temple.edu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "Greeting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fieldValidation()) {
                    showToast("You must enter all information");
                }
                else if (!pwdValidation()) {
                    showToast("Password and Confirm Password does not match");
                }
                else
                sendMessage(v);
            }
        });
    }

    /** Called when the user taps the Save button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.txtUsername);
        String username = editText.getText().toString();
        String greeting = "Welcome, " + username + ",\n to the SignUpForm App";
        intent.putExtra(EXTRA_MESSAGE, greeting);
        startActivity(intent);
    }

    /** Validate the fields */
    public boolean fieldValidation() {
        EditText usernameEditText = (EditText) findViewById(R.id.txtUsername);
        EditText emailEditText = (EditText) findViewById(R.id.txtEmail);
        EditText pwdEditText = (EditText) findViewById(R.id.txtPwd);
        EditText pwdConfirmEditText = (EditText) findViewById(R.id.txtPwdConfirm);

        if (usernameEditText.getText().toString().matches("") || emailEditText.getText().toString().matches("")
                || pwdEditText.getText().toString().matches("") || pwdConfirmEditText.getText().toString().matches("") ) {
            return false;
        }
        return true;
    }

    /** Validate if the passwords match */
    public boolean pwdValidation() {
        EditText pwdEditText = (EditText) findViewById(R.id.txtPwd);
        EditText pwdConfirmEditText = (EditText) findViewById(R.id.txtPwdConfirm);

        if (pwdEditText.getText().toString().matches(pwdConfirmEditText.getText().toString()))
            return true;
        return false;
    }

    /** Print Toast */
    public void showToast(CharSequence msg) {
        Context context = getApplicationContext();
        CharSequence error_fieldValidation = msg;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, error_fieldValidation, duration);
        toast.show();
    }
}