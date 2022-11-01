package com.example.sewavespa.auth.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sewavespa.R;
import com.example.sewavespa.SewaActivity;
import com.example.sewavespa.api.ApiClient;
import com.example.sewavespa.api.ApiInterface;
import com.example.sewavespa.auth.login.LoginActivity;
import com.example.sewavespa.model.login.Login;
import com.example.sewavespa.model.register.Register;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    
    Button btnReg, btnLogin;
    TextInputLayout nameInp, emailInp, passInp, confInp;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnReg = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login_acc);
        nameInp = findViewById(R.id.name_register);
        emailInp = findViewById(R.id.email_register);
        passInp = findViewById(R.id.password_register);
        confInp = findViewById(R.id.confirmation_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, email, password, confirm;

                name = nameInp.getEditText().getText().toString();
                email = emailInp.getEditText().getText().toString();
                password = passInp.getEditText().getText().toString();
                confirm = confInp.getEditText().getText().toString();

                register(name, email, password, confirm);
            }
        });
    }

    private void register(String name, String email, String password, String confirm) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> registerCall = apiInterface.registerResponse(name, email, password, confirm);
        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                login(email, password);
                Log.d("Register", "Berhasil");
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Log.d("Register", "Gagal");
            }
        });
    }

    private void login(String email, String password) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginResponse(email, password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Intent intent = new Intent(RegisterActivity.this, SewaActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.d("Login", "Masuk Gagal!");
            }
        });
    }
}