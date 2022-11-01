package com.example.sewavespa.auth.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sewavespa.R;
import com.example.sewavespa.SewaActivity;
import com.example.sewavespa.api.ApiClient;
import com.example.sewavespa.api.ApiInterface;
import com.example.sewavespa.auth.register.RegisterActivity;
import com.example.sewavespa.model.login.Login;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnReg;
    TextInputLayout emailInp, passInp;
    String tujuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent i = getIntent();
        tujuan = i.getStringExtra("tujuan");

        SharedPreferences sharedPreferences = getSharedPreferences("my-app", MODE_PRIVATE);

        btnLogin = findViewById(R.id.btn_login);
        btnReg = findViewById(R.id.btn_create_acc);
        emailInp = findViewById(R.id.email_login);
        passInp = findViewById(R.id.password_login);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = emailInp.getEditText().getText().toString();
                password = passInp.getEditText().getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("login", "yes");
                editor.apply();

                Log.d("tag", "login");

                login(email, password);
            }
        });
    }

    private void login(String email, String password) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginResponse(email, password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Intent intent = new Intent(LoginActivity.this, SewaActivity.class);
                startActivity(intent);
                Log.d("tag8", "login44");
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.d("Login", "Masuk Gagal!");
            }
        });
    }
}