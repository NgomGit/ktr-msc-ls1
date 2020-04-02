package com.example.businesscardmanager.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.businesscardmanager.models.User;

import static com.example.businesscardmanager.utils.Const.CURRENT_USER_COMPANY;
import static com.example.businesscardmanager.utils.Const.CURRENT_USER_EMAIL;
import static com.example.businesscardmanager.utils.Const.CURRENT_USER_NAME;
import static com.example.businesscardmanager.utils.Const.CURRENT_USER_PASSWORD;
import static com.example.businesscardmanager.utils.Const.CURRENT_USER_TEL;

//sometimes it may happen that we want to use the shareprefs
//to save some kind of data to have beter performance in our app
public class SharedPrefs  {

    private Context context;
    private SharedPreferences pref;

    public SharedPrefs(Context context){
        this.context = context;
        init();
    }

    private void init() {
        pref = context.getSharedPreferences("MyPref", 0);
    }

    //set the currents user info
    public void setCurrentUser(User user){
        Toast.makeText(context, "user password "+user.getPassword(), Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putString(CURRENT_USER_NAME,  user.getName());
        editor.putString(CURRENT_USER_EMAIL,  user.getEmailAdress());
        editor.putString(CURRENT_USER_COMPANY,  user.getCompanyName());
        editor.putString(CURRENT_USER_TEL,  user.getTelephone());
        editor.putString(CURRENT_USER_PASSWORD,  user.getPassword());
        editor.commit();

    }


    public void setCurrentUserLogin(User user){

        SharedPreferences.Editor editor = this.pref.edit();
        editor.putString(CURRENT_USER_EMAIL,  user.getEmailAdress());
        editor.putString(CURRENT_USER_PASSWORD,  user.getPassword());

        editor.commit();

    }

    //methods to get the username and the email of the user
    public User getCurrentUser(){

        String email =  pref.getString(CURRENT_USER_EMAIL,null);

        if (email == null) return null; //that's mean there is no current User.

            User user = new User();
            user.setName(pref.getString(CURRENT_USER_NAME,null));
            user.setTelephone(pref.getString(CURRENT_USER_TEL,null));
            user.setPassword(pref.getString(CURRENT_USER_PASSWORD,null));
            user.setCompanyName(pref.getString(CURRENT_USER_COMPANY,null));
            user.setEmailAdress(email);

        Toast.makeText(context, "name "+user.getName(), Toast.LENGTH_SHORT).show();
        return user;
    }

    public void logout() {

        SharedPreferences.Editor editor = this.pref.edit();
        editor.clear();
        editor.commit();
    }
}
