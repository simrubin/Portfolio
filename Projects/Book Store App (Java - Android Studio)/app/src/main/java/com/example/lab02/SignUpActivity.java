package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    public static final String USER_KEY = "user_key";
    public static final String PSWRD_KEY = "password_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText enterUser = findViewById(R.id.enterUsername_Et);
        EditText enterPwrd = findViewById(R.id.enterPassword_Et);
        EditText confirmPwrd = findViewById(R.id.confirmPassword_Et);

        Button signUpBtb = findViewById(R.id.signUp_btn);

        signUpBtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enterUser.getText().toString().isEmpty() || enterPwrd.getText().toString().isEmpty() || confirmPwrd.getText().toString().isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (enterPwrd.getText().toString().equals(confirmPwrd.getText().toString())) {
                    String username = enterUser.getText().toString();
                    String password = enterPwrd.getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences("loginDetails.ext", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(USER_KEY, username);
                    editor.putString(PSWRD_KEY,password);
                    editor.apply(); // be wary of this commit, could crash app

                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Passwords must match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}