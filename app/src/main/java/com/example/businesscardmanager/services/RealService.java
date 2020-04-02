package com.example.businesscardmanager.services;

import android.content.Context;
import android.widget.Toast;

import com.example.businesscardmanager.models.User;

import io.realm.Realm;

public class RealService {

    public User currentUser;

    private Realm realm;
    private  Context context;

    public void initRealm(Context context){
        // Initialize Realm
        this.context = context ;
        Realm.init(context);

        // Get a Realm instance for this thread
         realm = Realm.getDefaultInstance();
    }

    public User getUserWithEmailPassword(String email, String password){

         User user =   this.realm.where(User.class).equalTo("emailAdress",email)
                 .and()
                 .equalTo("password",password)
                 .findFirst();
         
        return user;
    }

    public User saveUser(User user){

        //persist the data into transaction

        this.realm.beginTransaction();
        this.realm.copyToRealm(user);
        this.realm.commitTransaction();

        return user;
    }


    public User getCurrentUser(){

        return currentUser;
    }

    public boolean isEmailAlreadyUse(String email) {

       long number =  realm.where(User.class).equalTo("emailAdress",email).count();
       if (number == 1) return true;

       return false;
    }
}
