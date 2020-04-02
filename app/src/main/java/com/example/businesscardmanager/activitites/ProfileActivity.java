package com.example.businesscardmanager.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etCompName;
    private EditText etTel;
    private Button btnSave;

    private SharedPrefs sharedPrefs;
    private User currentUser;
    private RealService realmService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();
        bindViews();
        setListner();
    }

    private void init() {
        sharedPrefs = new SharedPrefs(this);
        currentUser = sharedPrefs.getCurrentUser();
        realmService = new RealService();
        realmService.initRealm(this);

    }

    private void bindViews() {
        etName = findViewById(R.id.et_name);
        etCompName = findViewById(R.id.et_comp_name);
        etTel = findViewById(R.id.et_telephone);
        btnSave = findViewById(R.id.btn_save);
    }

    private void setListner() {

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_save){

            String name = etName.getText().toString();
            String companyName = etCompName.getText().toString();
            String telephone = etTel.getText().toString();

           updateCurrentUser(name,companyName,telephone);
        }

    }

    private void updateCurrentUser(String name,String companyName,String telephone) {

        //Once we have finished to update the current User let send the user to the main activity

        currentUser.setName(name);
        currentUser.setCompanyName(companyName);
        currentUser.setTelephone(telephone);
        sharedPrefs.setCurrentUser(currentUser);

        //save the user into the realm database
        realmService.saveUser(currentUser);
        //send the user to main
        sendToMain();
    }

    private void sendToMain() {


        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);

        //animate the view
        overridePendingTransition(R.anim.pull_in_right,R.anim.pull_out_left);
        finish();
    }
}
