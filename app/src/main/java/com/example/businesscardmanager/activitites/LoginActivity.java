package com.example.businesscardmanager.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businesscardmanager.R;
import com.example.businesscardmanager.models.User;
import com.example.businesscardmanager.services.RealService;
import com.example.businesscardmanager.services.SharedPrefs;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvCreateAccount;

    private RealService realmService;

    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        bindViews();
        setListener();
    }

    private void init() {
        sharedPrefs = new SharedPrefs(this);
       realmService =  new  RealService();
       realmService.initRealm(this);

    }

    private void bindViews() {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvCreateAccount = findViewById(R.id.tv_create_account);

    }

    private void setListener() {

        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()  == R.id.btn_login){

            String email = etEmail.getText().toString();
            String password =etPassword.getText().toString();

            if (checkInputs(email,password)){


               User user = realmService.getUserWithEmailPassword(email,password);

               if (user == null) {

                   Toast.makeText(this, "Email or password incorrect", Toast.LENGTH_SHORT).show();
               }else{

                   //Save the current user and send him in main activity
                   sharedPrefs.setCurrentUser(user);
                   sendToMain();
               }
            }
            else{
                Toast.makeText(this, "Please fill correctly the field", Toast.LENGTH_SHORT).show();
            }
        }

        if(v.getId()  == R.id.tv_create_account){
            sendToRegister();
        }

    }

    private void sendToMain() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);

        //animate the view
        overridePendingTransition(R.anim.pull_out_left,R.anim.pull_in_right);
        finish();
    }

    private void sendToRegister() {


        Intent registerIntent = new Intent(this,RegisterActivity.class);
        registerIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(registerIntent);

        //animate the view
        overridePendingTransition(R.anim.pull_out_left,R.anim.pull_in_right);
        finish();


    }

    private boolean checkInputs(String email, String password) {

        //Define some characteristique
        if(!TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password ) ){
            return true;
        }

        return false;
    }
}
