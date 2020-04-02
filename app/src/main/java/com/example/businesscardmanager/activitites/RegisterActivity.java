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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfPassword;
    private Button btn_register;

    private TextView tv_have_already_account;
    private SharedPrefs sharedPrefs;
    private RealService realmService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        bindViews();
        setListener();
    }

    private void init() {

        sharedPrefs = new SharedPrefs(this);
        realmService = new RealService();
        realmService.initRealm(this );
    }

    private void bindViews() {

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfPassword = findViewById(R.id.et_conf_password);
        btn_register = findViewById(R.id.btn_register);
        tv_have_already_account = findViewById(R.id.tv_have_already_account);
    }

    private void setListener() {

        btn_register.setOnClickListener(this);
        tv_have_already_account.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()  == R.id.btn_register){

            String email = etEmail.getText().toString();
            String password =etPassword.getText().toString();
            String confPassword = etConfPassword.getText().toString();

            if (checkInputs(email,password,confPassword)){


                User user = new User();
                user.setEmailAdress(email);
                user.setPassword(password);
                sharedPrefs.setCurrentUserLogin(user);
                sendToProfile();
            }

        }

        if(v.getId()  == R.id.tv_have_already_account){
            sendToLogin();
        }

    }

    private void sendToProfile() {

        Intent intent = new Intent(this,ProfileActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);

        //animate the view
        overridePendingTransition(R.anim.pull_out_right,R.anim.pull_in_left);
        finish();

    }

    private void sendToLogin() {

        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);

        //animate the view
        overridePendingTransition(R.anim.pull_out_right,R.anim.pull_in_left);
        finish();

    }

    private boolean checkInputs(String email, String password, String confPassword) {

        //Define some characteristique
        if(TextUtils.isEmpty(email)
                || TextUtils.isEmpty(password  )  ){

            Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        else {
            if(  !password.equals(confPassword)  ){
                Toast.makeText(this, "Passwords does not match", Toast.LENGTH_SHORT).show();
            }
        }

      if (realmService.isEmailAlreadyUse(email) ){
          Toast.makeText(this, "Email Address Already in use", Toast.LENGTH_SHORT).show();
          return false;
      }


        return true;
    }
}
