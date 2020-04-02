package com.example.businesscardmanager.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.businesscardmanager.R;
import com.example.businesscardmanager.models.User;
import com.example.businesscardmanager.services.SharedPrefs;

import org.w3c.dom.Text;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class MainActivity extends AppCompatActivity {

    private SharedPrefs sharedPrefs;
    private User currentUser;

    private TextView tvUsername;
    private ImageView ivLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        init();
    }

    private void bindViews() {
        tvUsername = findViewById(R.id.tv_username);
        ivLogout = findViewById(R.id.iv_log_out);
    }

    private void init() {
        sharedPrefs = new SharedPrefs(this);

        currentUser = sharedPrefs.getCurrentUser();
        if (currentUser == null){
            sendToLoginActivity();
        }
        //in the case where the complete profile was interrupt
        //for some reasons
        else if (currentUser.getName() == null){
            sendToProfil();
        }

        else
        {
            tvUsername.setText(currentUser.getName());

            setListener();
        }

    }

    private void setListener() {
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefs.logout();
                sendToLoginActivity();
            }
        });
    }

    private void sendToProfil() {
        Intent intent = new Intent(this,ProfileActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);

        //animate the view
        overridePendingTransition(R.anim.pull_in_right,R.anim.pull_out_left);
        finish();
    }

    private void sendToLoginActivity() {

        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);

        //animate the view
        overridePendingTransition(R.anim.pull_in_right,R.anim.pull_out_left);
        finish();
    }
}
