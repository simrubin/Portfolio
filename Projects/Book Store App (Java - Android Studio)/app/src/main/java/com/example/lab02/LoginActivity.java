package com.example.lab02;

import static com.example.lab02.SignUpActivity.PSWRD_KEY;
import static com.example.lab02.SignUpActivity.USER_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private String correctUsername;
    private String correctPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        EditText username = findViewById(R.id.username_Et);
        EditText password = findViewById(R.id.password_Et);

        Button loginBtn = findViewById(R.id.login_btn);
        Button SignUpBtn = findViewById(R.id.signup_btn);

        SharedPreferences sP = getSharedPreferences("loginDetails.ext",MODE_PRIVATE);
        correctUsername = sP.getString(USER_KEY,"user");
        correctPassword = sP.getString(PSWRD_KEY,"password");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals(correctUsername) && password.getText().toString().equals(correctPassword)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    public void togglePswrd(View view){
        EditText password = findViewById(R.id.password_Et);
        int selectionStart = password.getSelectionStart();
        int selectionEnd = password.getSelectionEnd();
        if (password.getTransformationMethod() instanceof PasswordTransformationMethod) {
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        password.setSelection(selectionStart, selectionEnd);
    } // I used ChatGPT to help develop this function.
}